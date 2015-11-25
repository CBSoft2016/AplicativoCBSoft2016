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

public class RestauranteAdapter extends ArrayAdapter<Restaurante>{
    Context context;
    int resource;
    List<Restaurante> restaurantes = new ArrayList<Restaurante>();

    public RestauranteAdapter(Context context, int resource, List<Restaurante> restaurantes) {
        super(context, resource, restaurantes);
        this.context = context;
        this.resource = resource;
        this.restaurantes = restaurantes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        Restaurante item = restaurantes.get(position);

        if(item != null){
            TextView Nome = (TextView) convertView.findViewById(R.id.textRestaurantesNome);
            TextView Endereco = (TextView) convertView.findViewById(R.id.textRestaurantesEndereco);
            TextView Telefone = (TextView) convertView.findViewById(R.id.textRestaurantesTelefone);

            Nome.setText(item.getNome());
            Endereco.setText(item.getEndereco());
            Telefone.setText(item.getTelefone());
        }
        return convertView;
    }
}
