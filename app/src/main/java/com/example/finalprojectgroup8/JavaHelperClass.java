package com.example.finalprojectgroup8;

public class JavaHelperClass {
    String username, email, phone, password, review;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public JavaHelperClass(String username, String email, String phone, String password, String review) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.review=review;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}