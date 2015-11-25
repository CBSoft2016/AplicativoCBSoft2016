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
import android.widget.Button;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        Lista.setAdapter(adapter);

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

                Button button_agendar = (Button) dialog.findViewById(R.id.f_agendar_button);
                button_agendar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String titulo = palestra.getNome();
                        String local = palestra.getLocal();
                        String descricao = palestra.getDescricao();

                        String date = palestra.getData();
                        String[] separated_date = date.split("-");
                        int ano = Integer.parseInt(separated_date[0]);
                        int mes = Integer.parseInt(separated_date[1]);
                        int dia = Integer.parseInt(separated_date[2]);

                        String hour = palestra.getHorario();
                        String [] separated_hour = hour.split(":");
                        int hours = Integer.parseInt(separated_hour[0]);
                        int minutes = Integer.parseInt(separated_hour[1]);

                        calendarPalestras(titulo, local, descricao, ano, dia, mes, hours, minutes);
                    }
                });
                dialog.show();

            }
        });
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

    public void calendarPalestras(String titulo, String local, String descricao, int ano, int dia, int mes,
                                  int horas, int minutos){

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(ano, mes-1, dia, horas, minutos);
        Calendar endtime = Calendar.getInstance();
        endtime.set(ano, mes - 1, dia, horas + 1, minutos);

        Intent intent_calendar = new Intent(Intent.ACTION_INSERT);
        intent_calendar.setData(Events.CONTENT_URI);

        //Configurações do evento.
        intent_calendar.putExtra(Events.TITLE, titulo);
        intent_calendar.putExtra(Events.EVENT_LOCATION, local);
        intent_calendar.putExtra(Events.DESCRIPTION, descricao);
        intent_calendar.putExtra(Events.ACCOUNT_NAME, "");

        intent_calendar.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                beginTime.getTimeInMillis());
        intent_calendar.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                endtime.getTimeInMillis());

        startActivity(intent_calendar);
    }
}
