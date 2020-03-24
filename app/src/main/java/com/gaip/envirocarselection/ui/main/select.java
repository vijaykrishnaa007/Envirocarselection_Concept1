package com.gaip.envirocarselection.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alespero.expandablecardview.ExpandableCardView;
import com.gaip.envirocarselection.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class select extends Fragment
{
    ExpandableCardView vehicle;
    private List<manufacturer> mExampleList;
    private List<vehicles> mExampleList1;
    AutoCompleteTextView searchmanu;
    private List<vehicledetails> details=new LinkedList<>();
    private RecyclerView mRecyclerView1;
    private manuadapter1 mAdapter1;
    public static select newInstance()
    {
        select fragmentFirst = new select();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserList();

    }

    private void getUserList() {

        try {
            String url = "https://processing.envirocar.org/vehicles/";


            Retrofit retrofit = null;

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }


            api service = retrofit.create(api.class);


            Call<List<manufacturer>> call = service.getUserData();

            call.enqueue(new Callback<List<manufacturer>>() {
                @Override
                public void onResponse(Call<List<manufacturer>> call, Response<List<manufacturer>> response) {

                    mExampleList = response.body();
                    String names[]=new String[mExampleList.size()];int i=0;
                    for(manufacturer temp:mExampleList)
                    {
                        names[i++]=temp.getName();
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (getContext(),android.R.layout.select_dialog_item,names);
                    searchmanu.setAdapter(adapter);
                    searchmanu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            getVehicleList(mExampleList.get(i).getHsn());
                            vehicle.expand();
                            progressBar.setVisibility(View.VISIBLE);
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                        }
                    });
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<List<manufacturer>> call, Throwable t) {
int hy=0;
                }
            });
        }
        catch (Exception r)
        {

        }
    }




    private void filter1(String text) {
        ArrayList<vehicledetails> filteredList = new ArrayList<>();

        for (vehicledetails item : details) {
            if (item.getCommercialName().toLowerCase().contains(text.toLowerCase())) {

                filteredList.add(item);
            }
        }

        mAdapter1.filterList(filteredList);
    }


    private void getVehicleList(final String hsn) {

        try {
            String url = "https://processing.envirocar.org/vehicles/";


            Retrofit retrofit = null;

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }


            vehicleapi service = retrofit.create(vehicleapi.class);


            Call<List<vehicles>> call = service.getUserData(hsn);
            details.clear();
            buildRecyclerView1();
            mAdapter1.notifyDataSetChanged();
            final Retrofit finalRetrofit = retrofit;
            call.enqueue(new Callback<List<vehicles>>() {
                @Override
                public void onResponse(Call<List<vehicles>> call, Response<List<vehicles>> response) {

                    mExampleList1 = response.body();
                    vehicledetailsapi service1 = finalRetrofit.create(vehicledetailsapi.class);
                    int h=10;
                    for(vehicles temp:mExampleList1) {
                        h--;
                        if(h==0)
                            break;
                        Call<vehicledetails> call1 = service1.getUserData(hsn,temp.tsn);
                        call1.enqueue(new Callback<vehicledetails>() {
                            @Override
                            public void onResponse(Call<vehicledetails> call, Response<vehicledetails> response) {
                                details.add(response.body());
                                progressBar.setVisibility(View.GONE);
                                mAdapter1.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<vehicledetails> call, Throwable t) {
int hy=0;
                            }
                        });
                    }

                }

                @Override
                public void onFailure(Call<List<vehicles>> call, Throwable t) {
                    int hy=0;
                }
            });
        }
        catch (Exception r)
        {
        int h=0;
        }
    }
ProgressBar progressBar;
                @Override
                public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                                         Bundle savedInstanceState) {
                    View view = inflater.inflate(R.layout.rest, container, false);
                    mRecyclerView1 = view.findViewById(R.id.manurecycle1);
                    vehicle=view.findViewById(R.id.vehicle);
                    searchmanu = view.findViewById(R.id.searchmanu);
                    progressBar=view.findViewById(R.id.progress);
                    return view;
                }












    private void buildRecyclerView1() {

        mAdapter1 = new manuadapter1(details);
        mRecyclerView1.setLayoutManager(new GridLayoutManager(mRecyclerView1.getContext(),2));
        mRecyclerView1.setAdapter(mAdapter1);

    }


}
