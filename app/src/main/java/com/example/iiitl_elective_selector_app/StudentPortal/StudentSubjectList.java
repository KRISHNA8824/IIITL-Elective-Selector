package com.example.iiitl_elective_selector_app.StudentPortal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iiitl_elective_selector_app.AdminPortal.Elective;
import com.example.iiitl_elective_selector_app.R;

import java.util.ArrayList;

public class StudentSubjectList extends AppCompatActivity {

    ArrayList<String> subjectArrayList, facultyArrayList;
    Button selectButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subject_list);

        selectButton = findViewById(R.id.select_button);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentSubjectList.this, "Subject selected", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
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
        //Toast.makeText(this, intent.getStringExtra("elective"), Toast.LENGTH_SHORT).show();
        Elective elective = (Elective) intent.getSerializableExtra("elective");
        subjectArrayList = elective.getSubjectArrayList();
        facultyArrayList = elective.getFacultyArrayList();
        RecyclerView recyclerView = findViewById(R.id.subject_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        StudentSubjectAdapter subjectAdapter = new StudentSubjectAdapter(getApplicationContext(),subjectArrayList, facultyArrayList);
        recyclerView.setAdapter(subjectAdapter);




    }
}