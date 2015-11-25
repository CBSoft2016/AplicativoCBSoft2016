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

/**
 * Created by fronchetti on 25/11/15.
 */
public class TaxiAdapter extends ArrayAdapter<Taxi>{
    Context context;
    int resource;
    List<Taxi> taxis = new ArrayList<Taxi>();

    public TaxiAdapter(Context context, int resource, List<Taxi> taxis) {
        super(context, resource, taxis);
        this.context = context;
        this.resource = resource;
        this.taxis = taxis;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        Taxi item = taxis.get(position);

        if(item != null){
            TextView Nome = (TextView) convertView.findViewById(R.id.textTaxiNome);
            TextView Telefone = (TextView) convertView.findViewById(R.id.textTaxiTelefone);

            Nome.setText(item.getNome());
            Telefone.setText(item.getTelefone());
        }
        return convertView;
    }
}
