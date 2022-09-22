package com.example.iiitl_elective_selector_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    CardView admin, student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = (CardView) findViewById(R.id.admin);
        student = (CardView) findViewById(R.id.student);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, StudentPortal.class));
        }
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginAdmin.class);
                startActivity(intent);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });


    }
}