package com.applications.fronchetti.cbsoft2016.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.applications.fronchetti.cbsoft2016.R;

import java.io.InputStream;
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
            new DownloadImageTask((ImageView) convertView.findViewById(R.id.imageRestaurantes)).execute(item.getImagem());

            Nome.setText(item.getNome());
            Endereco.setText(item.getEndereco());
            Telefone.setText(item.getTelefone());
        }
        return convertView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
