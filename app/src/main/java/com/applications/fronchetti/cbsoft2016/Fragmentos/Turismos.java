package com.applications.fronchetti.cbsoft2016.Fragmentos;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.applications.fronchetti.cbsoft2016.Adapters.Restaurante;
import com.applications.fronchetti.cbsoft2016.Adapters.RestauranteAdapter;
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

public class Turismos extends Fragment {

    public Turismos() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_turismo, container, false);
        final List<Turismo> Turismos = new ArrayList<Turismo>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("pontos_turisticos");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String nome = jo_inside.getString("ponto_turistico");
                String endereco = jo_inside.getString("endereco");
                String telefone = jo_inside.getString("telefone");
                String imagem = jo_inside.getString("imagem");
                String website = jo_inside.getString("website");
                System.out.println(endereco + telefone + imagem + website);
                Turismos.add(new Turismo(nome, endereco, telefone, imagem, website));

                m_li = new HashMap<String, String>();
                m_li.put("ponto_turistico", nome);
                m_li.put("endereco", endereco);
                m_li.put("telefone", telefone);
                m_li.put("imagem", imagem);
                m_li.put("website", website);
                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TurismoAdapter adapter = new TurismoAdapter(getActivity(), R.layout.fragment_turismo_item, Turismos);
        ListView Lista = (ListView) view.findViewById(R.id.listViewTurismo);
        Lista.setAdapter(adapter);

        return view;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("turismos.json");
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

