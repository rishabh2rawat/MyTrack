package com.rishabhrawat.mytrack.Ui;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rishabhrawat.mytrack.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    CircleImageView profileImage;
    TextView pname;
    TextView email;
    String name,pemail,uid,phoneNo;
    Uri photoUrl;


    Button startbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*...connecting....*/
        startbtn=(Button)findViewById(R.id.startbtn);
        pname=(TextView)findViewById(R.id.profile_name);
        email=(TextView)findViewById(R.id.email);
        profileImage=(CircleImageView)findViewById(R.id.circular_profileimg);


        /*...........start button event........*/
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,MaponeActivity.class);
                startActivity(intent);
            }



        });



        /*****checking user session*****************/
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                    finish();
                }
            }
        };


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            pemail = user.getEmail();
            photoUrl = user.getPhotoUrl();
             uid = user.getUid();
            phoneNo=user.getPhoneNumber();

            }


            /*-------filling up the user information ------*/

        Glide.with(this.getBaseContext()).load(photoUrl).into(profileImage);
        pname.setText(name);
        email.setText(pemail);


    }



    // menue item in the top corner
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options, menu);

        return super.onCreateOptionsMenu(menu);


    }

    //action for the options button ie logout about and notifiacations
    public void action(MenuItem mi) {
        switch (mi.getItemId()) {
            case R.id.logout:
                mAuth.signOut();

        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
