package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iiitl_elective_selector_app.Authentication.Details;
import com.example.iiitl_elective_selector_app.MainActivity;
import com.example.iiitl_elective_selector_app.R;
import com.example.iiitl_elective_selector_app.StudentPortal.StudentPortal;
import com.example.iiitl_elective_selector_app.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Objects;

public class AdminPortal extends AppCompatActivity {

    ImageView logout;
    Button next_button;
    AppCompatSpinner spinner_program,spinner_year, spinner_branch;
    FirebaseAuth mAuth;
    String program , branch, year;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
//    ProgressDialog progressDialog;
    CardView nextButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_portal);


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

        /*
        progressDialog = new ProgressDialog(AdminPortal.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
         */

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                progressDialog.show();

                // receive the value by getStringExtra() method and
                // key must be same which is send by first activity
                // display the string into textVie

                String program = autoCompleteTextView1.getText().toString();
                String year = autoCompleteTextView2.getText().toString();
                String branch = autoCompleteTextView3.getText().toString();

                if(program.equals("Choose Program")) {
//                    progressDialog.dismiss();
                    Toast.makeText(AdminPortal.this, "Please choose program.", Toast.LENGTH_SHORT).show();
                }
                else if(year.equals("Choose Year")){
//                    progressDialog.dismiss();
                    Toast.makeText(AdminPortal.this, "Please choose year.", Toast.LENGTH_SHORT).show();
                }
                else if(branch.equals("Choose Branch")){
//                    progressDialog.dismiss();
                    Toast.makeText(AdminPortal.this, "Please choose your branch.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), FloatElective.class);
                    intent.putExtra("program", program);
                    intent.putExtra("year", year);
                    intent.putExtra("branch", branch);
                    startActivity(intent);
                }

            }
        });


        /*
        spinner_program = findViewById(R.id.spinner_program);
        String [] program_itemList = {"B.Tech", "M.Tech"};
        ArrayAdapter<String> program_itemArrayAdapter = new ArrayAdapter<>(AdminPortal.this, R.layout.dropdown_item, program_itemList);
        spinner_program.setAdapter(program_itemArrayAdapter);
        program = spinner_program.getSelectedItem().toString();

        spinner_year = findViewById(R.id.spinner_year);
        ArrayList<String> year_itemList = new ArrayList<>();
        if(Objects.equals(program, "B.Tech")){
            year_itemList.add("1st Year");
            year_itemList.add("2nd Year");
            year_itemList.add("3rd Year");
            year_itemList.add("4th Year");
        }else{
            year_itemList.add("1st Year");
            year_itemList.add("2nd Year");
        }
        ArrayAdapter<String> year_itemArrayAdapter = new ArrayAdapter<>(AdminPortal.this, R.layout.dropdown_item, year_itemList);
        spinner_year.setAdapter(year_itemArrayAdapter);
        year = spinner_year.getSelectedItem().toString();

        spinner_branch = findViewById(R.id.spinner_branch);
        String [] branch_itemList = {"CSAI", "CS", "IT", "CSB"};
        ArrayAdapter<String> branch_itemArrayAdapter = new ArrayAdapter<>(AdminPortal.this, R.layout.dropdown_item, branch_itemList);
        spinner_branch.setAdapter(branch_itemArrayAdapter);
        branch = spinner_branch.getSelectedItem().toString();

        next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FloatElective.class);
                intent.putExtra("program", program);
                intent.putExtra("year", year);
                intent.putExtra("branch", branch);
                startActivity(intent);
            }
        });
         */




        logout = findViewById(R.id.logoutIV);
        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(AdminPortal.this, gso);
                mGoogleSignInClient.signOut();
                startActivity(new Intent(AdminPortal.this, MainActivity.class));
                finishAffinity();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
        }else{
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

}