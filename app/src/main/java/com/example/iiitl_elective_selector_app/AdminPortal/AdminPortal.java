package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.iiitl_elective_selector_app.MainActivity;
import com.example.iiitl_elective_selector_app.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdminPortal extends AppCompatActivity {

    ImageView logout;
    TextView float_electiveTV;
    AppCompatSpinner spinner_program,spinner_year, spinner_branch;
    FirebaseAuth mAuth;
    String program , branch, year ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_portal);


        spinner_program = findViewById(R.id.spinner_program);
        String [] program_itemList = {"B.Tech", "M.Tech"};
        ArrayAdapter<String> program_itemArrayAdapter = new ArrayAdapter<>(AdminPortal.this, R.layout.dropdown_item, program_itemList);
        spinner_program.setAdapter(program_itemArrayAdapter);
        program = spinner_program.getSelectedItem().toString();

        spinner_year = findViewById(R.id.spinner_year);
        String [] year_itemList = {"1st Year", "2nd Year", "3rd Year","4th Year"};
        ArrayAdapter<String> year_itemArrayAdapter = new ArrayAdapter<>(AdminPortal.this, R.layout.dropdown_item, year_itemList);
        spinner_year.setAdapter(year_itemArrayAdapter);
        year = spinner_year.getSelectedItem().toString();

        spinner_branch = findViewById(R.id.spinner_branch);
        String [] branch_itemList = {"CSAI", "CS", "IT", "CSB"};
        ArrayAdapter<String> branch_itemArrayAdapter = new ArrayAdapter<>(AdminPortal.this, R.layout.dropdown_item, branch_itemList);
        spinner_branch.setAdapter(branch_itemArrayAdapter);
        branch = spinner_branch.getSelectedItem().toString();

        float_electiveTV = findViewById(R.id.float_electiveTV);
        float_electiveTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(),FloatElective.class));
                Intent intent = new Intent(getApplicationContext(), FloatElective.class);
                String [] dataString = new String[3];
                dataString[0] = program;
                dataString[1] = year;
                dataString[2] = branch;

                intent.putExtra("stringArray", dataString);
                startActivity(intent);
            }
        });



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
            }
        });

    }
}