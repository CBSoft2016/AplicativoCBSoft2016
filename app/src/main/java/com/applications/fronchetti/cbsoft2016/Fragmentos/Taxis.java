package com.applications.fronchetti.cbsoft2016.Fragmentos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.applications.fronchetti.cbsoft2016.Adapters.Taxi;
import com.applications.fronchetti.cbsoft2016.Adapters.TaxiAdapter;
import com.applications.fronchetti.cbsoft2016.Adapters.Turismo;
import com.applications.fronchetti.cbsoft2016.Adapters.TurismoAdapter;
import com.applications.fronchetti.cbsoft2016.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Taxis extends Fragment {

    public Taxis() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taxi, container, false);
        final List<Taxi> Taxis = new ArrayList<Taxi>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("pontos_taxi");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String nome = jo_inside.getString("nome_ponto");
                String endereco = jo_inside.getString("telefone");
                String website = jo_inside.getString("website");
                Taxis.add(new Taxi(nome, endereco, website));

                m_li = new HashMap<String, String>();
                m_li.put("ponto_turistico", nome);
                m_li.put("endereco", endereco);
                m_li.put("website", website);
                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TaxiAdapter adapter = new TaxiAdapter(getActivity(), R.layout.fragment_taxi_item, Taxis);
        ListView Lista = (ListView) view.findViewById(R.id.listViewTaxi);
        Lista.setAdapter(adapter);

        return view;
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("taxis.json");
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
