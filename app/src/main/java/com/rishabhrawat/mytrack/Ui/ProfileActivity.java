package com.rishabhrawat.mytrack.Ui;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rishabhrawat.mytrack.Models.Locationhisory;
import com.rishabhrawat.mytrack.Models.User;
import com.rishabhrawat.mytrack.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    CircleImageView profileImage;
    TextView pname;
    TextView email;
    String sname,semail,suid,sphoneNo;
    Uri photoUrl;
    FirebaseFirestore firestore;
    User mUserData;
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
        firestore = FirebaseFirestore.getInstance();


        /*...........start button event........*/
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveUserData();
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


        /*======setting user data--------*/
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            sname = user.getDisplayName();
            semail = user.getEmail();
            photoUrl = user.getPhotoUrl();
             suid = user.getUid();
            sphoneNo=user.getPhoneNumber();

            mUserData=new User();
            mUserData.setPname(sname);
            mUserData.setPemail(semail);
            mUserData.setPhoneno(sphoneNo);
            mUserData.setLastLogin(null);
            mUserData.setAuthid(suid);


            }


            /*-------filling up the user information ------*/

        Glide.with(this.getBaseContext()).load(photoUrl).into(profileImage);
        pname.setText(sname);
        email.setText(semail);




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




    /*---------Saveing user data..--------------------*/

    public void saveUserData()
    {

        DocumentReference userReference=FirebaseFirestore.getInstance().
                collection("Users").document(FirebaseAuth.getInstance().getUid());
        
        userReference.set(mUserData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete())
                {
                    Log.d(TAG, "onComplete: ");
                }
            }
        });

    }
}
