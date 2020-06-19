package com.example.finalprojectgroup8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {

    public ViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_view, container, false);
        Button crte = v.findViewById(R.id.create);
        Button udate = v.findViewById(R.id.update);
        crte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("com.example.finalprojectgroup8", Context.MODE_PRIVATE);
                final Boolean userbool = preferences.getBoolean("asNanny",true);
                if(userbool == true)
                {
                    Intent intent=new Intent(getActivity(),ProfileCreationAsNanny.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(getActivity(),ProfileCreationForNanny.class);
                    startActivity(intent);
                }
            }
        });
        udate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),Availability.class);
                startActivity(intent);
            }
        });



        return  v;
    }
}
