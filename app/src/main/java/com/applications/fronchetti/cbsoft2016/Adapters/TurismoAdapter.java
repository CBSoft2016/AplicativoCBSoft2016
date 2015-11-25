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
            Bitmap bitmap = getBitmap(item.getImagem());
            String image_path = saveStorage(bitmap, item.getNome().toLowerCase());
            new loadStorage(Imagem,image_path).execute();

            Nome.setText(item.getNome());
            Telefone.setText(item.getTelefone());
            Endereco.setText(item.getEndereco());
        }
        return convertView;
    }

    private Bitmap getBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    private String saveStorage(Bitmap bitmapImage, String file_name){
        ContextWrapper cw = new ContextWrapper(getContext());
        File directory = cw.getDir("Images", Context.MODE_PRIVATE);
        File path = new File(directory,file_name + ".jpg");
        FileOutputStream Output = null;
        try {
            Output = new FileOutputStream(path);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, Output);
            Output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }

    class loadStorage extends AsyncTask<Object, Void, Bitmap>{

        private ImageView imv;
        private String path;

        public loadStorage(ImageView image, String path) {
            this.imv = image;
            this.path = path;
        }

        @Override
        protected Bitmap doInBackground(Object... params) {
            Bitmap bitmap = null;
            File file = new File(
                    Environment.getExternalStorageDirectory().getAbsolutePath() + path);

            if(file.exists()){
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            if(result != null && imv != null){
                imv.setVisibility(View.VISIBLE);
                imv.setImageBitmap(result);
            }else{
                imv.setVisibility(View.GONE);
            }
        }
    }
}
