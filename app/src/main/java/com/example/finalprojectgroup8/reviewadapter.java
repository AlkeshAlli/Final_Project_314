package com.example.finalprojectgroup8;

import android.icu.text.Transliterator;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.finalprojectgroup8.R.id.reviewimage;

public class reviewadapter extends RecyclerView.Adapter<reviewadapter.MyViewHolder> {

    ReviewFragment reviewFragment;
    ArrayList<RatingHelper> ratingHelpers;

    public reviewadapter(ReviewFragment review,ArrayList<RatingHelper> ratingHelpers1){
        this.reviewFragment = review;
        this.ratingHelpers = ratingHelpers1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.rating_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        int pic=R.drawable.logo;
        holder.name.setText(ratingHelpers.get(position).getReviewerid());
        holder.review.setText(ratingHelpers.get(position).getDescription());
        holder.rating.setText(String.valueOf(ratingHelpers.get(position).getRating() ));
        DatabaseReference imgref = FirebaseDatabase.getInstance().getReference("images");
        Query query = imgref.orderByChild("username").equalTo(ratingHelpers.get(position).getReviewerid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Uri imgurl = Uri.parse(dataSnapshot.child(ratingHelpers.get(position).getReviewerid()).child("imageurl").getValue(String.class));
                    Glide.with(reviewFragment.getActivity()).load(imgurl).into(holder.revImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
       return ratingHelpers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,review,rating;
        ImageView revImage;
        public MyViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.reviewerid);
            review = itemView.findViewById(R.id.reviewcontent);
            rating = itemView.findViewById(R.id.starrate);
            revImage = itemView.findViewById(R.id.reviewimage);
        }
    }
}
