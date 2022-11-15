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

import com.example.iiitl_elective_selector_app.AdminPortal.SubjectModel;
import com.example.iiitl_elective_selector_app.R;

import java.util.ArrayList;
import java.util.Objects;

public class StudentSubjectAdapter  extends RecyclerView.Adapter<StudentSubjectAdapter.SubjectView>{
    ArrayList<SubjectModel> subjectModelArrayList = new ArrayList<>();
    Context context;
    public static String message = "";
    String subName;
    boolean selected = false;
    public StudentSubjectAdapter(Context applicationContext, ArrayList<SubjectModel> subjectModelArrayList) {
        this.context = applicationContext;
        this.subjectModelArrayList = subjectModelArrayList;
    }

    @NonNull
    @Override
    public StudentSubjectAdapter.SubjectView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.row_student_subjects,parent, false);
        return new SubjectView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentSubjectAdapter.SubjectView holder, int position) {
        SubjectModel subject = subjectModelArrayList.get(position);
//        holder.subjectName.setText(subjectArrayList.get(position));
//        holder.facultyName.setText(facultyArrayList.get(position));
        subName = subject.getSubjectName();
        holder.subjectName.setText(subject.getSubjectName());
        holder.facultyName.setText(subject.getFacultyName());
        int count = Integer.parseInt(subject.getCountofSeat());
        if(count > 0) {
            holder.status.setText("Available : " + count + " seats");
        }
        else {
            holder.status.setText("Unavailable");
        }
        selected = false;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selected){
                    message = holder.subjectName.getText().toString();
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
