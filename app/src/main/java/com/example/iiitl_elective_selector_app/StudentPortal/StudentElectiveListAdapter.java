package com.example.iiitl_elective_selector_app.StudentPortal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iiitl_elective_selector_app.AdminPortal.AdminSubjectList;
import com.example.iiitl_elective_selector_app.AdminPortal.DetailsModel;
import com.example.iiitl_elective_selector_app.AdminPortal.Elective;
import com.example.iiitl_elective_selector_app.AdminPortal.ElectiveAdapter;
import com.example.iiitl_elective_selector_app.AdminPortal.FloatElective;
import com.example.iiitl_elective_selector_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentElectiveListAdapter extends RecyclerView.Adapter<StudentElectiveListAdapter.ElectiveView>{
    ArrayList<Elective> electiveList = new ArrayList<>();
    Context context;
    DetailsModel detailsModel;
    HashMap<Integer, String> map = new HashMap<>();
//    String recieverSubject;
    public StudentElectiveListAdapter(Context applicationContext, ArrayList<Elective> electiveList,  HashMap<Integer, String> map, DetailsModel detailsModel) {
        this.electiveList = electiveList;
        this.context = applicationContext;
        this.detailsModel = detailsModel;
        this.map = map;

    }

//    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // Get extra data included in the Intent
//            String ItemName = intent.getStringExtra("item");
//            String qty = intent.getStringExtra("quantity");
//            Toast.makeText(context,ItemName +" "+qty ,Toast.LENGTH_SHORT).show();
//        }
//    };


    @NonNull
    @Override
    public StudentElectiveListAdapter.ElectiveView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_student_elective,parent, false);
        return new ElectiveView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentElectiveListAdapter.ElectiveView holder, @SuppressLint("RecyclerView") int position) {

        holder.electiveNumber.setText("Elective "+ String.valueOf(position+1));
        holder.electiveNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentSubjectList.class);


                String electiveID = map.get(position+1);
                intent.putExtra("Details", detailsModel);
                intent.putExtra("Position", electiveID);
//                Toast.makeText(context, detailsModel.getProgram() + " " + detailsModel.getYear() + " " + detailsModel.getBranch() + " " + detailsModel.getNew_program(), Toast.LENGTH_SHORT).show();
//
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return electiveList.size();
    }
    public class ElectiveView extends RecyclerView.ViewHolder {

        TextView electiveNumber;
        public ElectiveView(@NonNull View itemView) {
            super(itemView);
            electiveNumber = itemView.findViewById(R.id.elective_numberTV);

        }
    }
}
