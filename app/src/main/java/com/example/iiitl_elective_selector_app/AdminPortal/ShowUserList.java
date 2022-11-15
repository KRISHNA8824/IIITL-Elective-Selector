package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iiitl_elective_selector_app.R;
import com.example.iiitl_elective_selector_app.StudentModel;
import com.example.iiitl_elective_selector_app.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowUserList extends AppCompatActivity {

    String electiveID, subjectID;
    ArrayList<StudentModel> studentArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_list);

        Intent intent = getIntent();
        DetailsModel detailsModel = (DetailsModel) intent.getSerializableExtra("Details");
        electiveID =  intent.getStringExtra("electiveID");
        subjectID = intent.getStringExtra("subjectID");

        RecyclerView recyclerView = findViewById(R.id.student_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        studentArrayList = new ArrayList<>();
        ShowUserListAdapter showUserListAdapter = new ShowUserListAdapter(getApplicationContext(), studentArrayList);
        recyclerView.setAdapter(showUserListAdapter);


        String program = detailsModel.getNew_program();
        String year = detailsModel.getYear();
        String branch = detailsModel.getBranch();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Admin").child(program).child(year).child(branch).child(electiveID).child(subjectID);
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        Toast.makeText(ShowUserList.this, dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();
                        StudentModel studentModel = dataSnapshot.getValue(StudentModel.class);
                        studentArrayList.add(studentModel);
//                        Toast.makeText(ShowUserList.this, studentModel.getStudentName(), Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(ShowUserList.this, "" + studentArrayList.size(), Toast.LENGTH_SHORT).show();
                    showUserListAdapter.notifyDataSetChanged();
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