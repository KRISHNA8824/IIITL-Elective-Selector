package com.example.iiitl_elective_selector_app.AdminPortal;

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

public class AdminSubjectAdapter extends RecyclerView.Adapter<AdminSubjectAdapter.SubjectView>{
    ArrayList<SubjectModel> subjectModelArrayList = new ArrayList<>();
    Context context;

    public AdminSubjectAdapter(Context applicationContext, ArrayList<SubjectModel> subjectModelArrayList) {
        this.context = applicationContext;
        this.subjectModelArrayList = subjectModelArrayList;
    }

    @NonNull
    @Override
    public AdminSubjectAdapter.SubjectView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.row_student_subjects,parent, false);
        return new SubjectView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdminSubjectAdapter.SubjectView holder, int position) {
        SubjectModel subject = subjectModelArrayList.get(position);
//        holder.subjectName.setText(subjectArrayList.get(position));
//        holder.facultyName.setText(facultyArrayList.get(position));
        holder.subjectName.setText(subject.getSubjectName());
        holder.facultyName.setText(subject.getFacultyName());
        int count = Integer.parseInt(subject.getCountofSeat());
        if(count > 0) {
            holder.status.setText("Available : " + count + " seats");
        }
        else {
            holder.status.setText("Unavailable");
        }
    }

    @Override
    public int getItemCount() {
        return subjectModelArrayList.size();
    }
    public class SubjectView extends RecyclerView.ViewHolder {
        TextView subjectName, facultyName, status;
        public SubjectView(@NonNull View itemView) {
            super(itemView);
            subjectName = Objects.requireNonNull(itemView).findViewById(R.id.subject_name);
            facultyName = itemView.findViewById(R.id.faculty_name);
            status = itemView.findViewById(R.id.number_of_seats);

        }
    }
}
