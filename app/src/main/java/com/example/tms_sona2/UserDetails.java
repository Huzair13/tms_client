package com.example.tms_sona2;

public class UserDetails {
    String id;
    String email;

    public UserDetails(String id, String email) {
    }

    public String getName() {
        return id;
    }

    public void setName(String name) {
        this.id = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

