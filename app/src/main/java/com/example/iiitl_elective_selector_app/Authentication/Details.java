package com.example.iiitl_elective_selector_app.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.iiitl_elective_selector_app.R;
import com.example.iiitl_elective_selector_app.StudentPortal.StudentPortal;
import com.example.iiitl_elective_selector_app.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    TextInputEditText name, enrolmentNumber;
    CardView nextButton;
    String userName, userEmail, userEnrolment, lang;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.user_name);
        enrolmentNumber = findViewById(R.id.user_enrolment_number);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        userEmail = intent.getStringExtra("userEmail");
        name.setText(userName);
        userEnrolment = "";
        for(int i=0;i<userEmail.length();i++) {
            if(userEmail.charAt(i)=='@') break;
            if(userEmail.charAt(i)>='a' &&  userEmail.charAt(i)<='z') {
                char c = userEmail.charAt(i);
                userEnrolment += Character.toUpperCase(c);
            }
            else userEnrolment += userEmail.charAt(i);
        }

        enrolmentNumber.setText(userEnrolment);

        name.setText(userName);
        enrolmentNumber.setText(userEnrolment);

        mAuth = FirebaseAuth.getInstance();

        // For Selecting Program

        // get reference to the string array that we just created
        String program[] = getResources().getStringArray(R.array.program);
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, R.layout.dropdown_item, program);
        // get reference to the autocomplete text view
        AutoCompleteTextView autoCompleteTextView1 = findViewById(R.id.choose_program);
        // set adapter to the autocomplete tv to the arrayAdapter
        autoCompleteTextView1.setAdapter(arrayAdapter1);


        // For Selecting Year

        // get reference to the string array that we just created
        String years[] = getResources().getStringArray(R.array.year);
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.dropdown_item, years);
        // get reference to the autocomplete text view
        AutoCompleteTextView autoCompleteTextView2 = findViewById(R.id.choose_year);
        // set adapter to the autocomplete tv to the arrayAdapter
        autoCompleteTextView2.setAdapter(arrayAdapter2);


        // For Selecting Branch

        // get reference to the string array that we just created
        String branches[] = getResources().getStringArray(R.array.branch);
        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, R.layout.dropdown_item, branches);
        // get reference to the autocomplete text view
        AutoCompleteTextView autoCompleteTextView3 = findViewById(R.id.choose_branch);
        // set adapter to the autocomplete tv to the arrayAdapter
        autoCompleteTextView3.setAdapter(arrayAdapter3);


        progressDialog = new ProgressDialog(Details.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

         nextButton = findViewById(R.id.next_button);
         nextButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 progressDialog.show();

                 // receive the value by getStringExtra() method and
                 // key must be same which is send by first activity
                 // display the string into textVie

                 String program = autoCompleteTextView1.getText().toString();
                 String year = autoCompleteTextView2.getText().toString();
                 String branch = autoCompleteTextView3.getText().toString();

                 if(name.getText().toString().matches("")){
                     progressDialog.dismiss();
                     Toast.makeText(Details.this, "Please fill your name.", Toast.LENGTH_SHORT).show();
                 }
                 else if(enrolmentNumber.getText().toString().matches("")) {
                     progressDialog.dismiss();
                     Toast.makeText(Details.this, "Please fill your enrolment number.", Toast.LENGTH_SHORT).show();
                 }
                 else if(program.equals("Choose Program")) {
                     progressDialog.dismiss();
                     Toast.makeText(Details.this, "Please choose program.", Toast.LENGTH_SHORT).show();
                 }
                 else if(year.equals("Choose Year")){
                     progressDialog.dismiss();
                     Toast.makeText(Details.this, "Please choose year.", Toast.LENGTH_SHORT).show();
                 }
                 else if(branch.equals("Choose Branch")){
                     progressDialog.dismiss();
                     Toast.makeText(Details.this, "Please choose your branch.", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Registered Users").child(mAuth.getUid());
                     Users user = new Users(mAuth.getUid(), userName, userEmail, userEnrolment, program, year, branch);
                     databaseReference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if (task.isSuccessful()) {
                                 progressDialog.dismiss();
                                 startActivity(new Intent(Details.this, StudentPortal.class));
                                 finishAffinity();
                             /*
                             DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Registered Enrolment").child(userEnrolment);
                             reference1.setValue(mAuth.getCurrentUser().getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     progressDialog.dismiss();
                                     startActivity(new Intent(Details.this, StudentPortal.class));
                                     Toast.makeText(getApplicationContext(), "You have successfully signed in.", Toast.LENGTH_SHORT).show();
                                 }
                             });

                              */

                             } else {
                                 progressDialog.dismiss();
                                 Toast.makeText(Details.this, "Error occurred", Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
                 }

             }
         });



    }
}