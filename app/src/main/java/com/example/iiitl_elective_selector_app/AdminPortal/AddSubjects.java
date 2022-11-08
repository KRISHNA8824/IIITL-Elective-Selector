package com.example.iiitl_elective_selector_app.AdminPortal;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.iiitl_elective_selector_app.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AddSubjects extends AppCompatActivity implements View.OnClickListener{
    LinearLayout layoutList;
    Button add_button, finish_button;
    ImageView removeImage;
    ArrayList<Elective> electiveArrayList = new ArrayList<>();
    Elective elective;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);
        layoutList = findViewById(R.id.layout_list);

        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(this);

        finish_button = findViewById(R.id.finish_button);
        finish_button.setOnClickListener(this);
        elective = (Elective) getIntent().getExtras().getSerializable("Elective");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.add_button:
                addView();
                break;

            case R.id.finish_button:

                if(checkIfValidAndRead()){
                    Intent intent = new Intent(getApplicationContext(),AdminPortal.class);

                    startActivity(intent);

                }
                break;

        }
    }

    private boolean checkIfValidAndRead() {
        boolean result = true;
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=0;i<layoutList.getChildCount();i++) {

            View subjectView = layoutList.getChildAt(i);
            EditText subject_name = (EditText) subjectView.findViewById(R.id.edit_subject_name);
            if (!subject_name.getText().toString().equals("")) {
                arrayList.add(subject_name.getText().toString());
            }

        }
        if(arrayList.size() == 0){
            result = false;
            Toast.makeText(this,"Add Subjects First!",Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this,"Fill all credentials correctly",Toast.LENGTH_SHORT).show();
        }
        if(result == true && arrayList.size() != 0){
            elective.setSubjectArrayList(arrayList);
        }
        return result;
    }

    private void addView(){
        final View subjectView  = getLayoutInflater().inflate(R.layout.row_add_subjects,null,false);
        EditText editText = (EditText) findViewById(R.id.edit_subject_name);
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