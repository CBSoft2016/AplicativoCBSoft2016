package com.applications.fronchetti.cbsoft2016.Fragmentos;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.provider.CalendarContract.Events;

import com.applications.fronchetti.cbsoft2016.Adapters.Palestra;
import com.applications.fronchetti.cbsoft2016.Adapters.PalestrasAdapter;
import com.applications.fronchetti.cbsoft2016.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Palestras extends Fragment {
    public static Palestras newInstance() {
        Palestras fragment = new Palestras();
        return fragment;
    }

    public Palestras() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_palestras, container, false);
        final List<Palestra> Palestras = new ArrayList<Palestra>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("palestras");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String nome = jo_inside.getString("titulo");
                String local = jo_inside.getString("local");
                String palestrante = jo_inside.getString("palestrante");
                String data = jo_inside.getString("data");
                String descricao = jo_inside.getString("descricao");
                String trabalho = jo_inside.getString("trabalho");
                String url = jo_inside.getString("url");
                String imagem = jo_inside.getString("imagem");
                String horario = jo_inside.getString("horario");

                Palestras.add(new Palestra(nome, local, palestrante, descricao, horario, trabalho, imagem, url, data));

                m_li = new HashMap<String, String>();
                m_li.put("titulo", nome);
                m_li.put("local", local);
                m_li.put("palestrante", palestrante);
                m_li.put("data", data);
                m_li.put("descricao", descricao);
                m_li.put("trabalho", trabalho);
                m_li.put("url", url);
                m_li.put("imagem", imagem);
                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PalestrasAdapter adapter = new PalestrasAdapter(getActivity(), R.layout.fragment_palestras_item, Palestras);
        ListView Lista = (ListView) view.findViewById(R.id.listViewPalestras);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Palestra palestra = Palestras.get(position);

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.fragment_palestras_dialog);

                TextView nome_palestra = (TextView) dialog.findViewById(R.id.textViewNomeMinicurso);
                nome_palestra.setText(palestra.getNome());
                TextView local_palestra = (TextView) dialog.findViewById(R.id.textViewLocal);
                local_palestra.setText(palestra.getLocal());
                TextView data_palestra = (TextView) dialog.findViewById(R.id.textViewData);
                data_palestra.setText(palestra.getData());
                TextView palestrante_palestra = (TextView) dialog.findViewById(R.id.textViewPalestrante);
                palestrante_palestra.setText(palestra.getInstrutor());
                TextView descricao_palestra = (TextView) dialog.findViewById(R.id.textViewDescricao);
                descricao_palestra.setText(palestra.getDescricao());

                TextView text_agendar = (TextView) dialog.findViewById(R.id.textViewAgendar);
                text_agendar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String titulo = palestra.getNome();
                        String local = palestra.getLocal();
                        String descricao = palestra.getDescricao();

                        String data = palestra.getData();
                        String[] separated = data.split("-");
                        int ano = Integer.parseInt(separated[0]);
                        int mes = Integer.parseInt(separated[1]);
                        int dia = Integer.parseInt(separated[2]);

                        calendarPalestras(titulo, local, descricao, ano, dia, mes);
                    }
                });
                dialog.show();

            }
        });
        Lista.setAdapter(adapter);
        return view;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("palestras.json");
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

    public void calendarPalestras(String titulo, String local, String descricao, int ano, int dia, int mes){
        Intent intent_calendar = new Intent(Intent.ACTION_INSERT);
        intent_calendar.setData(CalendarContract.Events.CONTENT_URI);

        //Configurações do evento.
        intent_calendar.putExtra(Events.TITLE, titulo);
        intent_calendar.putExtra(Events.EVENT_LOCATION, local);
        intent_calendar.putExtra(Events.DESCRIPTION, descricao);

        //Configuração de data do evento
        GregorianCalendar calDate = new GregorianCalendar(ano, dia, mes);
        intent_calendar.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                calDate.getTimeInMillis());
        intent_calendar.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                calDate.getTimeInMillis());

        startActivity(intent_calendar);
    }
}
