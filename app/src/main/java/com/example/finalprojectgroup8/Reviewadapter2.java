package com.example.finalprojectgroup8;

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

public class Reviewadapter2 extends RecyclerView.Adapter<Reviewadapter2.MyViewHolder> {

    Rating rat;
    ArrayList<RatingHelper> ratingHelpers;

    public Reviewadapter2(Rating rating, ArrayList<RatingHelper> reviewlist) {
        this.rat=rating;
        this.ratingHelpers=reviewlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.rating_list,parent,false);

        return new Reviewadapter2.MyViewHolder(view);
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
                    Glide.with(rat).load(imgurl).into(holder.revImage);
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
