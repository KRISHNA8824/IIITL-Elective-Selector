package com.example.iiitl_elective_selector_app.AdminPortal;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
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
    ImageView removeImage, backPressButton;
    Intent intent;
    int count_electives = 0;
    DetailsModel detailsModel;

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

        backPressButton = findViewById(R.id.back_press);
        backPressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.add_button:
                addView();
                break;

            case R.id.finish_button:
                if(checkIfValidAndRead()){
                    startActivity(new Intent(getApplicationContext(),AdminPortal.class));
                    Toast.makeText(this,"Elective Added Successfully",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    private boolean checkIfValidAndRead() {
        boolean result = true;
        ArrayList<String> seatCountArrayList = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> facultyArrayList = new ArrayList<>();

        ArrayList<SubjectModel> subjectModelArrayList = new ArrayList<>();

        for(int i=0;i<layoutList.getChildCount();i++) {

            View subjectView = layoutList.getChildAt(i);
            TextInputEditText subject_name = subjectView.findViewById(R.id.subject_nameET);
            TextInputEditText facultyName = subjectView.findViewById(R.id.faculty_nameET);
            TextInputEditText number_of_seats = subjectView.findViewById(R.id.number_of_seatsET);

            if (!subject_name.getText().toString().equals("") && !facultyName.getText().toString().equals("") && !number_of_seats.getText().toString().equals("")){

                SubjectModel subjectModel = new SubjectModel(subject_name.getText().toString(), facultyName.getText().toString(), number_of_seats.getText().toString());
                subjectModelArrayList.add(subjectModel);
                arrayList.add(subject_name.getText().toString());
                facultyArrayList.add(facultyName.getText().toString());
                seatCountArrayList.add(number_of_seats.getText().toString());
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
            detailsModel = (DetailsModel) intent.getSerializableExtra("Details");
            String program = detailsModel.getProgram();
            String year = detailsModel.getYear();
            String branch = detailsModel.getBranch();
            String new_program = detailsModel.getNew_program();

            count_electives = 1;
            DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Electives").child(new_program).child(year).child(branch);
            reff.child("Count of ELectives").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
//                        Toast.makeText(AddSubjects.this, "Exits", Toast.LENGTH_SHORT).show();
                        String temp = snapshot.toString();
                        String temp1 = "";
                        int i = temp.length()-1;
                        while(temp.charAt(i) != '=') i--;
                        i += 2;
                        while(true) {
                            if(temp.charAt(i)==' ') break;
                            temp1 += temp.charAt(i++);
                        }
                        count_electives = Integer.parseInt(temp1) + 1;
                        reff.child("Count of ELectives").setValue(Integer.toString(count_electives));
                        for(SubjectModel subjectModel : subjectModelArrayList) {
                            reff.child("Elective" + count_electives).child("Subjects").child(subjectModel.getSubjectName()).setValue(subjectModel);
                        }
                        reff.child("Elective" + count_electives).child("status").setValue("Not Floated");
                    }
                    else {
                        reff.child("Count of ELectives").setValue(Integer.toString(count_electives));
                        for(SubjectModel subjectModel : subjectModelArrayList) {
                            reff.child("Elective" + count_electives).child("Subjects").child(subjectModel.getSubjectName()).setValue(subjectModel);
                        }
                        reff.child("Elective" + count_electives).child("status").setValue("Not Floated");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



/*
            Elective elective = new Elective();
            elective.setSubjectArrayList(arrayList);
            elective.setSeatCountArrayList(seatCountArrayList);
            elective.setStatus("Not Floated");
            elective.setFacultyArrayList(facultyArrayList);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

            DatabaseReference reference = firebaseDatabase.getReference().child("Electives")
                    .child(new_program).child(year).child(branch);
            count_electives = 1;


            reference.child("Count of ELectives").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        String temp = snapshot.toString();
                        String temp1 = "";
                        int i = temp.length()-1;
                        while(temp.charAt(i) != '=') i--;
                        i += 2;
                        while(true) {
                            if(temp.charAt(i)==' ') break;
                            temp1 += temp.charAt(i++);
                        }
                        count_electives = Integer.parseInt(temp1) + 1;

                        reference.child("Count of ELectives").setValue(Integer.toString(count_electives));
                        reference.child("Elective" + count_electives).setValue(elective);
//                        Toast.makeText(AddSubjects.this, "hihihi", Toast.LENGTH_SHORT).show();
//                        for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                            count_electives++;
//                        }
//                        reference.child("Elective" + count_electives).setValue(elective);
                    }
                    else {
                        reference.child("Count of ELectives").setValue(Integer.toString(count_electives));
                        reference.child("Elective" + count_electives).setValue(elective);
//                        reference.child("Elective" + count_electives).setValue(elective);
//                        Toast.makeText(AddSubjects.this, "kkk", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            */

//            for(int i=0;i<arrayList.size();i++) {
//                Log.d("AddSubject", "checkIfValidAndRead: " + arrayList.get(i));
//            }

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