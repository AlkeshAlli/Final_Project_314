package com.example.finalprojectgroup8;

public class RecyclerViewList {
    String username;
    String location;
    String rate;
    public RecyclerViewList() {
    }

    public RecyclerViewList(String username, String location,String rate) {
        this.username = username;
        this.location = location;
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String email) {
        this.location = location;
    }
}
