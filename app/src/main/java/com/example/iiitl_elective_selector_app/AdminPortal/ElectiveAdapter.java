package com.example.iiitl_elective_selector_app.AdminPortal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iiitl_elective_selector_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ElectiveAdapter extends RecyclerView.Adapter<ElectiveAdapter.ElectiveView>{
    ArrayList<Elective> electiveList = new ArrayList<>();
    Context context;
    String program,year,branch;
    HashMap<Integer, String> map = new HashMap<>();
    public ElectiveAdapter(Context applicationContext, ArrayList<Elective> electiveList, HashMap<Integer, String> map, String new_program, String year, String branch) {
        this.electiveList = electiveList;
        this.context = applicationContext;
        this.map = map;
        this.program = new_program;
        this.year = year;
        this.branch = branch;
    }


    @NonNull
    @Override
    public ElectiveAdapter.ElectiveView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_add_elective,parent, false);
        return new ElectiveView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ElectiveAdapter.ElectiveView holder, @SuppressLint("RecyclerView") int position) {
        holder.electiveNumber.setText("Elective "+ String.valueOf(position+1));
        Elective elective = electiveList.get(position);
        holder.electiveNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminSubjectList.class);

                intent.putExtra("elective",elective);

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



        holder.deleteElectiveTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new AlertDialog.Builder(context)
//                        .setTitle("Delete!")
//                        .setMessage("Are you sure?")
//                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
////                                String electiveID = map.get(position+1);
////                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Electives").child(program).child(year).child(branch);
////                                reference.child(electiveID).removeValue();
////                                Toast.makeText(context, "Elective is Successfully Removed", Toast.LENGTH_SHORT).show();
////                                HashMap<Integer,String> new_map = new HashMap<>();
////                                for (Integer pos : map.keySet()){
////                                    if(pos != position+1){
////                                        new_map.put(pos,map.get(pos));
////                                    }
////
////                                }
////                                map = new_map;
//
//                            }
//                        })
//                        .setNegativeButton("Cancel", null)
//                        .show();
                        String electiveID = map.get(position+1);
//                Toast.makeText(context, electiveID, Toast.LENGTH_SHORT).show();
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Electives").child(program).child(year).child(branch);
                        reference.child(electiveID).removeValue();
                        Toast.makeText(context, "Elective is Successfully Removed", Toast.LENGTH_SHORT).show();
                        HashMap<Integer,String> new_map = new HashMap<>();
                        for (Integer pos : map.keySet()){
                            if(pos != position+1){
                                new_map.put(pos,map.get(pos));
                            }

                        }
                        map = new_map;

                  Intent intent = new Intent(context, FloatElective.class);
                  String program1 = program.substring(0,1) + "." + program.substring(1);
                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  intent.putExtra("program", program1);
                  intent.putExtra("year", year);
                  intent.putExtra("branch", branch);
                  context.startActivity(intent);
                  ((Activity)context).finish();

            }
        });

        holder.floatElectiveTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String electiveID = map.get(position+1);
//                Toast.makeText(context, electiveID, Toast.LENGTH_SHORT).show();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Electives").child(program).child(year).child(branch);
                reference.child(electiveID).child("status").setValue("Floated");
                Toast.makeText(context, "Elective is Successfully Floated", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, FloatElective.class);
                String program1 = program.substring(0,1) + "." + program.substring(1);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("program", program1);
                intent.putExtra("year", year);
                intent.putExtra("branch", branch);
                context.startActivity(intent);
                ((Activity)context).finish();

            }
        });

    }

    @Override
    public int getItemCount() {
        return electiveList.size();
    }
    public class ElectiveView extends RecyclerView.ViewHolder {
        TextView electiveNumber, deleteElectiveTV,floatElectiveTV;
        public ElectiveView(@NonNull View itemView) {
            super(itemView);
            electiveNumber = (TextView) itemView.findViewById(R.id.elective_numberTV);
            deleteElectiveTV = (TextView) itemView.findViewById(R.id.delete_electiveTV);
            floatElectiveTV = (TextView) itemView.findViewById(R.id.float_electiveTV);

        }
    }
}
