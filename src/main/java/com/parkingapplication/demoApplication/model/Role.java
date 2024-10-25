package com.parkingapplication.demoApplication.model;

public enum Role {
    USER("User"),
    ADMIN("Admin");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}