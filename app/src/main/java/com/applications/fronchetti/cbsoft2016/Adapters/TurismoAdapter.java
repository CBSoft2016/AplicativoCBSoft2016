package com.applications.fronchetti.cbsoft2016.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applications.fronchetti.cbsoft2016.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TurismoAdapter extends ArrayAdapter<Turismo>{
    Context context;
    int resource;
    List<Turismo> turismos = new ArrayList<Turismo>();

    public TurismoAdapter(Context context, int resource, List<Turismo> turismos) {
        super(context, resource, turismos);
        this.context = context;
        this.resource = resource;
        this.turismos = turismos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        Turismo item = turismos.get(position);

        if(item != null){
            TextView Nome = (TextView) convertView.findViewById(R.id.textTurismoNome);
            TextView Telefone = (TextView) convertView.findViewById(R.id.textTurismoTelefone);
            TextView Endereco = (TextView) convertView.findViewById(R.id.textTurismoEndereco);
            ImageView Imagem = (ImageView) convertView.findViewById(R.id.imageView_turismo);
            Picasso.with(getContext()).load(item.getImagem()).into(Imagem);

            Nome.setText(item.getNome());
            Telefone.setText(item.getTelefone());
            Endereco.setText(item.getEndereco());
        }
        return convertView;
    }
}
