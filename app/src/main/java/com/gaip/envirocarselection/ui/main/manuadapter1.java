package com.gaip.envirocarselection.ui.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gaip.envirocarselection.R;

import java.util.ArrayList;
import java.util.List;

public class manuadapter1 extends RecyclerView.Adapter<manuadapter1.ExampleViewHolder>
{
    static  vehicledetails vehicledetails;
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    private List<vehicledetails> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView name,date;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
        }
    }

    public manuadapter1(List<vehicledetails> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle,
                parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        final vehicledetails currentItem = mExampleList.get(position);
        holder.name.setText(currentItem.getLinks().get(0).getTitle());
        holder.date.setText(currentItem.getEngineCapacity()+" CC");
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manuadapter1.vehicledetails=currentItem;
                view.getContext().startActivity(new Intent(view.getContext(),complete.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void filterList(ArrayList<vehicledetails> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }
}
