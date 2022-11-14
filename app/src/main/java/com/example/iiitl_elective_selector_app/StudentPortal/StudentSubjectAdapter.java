package com.example.iiitl_elective_selector_app.StudentPortal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iiitl_elective_selector_app.R;

import java.util.ArrayList;
import java.util.Objects;

public class StudentSubjectAdapter  extends RecyclerView.Adapter<StudentSubjectAdapter.SubjectView>{
    ArrayList<String> subjectArrayList = new ArrayList<>();
    ArrayList<String> facultyArrayList = new ArrayList<>();
    Context context;
    boolean selected = false;
    public StudentSubjectAdapter(Context applicationContext, ArrayList<String> subjectArrayList, ArrayList<String>facultyArrayList) {
        this.subjectArrayList = subjectArrayList;
        this.facultyArrayList = facultyArrayList;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public StudentSubjectAdapter.SubjectView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.row_student_subjects,parent, false);
        return new SubjectView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentSubjectAdapter.SubjectView holder, int position) {
        holder.subjectName.setText(subjectArrayList.get(position));
        holder.facultyName.setText(facultyArrayList.get(position));
        selected = false;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selected){
                    selected = true;
                    String subject_name = holder.subjectName.getText().toString();
                    Intent intent = new Intent("Subject Adapter");
                    //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
                    intent.putExtra("subject_name",subject_name);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                    holder.itemView.setBackgroundColor(Color.parseColor("#287323"));

                }else{
                    Toast.makeText(context, "Already selected", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return subjectArrayList.size();
    }
    public class SubjectView extends RecyclerView.ViewHolder {
        TextView subjectName, facultyName;
        public SubjectView(@NonNull View itemView) {
            super(itemView);
            subjectName = Objects.requireNonNull(itemView).findViewById(R.id.subject_name);
            facultyName = itemView.findViewById(R.id.faculty_name);
        }
    }
}
