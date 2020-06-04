package com.example.finalprojectgroup8;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {


    ValueEventListener homeFragment;
    ArrayList<RecyclerViewList> recyclerViewLists;

    public myAdapter(ValueEventListener home, ArrayList<RecyclerViewList> profiles){
        homeFragment=home;
        recyclerViewLists = profiles;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText1.setText(recyclerViewLists.get(position).getUsername());
        holder.myText2.setText(recyclerViewLists.get(position).getEmail());


       /* holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,DetailsActivity.class);
                i.putExtra("data1", data1[position]);
                i.putExtra("data2", data2[position]);
                i.putExtra("myImage", images[position]);
                context.startActivity(i);


            }
        });*/



    }

    @Override
    public int getItemCount() {
        return recyclerViewLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView myText1,myText2;
        ImageView myImage;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.username);
            myText2 = itemView.findViewById(R.id.mailid);
            myImage = itemView.findViewById(R.id.profileImage);
            mainLayout = itemView.findViewById(R.id.mainLayout);        }
    }
}
