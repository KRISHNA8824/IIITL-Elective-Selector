package com.example.iiitl_elective_selector_app.AdminPortal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.iiitl_elective_selector_app.R;

import java.util.ArrayList;

public class ShowElectives extends AppCompatActivity {
    RecyclerView show_electiveRecyclerView;
    ArrayList<Elective> electiveList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_electives);

        show_electiveRecyclerView = findViewById(R.id.show_electiveRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        show_electiveRecyclerView.setLayoutManager(layoutManager);

        electiveList = (ArrayList<Elective>) getIntent().getExtras().getSerializable("list");

        show_electiveRecyclerView.setAdapter(new ElectiveAdapter(electiveList));
    }
}