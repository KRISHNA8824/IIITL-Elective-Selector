package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.iiitl_elective_selector_app.MainActivity;
import com.example.iiitl_elective_selector_app.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class AdminPortal extends AppCompatActivity {

    ImageView logout;
    TextInputLayout program_menuTIL,branch_menuTIL, year_menuTIL, semester_menuTIL;
    AutoCompleteTextView program_autoCompleteTextView, branch_autoCompleteTextView, year_autoCompleteTextView, semester_autoCompleteTextView;

    FirebaseAuth mAuth;
    String program , branch, year ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_portal);

        program_menuTIL = findViewById(R.id.program_menuTIL);
        program_autoCompleteTextView = findViewById(R.id.programTV);
        String [] program_itemList = {"B.Tech", "M.Tech"};
        ArrayAdapter<String> program_itemArrayAdapter = new ArrayAdapter<>(AdminPortal.this, R.layout.dropdown_item, program_itemList);
        program_autoCompleteTextView.setAdapter(program_itemArrayAdapter);
        program_autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                program_autoCompleteTextView.setText((String)parent.getItemAtPosition(position));
                program = (String)parent.getItemAtPosition(position);
            }
        });

        year_menuTIL = findViewById(R.id.year_menuTIL);
        year_autoCompleteTextView = findViewById(R.id.yearTV);
        String [] year_itemList = {"1st Year", "2nd Year", "3rd Year","4th Year"};
        ArrayAdapter<String> year_itemArrayAdapter = new ArrayAdapter<>(AdminPortal.this, R.layout.dropdown_item, year_itemList);
        year_autoCompleteTextView.setAdapter(year_itemArrayAdapter);
        year_autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                year_autoCompleteTextView.setText((String)parent.getItemAtPosition(position));
                year = (String)parent.getItemAtPosition(position);
            }
        });

        branch_menuTIL = findViewById(R.id.branch_menuTIL);
        branch_autoCompleteTextView = findViewById(R.id.branchTV);
        String [] branch_itemList = {"CSAI", "CS", "IT", "CSB"};
        ArrayAdapter<String> branch_itemArrayAdapter = new ArrayAdapter<>(AdminPortal.this, R.layout.dropdown_item, branch_itemList);
        branch_autoCompleteTextView.setAdapter(branch_itemArrayAdapter);
        branch_autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                branch_autoCompleteTextView.setText((String)parent.getItemAtPosition(position));
                branch = (String)parent.getItemAtPosition(position);
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