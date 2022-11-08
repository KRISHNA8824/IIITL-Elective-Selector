package com.example.iiitl_elective_selector_app.AdminPortal;

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
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class FloatElective extends AppCompatActivity {
    RecyclerView elective_RecyclerView;
    Button add_elective_button, float_elective_button;
    ArrayList<Elective> electiveArrayList = new ArrayList<>();
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_elective);

        add_elective_button = findViewById(R.id.add_elective_button);
        add_elective_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Elective elective = new Elective();
                elective.setSubjectArrayList(new ArrayList<>());
                electiveArrayList.add(elective);
                elective_RecyclerView = findViewById(R.id.elective_recyclerView);
                elective_RecyclerView.setHasFixedSize(true);
                elective_RecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                ElectiveAdapter electiveAdapter = new ElectiveAdapter(getApplicationContext(),electiveArrayList);
                elective_RecyclerView.setAdapter(electiveAdapter);

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
                     if(arrayList.size() == 0){
                         flag = true;
                         break;
                     }
                 }
                 if(flag){
                     Toast.makeText(getApplicationContext(),"Add Electives First",Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(getApplicationContext(),"Elective Floated Successfully",Toast.LENGTH_SHORT).show();

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