package com.applications.fronchetti.cbsoft2016.Fragmentos;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.applications.fronchetti.cbsoft2016.Adapters.Hotel;
import com.applications.fronchetti.cbsoft2016.Adapters.HotelAdapter;
import com.applications.fronchetti.cbsoft2016.Adapters.MinicursosAdapter;
import com.applications.fronchetti.cbsoft2016.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Minicursos extends Fragment {
    public static Minicursos newInstance() {
        Minicursos fragment = new Minicursos();
        return fragment;
    }

    public Minicursos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_minicursos, container, false);
        List<com.applications.fronchetti.cbsoft2016.Adapters.Minicursos> Minicursos = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("minicursos");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String nome = jo_inside.getString("titulo");
                String local = jo_inside.getString("local");
                String instrutor = jo_inside.getString("instrutor");

                Minicursos.add(new com.applications.fronchetti.cbsoft2016.Adapters.Minicursos(nome, local, instrutor));
                m_li = new HashMap<String, String>();
                m_li.put("titulo", nome);
                m_li.put("local", local);
                m_li.put("instrutor", instrutor);
                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MinicursosAdapter adapter = new MinicursosAdapter(getActivity(), R.layout.fragment_minicursos_item, Minicursos);
        ListView Lista = (ListView) view.findViewById(R.id.listView_minicursos);
        Lista.setAdapter(adapter);
        return view;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("minicursos.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
