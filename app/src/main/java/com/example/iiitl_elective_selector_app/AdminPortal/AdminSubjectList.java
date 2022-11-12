package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iiitl_elective_selector_app.R;

import java.util.ArrayList;

public class AdminSubjectList extends AppCompatActivity {

    ArrayList<String> subjectArrayList, facultyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subject_list);

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
        SubjectAdapter electiveAdapter = new SubjectAdapter(getApplicationContext(),subjectArrayList, facultyArrayList);
        recyclerView.setAdapter(electiveAdapter);


        ImageView backPressButton = findViewById(R.id.back_press);
        backPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}