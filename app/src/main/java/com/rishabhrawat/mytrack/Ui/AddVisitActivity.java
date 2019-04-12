
package com.rishabhrawat.mytrack.Ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.rishabhrawat.mytrack.Models.addVisit;
import com.rishabhrawat.mytrack.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddVisitActivity extends AppCompatActivity {
    private static final String TAG = "AddVisitActivity";

    String Cname;
    String Ccontactno;
    String PersonIntracted;
    String Remark;
    String Status;
    addVisit addVisit;
    String la,lo;
    ProgressBar progressBar;


    EditText name, contactno, intractedperson, remark, status;

    Button savevisit;

    private FusedLocationProviderClient mFusedLocationClient;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visit);

        /*-----------------------initialising--------------------------*/
        name = (EditText) findViewById(R.id.name_of_client);
        intractedperson = (EditText) findViewById(R.id.person_interacted);
        contactno = (EditText) findViewById(R.id.contact_no);
        remark = (EditText) findViewById(R.id.remarks);
        status = (EditText) findViewById(R.id.status);
        savevisit = (Button) findViewById(R.id.savevisit_btn);
        progressBar=(ProgressBar)findViewById(R.id.pogressbarp);

        addVisit = new addVisit();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        /*.....intent data.....*/

        la=getIntent().getStringExtra("la");
        lo=getIntent().getStringExtra("lo");







        /*-----onclick Listener --------*/

        savevisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                Cname = name.getText().toString().trim();
                PersonIntracted = intractedperson.getText().toString().trim();
                Ccontactno = contactno.getText().toString().trim();
                Remark = remark.getText().toString().trim();
                Status = status.getText().toString().trim();


                if(Cname.isEmpty() || PersonIntracted.isEmpty() || Ccontactno.isEmpty() || Remark.isEmpty()  || Status.isEmpty()) {
                    /*-----setting the add visit-----*/
                    Toast.makeText(AddVisitActivity.this, "Enter all the details", Toast.LENGTH_SHORT).show();

            }
                else
                {
                    addVisit.setVisited_by(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    addVisit.setClient_name(Cname);
                    addVisit.setPerson_Intracted(PersonIntracted);
                    addVisit.setClient_contactno(Ccontactno);
                    addVisit.setDateofVisit(null);
                    addVisit.setRemark(Remark);
                    addVisit.setStatus(Status);
                    double latitude = Double.parseDouble(la);
                    double longitude = Double.parseDouble(lo);
                    GeoPoint geoPoint = new GeoPoint(latitude,longitude);
                    addVisit.setGeopoint(geoPoint);
                    savevisit();
                }
            }
        });

    }

    private void savevisit() {
        if (addVisit != null) {
            try {
                firestore = FirebaseFirestore.getInstance();
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String dateTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
                final DocumentReference saveVisitReference = firestore.
                        collection("Visit").
                        document(FirebaseAuth.getInstance().getUid()).
                        collection(date).
                        document(dateTime);


             saveVisitReference.set(addVisit).addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(AddVisitActivity.this);
                    dlgAlert.setMessage("Visit Sucsessfully added");
                    dlgAlert.setTitle("Confirmation");
                    dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            name.setText("");
                            intractedperson.setText("");
                            contactno.setText("");
                            remark.setText("");
                            status.setText("");
                            progressBar.setVisibility(View.INVISIBLE);
                            finish();
                        }
                    });

                    dlgAlert.create().show();
                }
                else
                {
                    Toast.makeText(AddVisitActivity.this, "not added", Toast.LENGTH_SHORT).show();
                }

                 }
             });
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("-------------------------------------------------------");
            }
        }
    }
}
