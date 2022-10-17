package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.iiitl_elective_selector_app.R;

import java.util.ArrayList;

public class FloatElective extends AppCompatActivity {
    TextView addElectiveTV, programTV,yearTV,branchTV;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_elective);
        addElectiveTV = findViewById(R.id.addElectiveTV);
        addElectiveTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddSubjects.class));
            }
        });

        Intent intent = getIntent();
        String[] dataString = intent.getStringArrayExtra("stringArray");
        programTV = findViewById(R.id.programTV);
        yearTV = findViewById(R.id.yearTV);
        branchTV = findViewById(R.id.branchTV);
        programTV.setText("Program:  " + dataString[0]);
        yearTV.setText("Year:  " + dataString[1]);
        branchTV.setText("Program:  " + dataString[2]);


    }
}