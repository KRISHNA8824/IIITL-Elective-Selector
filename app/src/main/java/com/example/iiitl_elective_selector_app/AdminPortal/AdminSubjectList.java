package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iiitl_elective_selector_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminSubjectList extends AppCompatActivity {

    ArrayList<String> subjectArrayList, facultyArrayList;
    String electiveID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subject_list);

        subjectArrayList = new ArrayList<>();
        facultyArrayList = new ArrayList<>();
        Intent intent = getIntent();
        DetailsModel detailsModel = (DetailsModel) intent.getSerializableExtra("Details");
        electiveID =  intent.getStringExtra("Position");
        //Toast.makeText(this, intent.getStringExtra("elective"), Toast.LENGTH_SHORT).show();
        Elective elective = (Elective) intent.getSerializableExtra("elective");
        subjectArrayList = elective.getSubjectArrayList();
        facultyArrayList = elective.getFacultyArrayList();
        RecyclerView recyclerView = findViewById(R.id.subject_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ArrayList<SubjectModel> subjectModelArrayList = new ArrayList<>();
        AdminSubjectAdapter electiveAdapter = new AdminSubjectAdapter(getApplicationContext(), subjectModelArrayList);
        recyclerView.setAdapter(electiveAdapter);


        String program = detailsModel.getNew_program();
        String year = detailsModel.getYear();
        String branch = detailsModel.getBranch();

//        Toast.makeText(AdminSubjectList.this, program + " " + year + " " + branch + " " + electiveID, Toast.LENGTH_SHORT).show();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Electives").child(program).child(year).child(branch);
        reference.child(electiveID).child("Subjects").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChildren()){
//                    Toast.makeText(AdminSubjectList.this, "Exits", Toast.LENGTH_SHORT).show();
                    subjectModelArrayList.clear();
                    for(DataSnapshot dataSnapshot :  snapshot.getChildren()){
                        SubjectModel subject = dataSnapshot.getValue(SubjectModel.class);
                        Log.d("aaaaaa", "onDataChange: " + subject.getSubjectName());
                        subjectModelArrayList.add(subject);
                    }
                    electiveAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImageView backPressButton = findViewById(R.id.back_press);
        backPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}