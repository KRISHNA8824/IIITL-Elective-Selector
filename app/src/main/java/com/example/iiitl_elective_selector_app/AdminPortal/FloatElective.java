package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iiitl_elective_selector_app.Authentication.LoginStudent;
import com.example.iiitl_elective_selector_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import org.w3c.dom.Text;

import java.io.Serializable;
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
    String program,year,branch;
    int count_elective;
    ProgressDialog progressDialog;
    TextView info_text;
    DetailsModel detailsModel;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_elective);

        info_text = (TextView) findViewById(R.id.information_text);

        progressDialog = new ProgressDialog(FloatElective.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Intent intent = getIntent();
        detailsModel = (DetailsModel) intent.getSerializableExtra("Details");
        program = detailsModel.getProgram();
        year = detailsModel.getYear();
        branch = detailsModel.getBranch();

        FloatingActionButton fab = findViewById(R.id.addElective);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FloatElective.this, program + " " + year + " " + branch, Toast.LENGTH_SHORT).show();
                Intent new_intent = new Intent(getApplicationContext(),AddSubjects.class);

                new_intent.putExtra("Details", detailsModel);
//                new_intent.putExtra("program", program);
//                new_intent.putExtra("year", year);
//                new_intent.putExtra("branch", branch);
                startActivity(new_intent);
            }
        });

        ImageView backPressButton = findViewById(R.id.back_press);
        backPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        add_elective_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////
//////                Toast.makeText(FloatElective.this, program + " " + year + " " + branch, Toast.LENGTH_SHORT).show();
////               Intent new_intent = new Intent(getApplicationContext(),AddSubjects.class);
////                new_intent.putExtra("program", program);
////                new_intent.putExtra("year", year);
////                new_intent.putExtra("branch", branch);
////                startActivity(new_intent);
//            }
//        });

        RecyclerView recyclerView = findViewById(R.id.elective_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        HashMap<Integer, String> map = new HashMap<>();
        String new_program = program.substring(0,1) + program.substring(2);
        ElectiveAdapter electiveAdapter = new ElectiveAdapter(getApplicationContext(), electiveArrayList, map, detailsModel);
        recyclerView.setAdapter(electiveAdapter);


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
                            map.put(count_elective, dataSnapshot.getKey().toString());
                            count_elective++;
                        }
                        else flag = true;
                    }
                    electiveAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
                else {
                    Toast.makeText(FloatElective.this, new_program + " " + year + " " + branch, Toast.LENGTH_SHORT).show();
                    info_text.setText("No elective is added.");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(this, AdminPortal.class));
//    }

}