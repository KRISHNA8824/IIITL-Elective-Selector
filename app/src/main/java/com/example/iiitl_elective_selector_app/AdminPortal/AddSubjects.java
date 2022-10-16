package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.iiitl_elective_selector_app.R;
import com.google.android.material.textfield.TextInputLayout;

public class AddSubjects extends AppCompatActivity {
    LinearLayout layoutList;
    Button add_button;
    ImageView removeImage;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);
        layoutList = findViewById(R.id.layout_list);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });


    }

    private void addView(){
        final View subjectView  = getLayoutInflater().inflate(R.layout.row_add_subjects,null,false);
        EditText editText = (EditText) findViewById(R.id.edit_subject_name);
//        removeImage = (ImageView)findViewById(R.id.image_remove);

//        removeImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                removeView(subjectView);
//            }
//        });
        layoutList.addView(subjectView);
    }
    private void removeView(View view){
        layoutList.removeView(view);
    }
}