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

public class PalestrasAdapter extends ArrayAdapter<Palestra> {
    Context context;
    int resource;
    List<Palestra> palestras = new ArrayList<Palestra>();

    public PalestrasAdapter(Context context, int resource, List<Palestra> palestras) {
        super(context, resource, palestras);
        this.resource = resource;
        this.context = context;
        this.palestras = palestras;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        Palestra item = palestras.get(position);

        if(item != null){
            TextView Nome = (TextView) convertView.findViewById(R.id.textPalestraNome);
            TextView Local = (TextView) convertView.findViewById(R.id.textPalestraLocal);
            TextView Instrutor = (TextView) convertView.findViewById(R.id.textPalestraInstrutor);

            Nome.setText(item.getNome());
            Local.setText(item.getLocal());
            Instrutor.setText(item.getInstrutor());
        }
        return convertView;
    }
}
