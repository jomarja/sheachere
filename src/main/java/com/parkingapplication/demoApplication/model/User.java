package com.parkingapplication.demoApplication.model;

import java.util.Objects;


public class User {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Role role;

    private int paymentInfo;

    public int getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(int paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(Long id, String username, String password, String email, int paymentInfo,Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.paymentInfo = paymentInfo;
        this.role = role;
    }

    public User(String username, String password, String email, Role role, int paymentInfo) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.paymentInfo = paymentInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(id, that.id)
                && Objects.equals(username, that.username)
                && Objects.equals(password, that.password)
                && Objects.equals(email, that.email)
                && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, role);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role.toString() +
                '}';
    }
}