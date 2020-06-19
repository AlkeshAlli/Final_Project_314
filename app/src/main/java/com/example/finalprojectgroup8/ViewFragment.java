package com.example.finalprojectgroup8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CountDownLatch;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {

    SharedPreferences preferences;
    String usernamefromsession;
    DatabaseReference reference,reference2;
    Boolean creationuser;
    String Storeage,Storedescription,Storeexp,Storeloc,Storefulln,Storerate,
            Storechild,Storefornannydescp,Storefornannyloc,Storefornannyfname,Storefornannyrate;

    public ViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view, container, false);
        Button udate = v.findViewById(R.id.update);

        preferences =this.getActivity().getSharedPreferences("com.example.finalprojectgroup8",Context.MODE_PRIVATE);
        usernamefromsession=preferences.getString("username",null);
        udate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preferences.getBoolean("asNanny",true)){

                    CountDownTimer done = new CountDownTimer(2000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            reference= FirebaseDatabase.getInstance().
                                    getReference("Profile Creation AsNanny").child(usernamefromsession);
                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Storeage=dataSnapshot.child("age").getValue(String.class);
                                    Storedescription=dataSnapshot.child("description").getValue(String.class);
                                    Storeexp=dataSnapshot.child("experience").getValue(String.class);
                                    Storefulln=dataSnapshot.child("fullname").getValue(String.class);
                                    Storeloc=dataSnapshot.child("location").getValue(String.class);
                                    Storerate=dataSnapshot.child("rate").getValue(String.class);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onFinish() {
                            Intent intent=new Intent(getActivity(),ProfileCreationAsNanny.class);
                            intent.putExtra("sendage",Storeage);
                            Log.i("View","age"+Storeage);
                            intent.putExtra("senddescp",Storedescription);
                            Log.i("View","fragment"+Storedescription);
                            intent.putExtra("sendlocation",Storeloc);
                            intent.putExtra("sendfname",Storefulln);
                            intent.putExtra("sendrate",Storerate);
                            intent.putExtra("sendexperience",Storeexp);
                            // intent.putExtra("sendage",Storeage);
                            startActivity(intent);
                        }
                    }.start();
                }
                else {
                    Log.i("test update for nanny","result");
                    CountDownTimer done = new CountDownTimer(2000,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            reference2= FirebaseDatabase.getInstance().
                                    getReference("Profile Creation For Nanny").child(usernamefromsession);
                            reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Storechild=dataSnapshot.child("children").getValue(String.class);
                                    Storefornannydescp=dataSnapshot.child("description").getValue(String.class);
                                    Storefornannyrate=dataSnapshot.child("rate").getValue(String.class);
                                    Storefornannyfname=dataSnapshot.child("fullname").getValue(String.class);
                                    Storefornannyloc=dataSnapshot.child("location").getValue(String.class);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onFinish() {
                            Intent intent2=new Intent(getActivity(),ProfileCreationForNanny.class);
                            intent2.putExtra("sendchild",Storechild);
                            intent2.putExtra("sendfnannydescp",Storefornannydescp);
                            Log.i("View","fragment"+Storefornannydescp);
                            intent2.putExtra("sendfnannylocation",Storefornannyloc);
                            intent2.putExtra("sendfnannyname",Storefornannyfname);
                            intent2.putExtra("sendfrate",Storefornannyrate);
                            startActivity(intent2);
                        }
                    }.start();

                }
            }
        });

        return  v;
    }

}
