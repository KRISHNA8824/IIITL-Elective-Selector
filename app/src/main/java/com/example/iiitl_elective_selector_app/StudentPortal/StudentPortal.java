package com.example.iiitl_elective_selector_app.StudentPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.iiitl_elective_selector_app.AdminPortal.Elective;
import com.example.iiitl_elective_selector_app.AdminPortal.ElectiveAdapter;
import com.example.iiitl_elective_selector_app.MainActivity;
import com.example.iiitl_elective_selector_app.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class StudentPortal extends AppCompatActivity {

    ImageView logout;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String program,year,branch;
    ArrayList<Elective> electiveArrayList = new ArrayList<>();
    int count_elective;
    User user;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_portal);

        progressDialog = new ProgressDialog(StudentPortal.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
//        progressDialog.show();

        ImageView backPressButton = findViewById(R.id.back_press);
        backPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        logout = findViewById(R.id.logoutIV);
        mAuth = FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(StudentPortal.this, gso);
                mGoogleSignInClient.signOut();
                startActivity(new Intent(StudentPortal.this, MainActivity.class));
                finishAffinity();
            }
        });

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(mAuth.getUid());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                user = snapshot.getValue(Users.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*
        RecyclerView recyclerView = findViewById(R.id.elective_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        ElectiveAdapter electiveAdapter = new ElectiveAdapter(getApplicationContext(),electiveArrayList, map);
        recyclerView.setAdapter(electiveAdapter);
        String new_program = program.substring(0,1) + program.substring(2);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Electives").child(new_program).child(year).child(branch);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    count_elective = 1;
                    boolean flag = false;
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        if(flag) {
                            Elective elective = dataSnapshot.getValue(Elective.class);
                            electiveArrayList.add(elective);
                            count_elective++;
                        }
                        else flag = true;
                    }
                    electiveAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
                else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */
    }
}