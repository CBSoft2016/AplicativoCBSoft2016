package com.applications.fronchetti.cbsoft2016.Fragmentos;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applications.fronchetti.cbsoft2016.R;

public class Palestras extends Fragment {
    public static Palestras newInstance(String param1, String param2) {
        Palestras fragment = new Palestras();
        return fragment;
    }

    public Palestras() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_palestras, container, false);
    }
}
