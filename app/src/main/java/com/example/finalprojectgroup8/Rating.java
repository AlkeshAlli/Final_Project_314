package com.example.finalprojectgroup8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Rating extends AppCompatActivity {
    FirebaseDatabase rootnode;
    DatabaseReference reference,newreference;
    EditText mFeedback;
    TextView mRatingScale;
    String username;
    String rating, review,reviewid,reviewerid,userid, usernamesession;
    int countreviews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        final RatingBar mRatingBar = findViewById(R.id.ratingBar);
        mRatingScale = findViewById(R.id.tvRatingScale);
        mFeedback = findViewById(R.id.etFeedback);
        final Button mSendFeedback = findViewById(R.id.btnSubmit);

        username = getIntent().getStringExtra("username").toString();
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });
        SharedPreferences preferences = this.getSharedPreferences("com.example.finalprojectgroup8", Context.MODE_PRIVATE);
        usernamesession = preferences.getString("username", null);
        Log.i("check","session"+usernamesession);

            reference = FirebaseDatabase.getInstance().getReference("review");
            mSendFeedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mFeedback.getText().toString().isEmpty()) {
                        Toast.makeText(Rating.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                    } else {

                        //  mFeedback.setText("");
                        //  mRatingBar.setRating(0);
                        reference.child(username).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists())
                                {
                                    countreviews=(int) dataSnapshot.getChildrenCount();
                                    Log.d("Reviews count", String.valueOf(countreviews));
                                    reviewid="review"+(countreviews+1);
                                }
                                else
                                {
                                    reviewid="review1";
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        rating = mRatingScale.getText().toString();
                        review = mFeedback.getText().toString();
                        reviewerid = usernamesession;
                        //userid = mFeedback.getText().toString();
                        userid=username;
                        addreview();
                        Toast.makeText(Rating.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    private void addreview() {
        rootnode = FirebaseDatabase.getInstance();
        reference = rootnode.getReference("reviews");
        newreference = reference.push();
        ReviewHelper ReviewHelper = new ReviewHelper(review, rating, reviewerid, userid,reviewid);
        newreference.child(userid).setValue(ReviewHelper);
        String key = newreference.child(userid).getKey();
        Log.i("key","rating"+key);

    }


}
