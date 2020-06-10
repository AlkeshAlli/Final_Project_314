package com.example.finalprojectgroup8;

public class ReviewHelper {
    String description,rating,reviewerid,userid;


    public ReviewHelper(String description, String rating, String reviewerid, String userid) {
        this.description = description;
        this.rating = rating;
        this.reviewerid = reviewerid;
        this.userid = userid;
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

    public String getUserid() {
        return userid;
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

    public void setUserid(String userid) {
        this.userid = userid;
    }
}

