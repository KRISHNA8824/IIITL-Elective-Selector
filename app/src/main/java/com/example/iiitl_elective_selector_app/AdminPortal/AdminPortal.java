package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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

import com.example.iiitl_elective_selector_app.MainActivity;
import com.example.iiitl_elective_selector_app.R;
import com.example.iiitl_elective_selector_app.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Objects;

public class AdminPortal extends AppCompatActivity {

    ImageView logout;
    Button next_button;
    AppCompatSpinner spinner_program,spinner_year, spinner_branch;
    FirebaseAuth mAuth;
    String program , branch, year ;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

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