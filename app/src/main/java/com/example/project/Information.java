package com.example.project;


import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class Information extends Fragment
{
 TextView seemore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_information, container, false);
        seemore=v.findViewById(R.id.seemore);
        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d=new Dialog(getActivity());
                d.setContentView(R.layout.timing);
                d.setCanceledOnTouchOutside(false);
                TextView timing=d.findViewById(R.id.timing);
                d.dismiss();
                d.show();
            }
        });

        return v;
    }

}
