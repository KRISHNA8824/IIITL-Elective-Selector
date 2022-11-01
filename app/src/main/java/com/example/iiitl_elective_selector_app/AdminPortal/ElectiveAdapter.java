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

public class ElectiveAdapter extends RecyclerView.Adapter<ElectiveAdapter.ElectiveView>{
    ArrayList<Elective> electiveList = new ArrayList<>();

    public ElectiveAdapter(ArrayList<Elective> electiveList) {
        this.electiveList = electiveList;
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
//        if(arrayList.size() != 0){
//            holder.subjectsName.setText(arrayList.get(0));
//        }else{
//            holder.subjectsName.setText("Subject Name");
//        }


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
