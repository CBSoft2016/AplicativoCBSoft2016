package com.applications.fronchetti.cbsoft2016.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.applications.fronchetti.cbsoft2016.Atividades.MainActivity;
import com.applications.fronchetti.cbsoft2016.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
            ImageView Imagem = (ImageView) convertView.findViewById(R.id.imageRestaurantes);

            Picasso.with(getContext()).load(item.getImagem()).into(Imagem);

            Nome.setText(item.getNome());
            Endereco.setText(item.getEndereco());
            Telefone.setText(item.getTelefone());
        }
        return convertView;
    }
}
