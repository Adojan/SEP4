package com.example.SEP_XYZ.models;

public class Administrator {


    private final String email="admin@gmail.com";
    private final String password= "admin123";

    public Administrator() {

    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public boolean equals(Object o) {
        Administrator that = (Administrator) o;

        if (!email.equals(that.email)) return false;
        return password.equals(that.password);
    }


}
