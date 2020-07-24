package com.example.amikomhilang.Model;

import java.io.Serializable;

public class Mahasiswa implements Serializable {

    public String username;
    public String email;

    public Mahasiswa() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Mahasiswa(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
