package com.applications.fronchetti.cbsoft2016.Fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.applications.fronchetti.cbsoft2016.Atividades.WebViewActivity;
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
        View view = inflater.inflate(R.layout.fragment_palestras, container, false);

        Button botao_teste_chat = (Button) view.findViewById(R.id.button3);

        //TESTANDO WEBVIEW QUE SERA IMPLEMENTADO NO DIÁLOGO!

        botao_teste_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}
