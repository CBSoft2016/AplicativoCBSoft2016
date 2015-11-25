package com.applications.fronchetti.cbsoft2016.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
        configureDefaultImageLoader(getContext());
        this.context = context;
        this.resource = resource;
        this.restaurantes = restaurantes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.common_ic_googleplayservices)
                .showImageForEmptyUri(R.drawable.common_ic_googleplayservices)
                .showImageOnFail(R.drawable.common_full_open_on_phone)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoader loader = ImageLoader.getInstance();
        Restaurante item = restaurantes.get(position);

        if(item != null){
            TextView Nome = (TextView) convertView.findViewById(R.id.textRestaurantesNome);
            TextView Endereco = (TextView) convertView.findViewById(R.id.textRestaurantesEndereco);
            TextView Telefone = (TextView) convertView.findViewById(R.id.textRestaurantesTelefone);
            ImageView Imagem = (ImageView) convertView.findViewById(R.id.imageRestaurantes);

            String caminho = DownloadImage(item);
            new DisplayTask(caminho,Imagem);
            Nome.setText(item.getNome());
            Endereco.setText(item.getEndereco());
            Telefone.setText(item.getTelefone());
        }
        return convertView;
    }

    public class DisplayTask extends AsyncTask<Void, Void, Void>{
        String url;
        ImageView imagem;

        public DisplayTask(String url,ImageView imagem){
            this.imagem = imagem;
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
        }

        @Override
        protected Void doInBackground(Void... params) {
            Picasso.with(getContext()).load(url).into(imagem);
            return null;
        }
    }

    public String DownloadImage(final Restaurante item){
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        File file = new File(
                                Environment.getExternalStorageDirectory().getPath()
                                        + "/" + item.getNome().toLowerCase().replaceAll("[\\s_]","") + ".jpg");
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,ostream);
                            ostream.close();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {}

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {}
        };

        Picasso.with(getContext()).load(item.getImagem()).into(target);
        return Environment.getExternalStorageDirectory().getPath() + "/" + item.getNome().toLowerCase().replaceAll("[\\s_]","") + ".jpg";
    }

    public static void configureDefaultImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        ImageLoader.getInstance().init(config);
    }
}
