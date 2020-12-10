package com.example.lab3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class custom extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom, container, false);
    }

    public void onCustomOperationClick(View view)
    {
        MainActivity act = (MainActivity) getActivity();
        act.onCustomOperationClick(view);
    }
}