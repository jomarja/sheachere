package com.parkingapplication.demoApplication.data;

import com.parkingapplication.demoApplication.model.Role;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"USER\"")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private Integer paymentInfo;

    @Column(nullable = false)
    @Enumerated
    private Role role;  // Add role field

    public user(String name, String email, String password,Role role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // Constructors, getters, and setters

    public user() {}

    // Getters and Setters for all fields including 'role'
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(Integer paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        user that = (user) o;
        return Objects.equals(userID, that.userID)
                && Objects.equals(name, that.name)
                && Objects.equals(password, that.password)
                && Objects.equals(email, that.email)
                && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, password, email, role);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + userID +
                ", username='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
