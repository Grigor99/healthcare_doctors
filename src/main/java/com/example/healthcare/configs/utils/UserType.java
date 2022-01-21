package com.example.healthcare.configs.utils;


import java.io.Serializable;

public enum UserType  {
    DOCTOR("DOCTOR"),
    PATIENT("PATIENT"),
    ADMIN("ADMIN"),
    CLIENT("CLIENT");


    private final String label;

    UserType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static UserType getByLabel(String label) {
        for (UserType userType : values()) {
            if (userType.getLabel().equals(label)) {
                return userType;
            }
        }
        return null;
    }
}