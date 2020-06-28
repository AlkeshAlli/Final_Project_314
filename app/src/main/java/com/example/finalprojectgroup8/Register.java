package com.example.finalprojectgroup8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothA2dp;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.finalprojectgroup8.JavaHelperClass;
import com.example.finalprojectgroup8.Login;
import com.example.finalprojectgroup8.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.ACTION_GET_CONTENT;

public class Register extends AppCompatActivity {
    StorageReference storageReference;
    int imgtest=0;
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri imageuri;
    StorageTask registerTask;
    EditText signupname,signupphone,signupemail,signuppass;
    ImageView userimage;
    Button button,button1;
    FirebaseDatabase rootnode,rootnode2;
    DatabaseReference reference,reference2,imageref;
    Spinner spinner;
    int selpos;
    boolean fieldcheck=false;
    String textselect,testemail,username,testphone,testpass,testreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        List<String> Regdata = new ArrayList<>();
        Regdata.add(0,"Register As Nanny");
        Regdata.add(1,"Register For Nanny");
        userimage = findViewById(R.id.regisimg);
        signupemail=findViewById(R.id.email);
        signupname=findViewById(R.id.name);
        signupphone=findViewById(R.id.phone);
        signuppass=findViewById(R.id.pass);
        button=findViewById(R.id.signup);
        button1=findViewById(R.id.asignup);
        spinner=findViewById(R.id.regspin);
        storageReference = FirebaseStorage.getInstance().getReference("profilepics");
        userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgtest=111;
                openFileChooser();
            }
        });


        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>
                (this,android.R.layout.simple_spinner_item,Regdata);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textselect=parent.getItemAtPosition(position).toString();
                selpos = position;
                Toast.makeText(Register.this,"Selected "+textselect,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrationconditions();
                if(fieldcheck==false)
                    CheckforDuplicateValues(username,selpos);
                    uploadProfile();

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(), Login.class);
                startActivity(intent1);
            }
        });
    }
    private void RigisterasNanny() {
        rootnode=FirebaseDatabase.getInstance();
        reference=rootnode.getReference("Register As Nanny");
        JavaHelperClass javaHelperClass = new JavaHelperClass(username,testemail,testphone,testpass);
        reference.child(username).setValue(javaHelperClass);
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }

    private void Rigisterfornanny() {
        rootnode=FirebaseDatabase.getInstance();
        reference=rootnode.getReference("Register For Nanny");
        JavaHelperClass javaHelperClass = new JavaHelperClass(username, testemail, testphone,testpass);
        reference.child(username).setValue(javaHelperClass);
        Log.d("Awesome",testemail);
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }

    private void CheckforDuplicateValues(final String username,int selpos){

        if(selpos==0)
        {
            reference = FirebaseDatabase.getInstance().getReference("Register As Nanny");
            Query checkuser = reference.orderByChild("username").equalTo(username);
            checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        signupname.setError("Username already exits");
                        Toast.makeText(Register.this, "Username already Exists", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        reference2 = FirebaseDatabase.getInstance().getReference("Register For Nanny");
                        Query checkuser2 = reference2.orderByChild("username").equalTo(username);
                        checkuser2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists())
                                {
                                    signupname.setError("Username already exits");
                                    Toast.makeText(Register.this, "Username already Exists", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    RigisterasNanny();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else {
            reference2 = FirebaseDatabase.getInstance().getReference("Register For Nanny");
            Query checkuser2 = reference2.orderByChild("username").equalTo(username);
            checkuser2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                    {
                        signupname.setError("Username already exits");
                        Toast.makeText(Register.this, "Username already Exists", Toast.LENGTH_SHORT).show();
                    }

                    else

                    {
                        reference = FirebaseDatabase.getInstance().getReference("Register As Nanny");
                        Query checkuser = reference.orderByChild("username").equalTo(username);
                        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists())
                                {
                                    signupname.setError("Username already exits");
                                    Toast.makeText(Register.this, "Username already Exists", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Rigisterfornanny();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }

    private void Registrationconditions(){

        fieldcheck=false;

        testemail=signupemail.getText().toString();
        username=signupname.getText().toString();
        testphone=signupphone.getText().toString();
        testpass=signuppass.getText().toString();
        testreview="alkesh";

        if(imgtest==0)
        {
            Toast.makeText(Register.this,"Please select a Image",Toast.LENGTH_SHORT).show();
            fieldcheck=true;
        }
        if (TextUtils.isEmpty(testemail)){
            signupemail.setError("Email field is empty");
            fieldcheck=true;
        }
        if (TextUtils.isEmpty(username)){
            signupname.setError("Field is empty");
            fieldcheck=true;
        }
        if (TextUtils.isEmpty(testphone)){
            signupphone.setError("Field is empty");
            fieldcheck=true;
        }
        if (TextUtils.isEmpty(testpass)){
            signuppass.setError("Field is Empty");
            fieldcheck=true;
        }

    }
    private void openFileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageuri = data.getData();
            Picasso.with(this).load(imageuri).into(userimage);
            userimage.setImageURI(imageuri);
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadProfile() {
        if (imageuri != null) {
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageuri));
            registerTask = fileReference.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imagedata = uri.toString();
                                    Log.d("img url", imagedata);
                                    imageref = FirebaseDatabase.getInstance().getReference("images");
                                    imageref.child(username).child("username").setValue(username);
                                    imageref.child(username).child("imageurl").setValue(imagedata);
                                }
                            });
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            }, 500);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progressbar = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        }
                    });
        }
        else{
            Toast.makeText(this,"no file selected",Toast.LENGTH_SHORT).show();
        }

    }

}
