package com.example.finalprojectgroup8;

public class RecyclerViewList {
    String username;
    String location;

    public RecyclerViewList() {
    }

    public RecyclerViewList(String username, String location) {
        this.username = username;
        this.location = location;
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
