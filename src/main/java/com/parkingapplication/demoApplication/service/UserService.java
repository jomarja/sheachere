package com.parkingapplication.demoApplication.service;

import com.parkingapplication.demoApplication.model.Role;
import com.parkingapplication.demoApplication.data.user;
import com.parkingapplication.demoApplication.model.User;
import com.parkingapplication.demoApplication.repo.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private static userRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(userRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String email, String password) throws Exception {
        if (userRepository.findByName(username).isPresent()) {
            throw new Exception("Username is already taken");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("Email is already registered");
        }

        user newUser = new user();
        newUser.setName(username);
        newUser.setEmail(email);
        newUser.setPaymentInfo(0);
        newUser.setPassword(passwordEncoder.encode(password));

        if (username.equals("admin")) {
            newUser.setRole(Role.ADMIN);
        } else {
            newUser.setRole(Role.USER);
        }

        userRepository.save(newUser);
    }

    public User findByUsername(String username) {
        return toDomain(Objects.requireNonNull(userRepository.findByName(username).orElse(null)));
    }

    public List<User> getAllUsersExceptCurrent(String currentUsername) {
        return userRepository.findAll().stream().map(UserService::toDomain)
                .filter(user -> !user.getUsername().equals(currentUsername))
                .collect(Collectors.toList());
    }

    private static User toDomain(user entity) {
        return new User(entity.getUserID(), entity.getName(), entity.getPassword(), entity.getEmail(), entity.getPaymentInfo(), entity.getRole());
    }

    public static void addMoney(String username, int amount) {
        // Find user by name using Optional
        Optional<user> optionalUser = userRepository.findByName(username);

        // Check if user is present
        if (optionalUser.isPresent()) {
            user user = optionalUser.get(); // Retrieve the User object from Optional
            user.setPaymentInfo(user.getPaymentInfo() + amount);
            userRepository.save(user); // Save the updated user information
        } else {
            // Handle case where user is not found, if necessary
            throw new UsernameNotFoundException("User not found with name: " + username);
        }
    }
}