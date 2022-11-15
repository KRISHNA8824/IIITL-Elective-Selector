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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class AdminSubjectAdapter extends RecyclerView.Adapter<AdminSubjectAdapter.SubjectView>{
    ArrayList<SubjectModel> subjectModelArrayList = new ArrayList<>();
    DetailsModel detailsModel;
    String electiveID;
    Context context;

    public AdminSubjectAdapter(Context applicationContext, ArrayList<SubjectModel> subjectModelArrayList, DetailsModel detailsModel, String electiveID) {
        this.context = applicationContext;
        this.subjectModelArrayList = subjectModelArrayList;
        this.detailsModel = detailsModel;
        this.electiveID = electiveID;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String program = detailsModel.getNew_program();
                String year = detailsModel.getYear();
                String branch = detailsModel.getBranch();

                //Toast.makeText(context, "hiiiii", Toast.LENGTH_SHORT).show();

                //.child(electiveID).child(holder.subjectName.getText().toString())



                Intent intent = new Intent(context, ShowUserList.class);

//                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child()
//                Toast.makeText(context, detailsModel.new_program + " " + detailsModel.year + " " + detailsModel.branch, Toast.LENGTH_SHORT).show();
                intent.putExtra("Details", detailsModel);
                intent.putExtra("electiveID", electiveID);
                intent.putExtra("subjectID", holder.subjectName.getText().toString());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
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
