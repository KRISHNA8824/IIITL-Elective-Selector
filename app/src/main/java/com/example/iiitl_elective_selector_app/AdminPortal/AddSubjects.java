package com.example.iiitl_elective_selector_app.AdminPortal;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.iiitl_elective_selector_app.Authentication.Details;
import com.example.iiitl_elective_selector_app.Authentication.LoginStudent;
import com.example.iiitl_elective_selector_app.R;
import com.example.iiitl_elective_selector_app.StudentPortal.StudentPortal;
import com.example.iiitl_elective_selector_app.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class AddSubjects extends AppCompatActivity implements View.OnClickListener{
    LinearLayout layoutList;
    Button add_button, finish_button;
    ImageView removeImage;
    Intent intent;
    int count_electives = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);

        intent = getIntent();
        layoutList = findViewById(R.id.layout_list); // to add subject row
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(this);

        finish_button = findViewById(R.id.finish_button);
        finish_button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.add_button:
                addView();
                break;

            case R.id.finish_button:
                if(checkIfValidAndRead()){
                    startActivity(new Intent(getApplicationContext(),FloatElective.class));
                    Toast.makeText(this,"Elective Added Successfully",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private boolean checkIfValidAndRead() {
        boolean result = true;
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> facultyArrayList = new ArrayList<>();
        for(int i=0;i<layoutList.getChildCount();i++) {

            View subjectView = layoutList.getChildAt(i);
            TextInputEditText subject_name = subjectView.findViewById(R.id.subject_nameET);
            TextInputEditText facultyName = subjectView.findViewById(R.id.faculty_nameET);
            if (!subject_name.getText().toString().equals("") && !facultyName.getText().toString().equals("") ){
                arrayList.add(subject_name.getText().toString());
                facultyArrayList.add(facultyName.getText().toString());
            }else{
                result = false;
                break;
            }

        }
        if(arrayList.size() == 0){
            result = false;
            Toast.makeText(this,"Add Subjects First!",Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this,"Fill all details correctly",Toast.LENGTH_SHORT).show();
        }
        if(result == true && arrayList.size() != 0){
            Elective elective = new Elective();
            TextInputEditText number_of_seats = findViewById(R.id.number_of_seatsET);
            String number = number_of_seats.getText().toString();
            elective.setSubjectArrayList(arrayList);
            elective.setNumberOfSeats(number);
            elective.setFacultyArrayList(facultyArrayList);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();


            String program = intent.getStringExtra("program");
            String year = intent.getStringExtra("year");
            String branch = intent.getStringExtra("branch");
            String new_program = program.substring(0,1) + program.substring(2);
            DatabaseReference reference = firebaseDatabase.getReference().child("Electives")
                    .child(new_program).child(year).child(branch);
            count_electives = 1;

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
//                        Toast.makeText(AddSubjects.this, "hihihi", Toast.LENGTH_SHORT).show();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            count_electives++;
                        }
                        reference.child("Elective" + count_electives).setValue(elective);
                    }
                    else {
                        reference.child("Elective" + count_electives).setValue(elective);
//                        Toast.makeText(AddSubjects.this, "kkk", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        return result;
    }

    private void addView(){
        final View subjectView  = getLayoutInflater().inflate(R.layout.row_add_subjects,null,false);
        removeImage = (ImageView)subjectView.findViewById(R.id.image_remove);
        removeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(subjectView);
            }
        });
        layoutList.addView(subjectView);
    }
    private void removeView(View view){
        layoutList.removeView(view);
    }
}