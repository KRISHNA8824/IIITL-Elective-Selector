package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class FloatElective extends AppCompatActivity {
    RecyclerView elective_RecyclerView;
    Button add_elective_button, float_elective_button;
    ArrayList<Elective> electiveArrayList = new ArrayList<>();
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
        add_elective_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String program = intent.getStringExtra("program");
               String year = intent.getStringExtra("year");
               String branch = intent.getStringExtra("branch");
               Intent new_intent = new Intent(getApplicationContext(),AddSubjects.class);
                new_intent.putExtra("program", program);
                new_intent.putExtra("year", year);
                new_intent.putExtra("branch", branch);
                startActivity(new_intent);
            }
        });


        float_elective_button = findViewById(R.id.float_elective_button);
        float_elective_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(electiveArrayList.size() == 0){
                   Toast.makeText(getApplicationContext(),"Add Electives First",Toast.LENGTH_SHORT).show();
               }else{
                   boolean flag = false;
                 for(int i=0;i<electiveArrayList.size();i++){
                     ArrayList<String> arrayList = electiveArrayList.get(i).subjectArrayList;
                     String number = electiveArrayList.get(i).numberOfSeats;
                     if(arrayList.size() == 0 || number.equals("")){
                         flag = true;
                         break;
                     }
                 }
                 if(flag){
                     Toast.makeText(getApplicationContext(),"Add Electives First",Toast.LENGTH_SHORT).show();
                 }else{
                     for(int i=0;i<electiveArrayList.size();i++){

                         Elective elective = electiveArrayList.get(i);
                         String program = intent.getStringExtra("program");
                         String year = intent.getStringExtra("year");
                         String branch = intent.getStringExtra("branch");
                         int electiveID = i+1;

                     }

                 }
               }
            }
        });



    }
//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(this, AdminPortal.class));
//    }

}