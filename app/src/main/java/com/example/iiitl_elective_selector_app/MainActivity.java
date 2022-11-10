package com.example.iiitl_elective_selector_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.iiitl_elective_selector_app.AdminPortal.AdminPortal;
import com.example.iiitl_elective_selector_app.Authentication.Details;
import com.example.iiitl_elective_selector_app.Authentication.LoginAdmin;
import com.example.iiitl_elective_selector_app.Authentication.LoginStudent;
import com.example.iiitl_elective_selector_app.StudentPortal.StudentPortal;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    CardView admin, student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = (CardView) findViewById(R.id.admin);
        student = (CardView) findViewById(R.id.student);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        if(mAuth.getCurrentUser() != null){
            progressDialog.show();
            if(mAuth.getCurrentUser().getEmail().equals("admin@gmail.com")){
                startActivity(new Intent(MainActivity.this, AdminPortal.class));
                finish();
                progressDialog.dismiss();
            }
            else{

                DatabaseReference reference31 = FirebaseDatabase.getInstance().getReference().child("Registered Users");
                reference31.child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            // user exists in the database
                            startActivity(new Intent(MainActivity.this, StudentPortal.class));
                            progressDialog.dismiss();
                            finishAffinity();
                            Toast.makeText(getApplicationContext(), "You have successfully signed in.", Toast.LENGTH_SHORT).show();

                        }else{
                            // user does not exist in the database
                            signOut();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Error: " + databaseError, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginAdmin.class);
                startActivity(intent);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginStudent.class);
                startActivity(intent);
            }
        });


    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
        mGoogleSignInClient.signOut();
    }
}