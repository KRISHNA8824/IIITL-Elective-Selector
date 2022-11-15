package com.example.iiitl_elective_selector_app.AdminPortal;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iiitl_elective_selector_app.R;
import com.example.iiitl_elective_selector_app.StudentModel;
import com.example.iiitl_elective_selector_app.StudentPortal.StudentSubjectList;

import java.util.ArrayList;
import java.util.Objects;

public class ShowUserListAdapter extends RecyclerView.Adapter<ShowUserListAdapter.StudentView> {
    ArrayList<StudentModel> studentArrayList = new ArrayList<>();
    Context context;


    public ShowUserListAdapter(Context applicationContext, ArrayList<StudentModel> studentArrayList) {
       this.context = applicationContext;
       this.studentArrayList = studentArrayList;
    }

    @NonNull
    @Override
    public ShowUserListAdapter.StudentView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.row_show_student,parent, false);
        return new ShowUserListAdapter.StudentView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ShowUserListAdapter.StudentView holder, int position) {
        holder.studentName.setText(studentArrayList.get(position).getStudentName());
        holder.studentEnroment.setText(studentArrayList.get(position).getStudentEnrolment());
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }
    public class StudentView extends RecyclerView.ViewHolder {
        TextView studentName, studentEnroment;
        public StudentView(@NonNull View itemView) {
            super(itemView);
            studentName = Objects.requireNonNull(itemView).findViewById(R.id.student_name);
            studentEnroment = itemView.findViewById(R.id.student_enrolment);
        }
    }

}
