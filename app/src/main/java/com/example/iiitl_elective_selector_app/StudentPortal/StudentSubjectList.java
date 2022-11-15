package com.example.iiitl_elective_selector_app.StudentPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iiitl_elective_selector_app.AdminPortal.DetailsModel;
import com.example.iiitl_elective_selector_app.AdminPortal.Elective;
import com.example.iiitl_elective_selector_app.AdminPortal.SubjectModel;
import com.example.iiitl_elective_selector_app.R;
import com.example.iiitl_elective_selector_app.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentSubjectList extends AppCompatActivity {

    ArrayList<String> subjectArrayList, facultyArrayList;
    Button selectButton;
    String electiveID;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subject_list);
        selectButton = findViewById(R.id.select_button);

        ImageView backPressButton = findViewById(R.id.back_press);
        backPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        subjectArrayList = new ArrayList<>();
        facultyArrayList = new ArrayList<>();
        Intent intent = getIntent();
        DetailsModel detailsModel = (DetailsModel) intent.getSerializableExtra("Details");
        electiveID =  intent.getStringExtra("Position");
        //Toast.makeText(this, intent.getStringExtra("elective"), Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView = findViewById(R.id.subject_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ArrayList<SubjectModel> subjectModelArrayList = new ArrayList<>();
        StudentSubjectAdapter subjectAdapter = new StudentSubjectAdapter(getApplicationContext(), subjectModelArrayList);
        recyclerView.setAdapter(subjectAdapter);

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
                        subjectModelArrayList.add(subject);
                    }
                    subjectAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Uid = FirebaseAuth.getInstance().getUid();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Student").child(Uid).child(electiveID);
                reference.setValue(StudentSubjectAdapter.message);


                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Admin").child(program).child(year).child(branch).child(electiveID).child(StudentSubjectAdapter.message);
                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("Registered Users").child(Uid);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            Users user = snapshot.getValue(Users.class);
                            reference1.child(Uid).setValue(user.getEnrolment());


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Toast.makeText(StudentSubjectList.this, "Subject selected", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}