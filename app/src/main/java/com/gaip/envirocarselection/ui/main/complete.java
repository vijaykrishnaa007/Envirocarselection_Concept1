package com.gaip.envirocarselection.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.gaip.envirocarselection.R;

public class complete extends AppCompatActivity
{
    RecyclerView recyclerView;
    TextView axles,cc,seats,mass,power;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completedetails);
        axles=findViewById(R.id.axles);
        cc=findViewById(R.id.cc);
        seats=findViewById(R.id.seat);
        mass=findViewById(R.id.kg);
        power=findViewById(R.id.power);
        axles.setText(manuadapter1.vehicledetails.getAxles()+"");
        cc.setText(manuadapter1.vehicledetails.getEngineCapacity()+"");
        seats.setText(manuadapter1.vehicledetails.getSeats()+"");
        mass.setText(manuadapter1.vehicledetails.getMaximumMass()+"");
        power.setText(manuadapter1.vehicledetails.getPower()+"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.one, menu);
        return true;
    }
}
