package com.example.iiitl_elective_selector_app.AdminPortal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
    Context context;

    public ElectiveAdapter(Context applicationContext, ArrayList<Elective> electiveList) {
        this.electiveList = electiveList;
        this.context = applicationContext;
    }


    @NonNull
    @Override
    public ElectiveAdapter.ElectiveView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person,parent, false);
        return new ElectiveView(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ElectiveAdapter.ElectiveView holder, @SuppressLint("RecyclerView") int position) {
        holder.electiveNumber.setText("Elective "+ String.valueOf(position+1));
        Elective elective = electiveList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminSubjectList.class);

                intent.putExtra("elective",elective);
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
            electiveNumber = (TextView) itemView.findViewById(R.id.elective_numberTV);
        }
    }
}
