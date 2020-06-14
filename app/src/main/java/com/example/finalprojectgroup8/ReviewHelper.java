package com.example.finalprojectgroup8;

public class ReviewHelper {
    String description,rating,reviewerid,username,reviewid;


    public ReviewHelper(String description, String rating, String reviewerid, String userid,String reviewid) {
        this.description = description;
        this.rating = rating;
        this.reviewerid = reviewerid;
        this.username = userid;
        this.reviewid = reviewid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReviewid() {
        return reviewid;
    }

    public void setReviewid(String reviewid) {
        this.reviewid = reviewid;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getReviewerid() {
        return reviewerid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setReviewerid(String reviewerid) {
        this.reviewerid = reviewerid;
    }

}

