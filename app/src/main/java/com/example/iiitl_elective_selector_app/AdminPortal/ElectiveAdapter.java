package com.example.iiitl_elective_selector_app.AdminPortal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iiitl_elective_selector_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class ElectiveAdapter extends RecyclerView.Adapter<ElectiveAdapter.ElectiveView>{
    ArrayList<Elective> electiveList = new ArrayList<>();
    Context context;

    public ElectiveAdapter(Context applicationContext, ArrayList<Elective> electiveList) {
        this.electiveList = electiveList;
        this.context = applicationContext;

    }

    @NonNull
    @Override
    public ElectiveAdapter.ElectiveView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_electives,parent, false);
        return new ElectiveView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ElectiveAdapter.ElectiveView holder, int position) {
        final Elective elective  = electiveList.get(position);
        holder.electiveNumber.setText("Elective "+ String.valueOf(position+1));

        ArrayList<String> arrayList = elective.getSubjectArrayList();
         for(int i=0;i<arrayList.size();i++){
             String s = arrayList.get(i);
             holder.subjectsName.append(s);
             holder.subjectsName.append("\n");

         }


    }

    @Override
    public int getItemCount() {
        return electiveList.size();
    }
    public class ElectiveView extends RecyclerView.ViewHolder {
        TextView subjectsName,electiveNumber;
        public ElectiveView(@NonNull View itemView) {
            super(itemView);
            subjectsName = (TextView) itemView.findViewById(R.id.subjects_nameTV);
            electiveNumber = (TextView) itemView.findViewById(R.id.elective_numberTV);
        }
    }
}
