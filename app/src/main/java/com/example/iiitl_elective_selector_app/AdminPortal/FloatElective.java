package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iiitl_elective_selector_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.internal.cache.DiskLruCache;

public class FloatElective extends AppCompatActivity {
    RecyclerView elective_RecyclerView;
    Button add_elective_button, float_elective_button;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_elective);
        Intent intent = getIntent();
        add_elective_button = findViewById(R.id.add_elective_button);

        elective_RecyclerView = findViewById(R.id.elective_recyclerView);
        elective_RecyclerView.setHasFixedSize(true);
        elective_RecyclerView.setLayoutManager(new GridLayoutManager(this,1));
        ArrayList<Elective> electiveArrayList = new ArrayList<>();
        ElectiveAdapter electiveAdapter = new ElectiveAdapter(this,electiveArrayList);
        elective_RecyclerView.setAdapter(electiveAdapter);

        String program = intent.getStringExtra("program");
        String year = intent.getStringExtra("year");
        String branch = intent.getStringExtra("branch");

        add_elective_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Toast.makeText(FloatElective.this, program + " " + year + " " + branch, Toast.LENGTH_SHORT).show();
               Intent new_intent = new Intent(getApplicationContext(),AddSubjects.class);
               new_intent.putExtra("program", program);
               new_intent.putExtra("year", year);
               new_intent.putExtra("branch", branch);
               startActivity(new_intent);
            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        String new_program = program.substring(0,1) + program.substring(2);
        DatabaseReference reference = firebaseDatabase.getReference().child("Electives").child(new_program).child(year).child(branch);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.hasChildren()){
//                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                        Elective elective  = dataSnapshot.getValue(Elective.class);
//                    }
//                    electiveAdapter.notifyDataSetChanged();
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        float_elective_button = findViewById(R.id.float_elective_button);
        float_elective_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(this, AdminPortal.class));
//    }

}