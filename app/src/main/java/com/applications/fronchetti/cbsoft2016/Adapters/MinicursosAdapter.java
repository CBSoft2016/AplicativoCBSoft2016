package com.applications.fronchetti.cbsoft2016.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.applications.fronchetti.cbsoft2016.R;

import java.util.ArrayList;
import java.util.List;

public class MinicursosAdapter extends ArrayAdapter<Minicursos>{
    Context context;
    int resource;
    List<Minicursos> minicursos = new ArrayList<>();

    public MinicursosAdapter(Context context, int resource, List<Minicursos> minicursos) {
        super(context, resource, minicursos);
        this.resource = resource;
        this.context = context;
        this.minicursos = minicursos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        Minicursos item = minicursos.get(position);

        if(item != null){
            TextView Nome = (TextView) convertView.findViewById(R.id.textMinicursoNome);
            TextView Local = (TextView) convertView.findViewById(R.id.textMinicursoLocal);
            TextView Instrutor = (TextView) convertView.findViewById(R.id.textMinicursoInstrutor);

            Nome.setText(item.getNome());
            Local.setText(item.getLocal());
            Instrutor.setText(item.getInstrutor());
        }
        return convertView;
    }

}
