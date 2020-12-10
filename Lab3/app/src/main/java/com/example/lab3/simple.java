package com.example.lab3;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class simple extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    public void onNumberClick(View view)
    {
        MainActivity act = (MainActivity) getActivity();
        act.onNumberClick(view);
    }

    public void onOperationClick(View view)
    {
        MainActivity act = (MainActivity) getActivity();
        act.onOperationClick(view);
    }
}