package com.example.map_my_sona;

import com.google.android.material.textfield.TextInputEditText;

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

