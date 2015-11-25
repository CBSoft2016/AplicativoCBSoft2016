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
import android.provider.CalendarContract.Events;
import android.widget.TextView;

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
import java.util.GregorianCalendar;
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
        final List<com.applications.fronchetti.cbsoft2016.Adapters.Minicursos> Minicursos = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("minicursos");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String nome = jo_inside.getString("titulo");
                String horario = jo_inside.getString("horario");
                String data = jo_inside.getString("data");
                String descricao = jo_inside.getString("descricao");
                String localdetrabalho = jo_inside.getString("localtrabalho");
                String local = jo_inside.getString("local");
                String instrutor = jo_inside.getString("instrutor");
                String imagem = jo_inside.getString("imagem");
                String url = jo_inside.getString("url");

                Minicursos.add(new com.applications.fronchetti.cbsoft2016.Adapters.Minicursos(nome, horario, data,
                        descricao, local, instrutor, localdetrabalho, imagem, url));
                m_li = new HashMap<String, String>();
                m_li.put("titulo", nome);
                m_li.put("local", local);
                m_li.put("horario", horario);
                m_li.put("data", data);
                m_li.put("descricao", descricao);
                m_li.put("localtrabalho", localdetrabalho);
                m_li.put("imagem", imagem);
                m_li.put("url", url);
                m_li.put("instrutor", instrutor);
                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MinicursosAdapter adapter = new MinicursosAdapter(getActivity(), R.layout.fragment_minicursos_item, Minicursos);
        ListView Lista = (ListView) view.findViewById(R.id.listView_minicursos);
        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final com.applications.fronchetti.cbsoft2016.Adapters.Minicursos minicursos = Minicursos.get(position);
                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.fragment_minicursos_dialog);

                TextView nome_minicurso = (TextView) dialog.findViewById(R.id.textViewNomeMinicurso);
                nome_minicurso.setText(minicursos.getTitulo());
                TextView local_minicurso = (TextView) dialog.findViewById(R.id.textViewLocal);
                local_minicurso.setText(minicursos.getLocal());
                TextView data_minicurso = (TextView) dialog.findViewById(R.id.textViewData);
                data_minicurso.setText(minicursos.getData());
                TextView palestrante_minicurso = (TextView) dialog.findViewById(R.id.textViewMinistrante);
                palestrante_minicurso.setText(minicursos.getInstrutor());
                TextView descricao_minicurso = (TextView) dialog.findViewById(R.id.textViewDescricao);
                descricao_minicurso.setText(minicursos.getDescricao());

                TextView text_agendar = (TextView) dialog.findViewById(R.id.textViewAgendar);
                text_agendar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String titulo = minicursos.getTitulo();
                        String local = minicursos.getLocal();
                        String descricao = minicursos.getDescricao();

                        String data = minicursos.getData();
                        String[] separated = data.split("-");
                        int ano = Integer.parseInt(separated[0]);
                        int mes = Integer.parseInt(separated[1]);
                        int dia = Integer.parseInt(separated[2]);

                        calendarMinicursos(titulo, local, descricao, ano, mes, dia);
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

    public void calendarMinicursos(String titulo, String local, String descricao, int ano, int mes, int dia){
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
