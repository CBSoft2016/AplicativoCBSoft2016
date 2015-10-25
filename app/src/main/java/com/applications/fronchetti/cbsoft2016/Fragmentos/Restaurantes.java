package com.applications.fronchetti.cbsoft2016.Fragmentos;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applications.fronchetti.cbsoft2016.R;

public class Restaurantes extends Fragment {
    public static Restaurantes newInstance(String param1, String param2) {
        Restaurantes fragment = new Restaurantes();
        return fragment;
    }

    public Restaurantes() {
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
        return inflater.inflate(R.layout.fragment_restaurantes, container, false);
    }
}
