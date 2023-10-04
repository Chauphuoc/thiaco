package com.example.thiaco.enums;

public enum ERole {
    ROLE_ADMIN("Admin"),
    ROLE_USER("User");

    private String value;
    ERole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ERole fromString (String value){
        for (ERole eRole : ERole.values()){
            if (eRole.getValue().equals(value)){
                return eRole;
            }
        }
        return null;
    }
}
