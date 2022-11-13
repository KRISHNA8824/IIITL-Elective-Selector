package com.example.iiitl_elective_selector_app.StudentPortal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iiitl_elective_selector_app.R;

import java.util.ArrayList;
import java.util.Objects;

public class StudentSubjectAdapter  extends RecyclerView.Adapter<StudentSubjectAdapter.SubjectView>{
    ArrayList<String> subjectArrayList = new ArrayList<>();
    ArrayList<String> facultyArrayList = new ArrayList<>();
    Context context;

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
