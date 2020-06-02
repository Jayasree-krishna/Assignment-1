package com.example.assignment_1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoad_Image extends AsyncTask<String,Void, Bitmap> {

    Context ct;
    public DownLoad_Image(Context ctx)
    {
        ct=ctx;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        String s1=strings[0];

        InputStream in;
        try {
            URL myurl=new URL(s1);
            HttpURLConnection mycon= (HttpURLConnection) myurl.openConnection();
            mycon.setReadTimeout(10000);
            mycon.setConnectTimeout(10000);
            mycon.setRequestMethod("GET");
            mycon.connect();

            in=mycon.getInputStream();

            Bitmap bitmap= BitmapFactory.decodeStream(in);
            return bitmap;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap){
        MainActivity.imageView.setImageBitmap(bitmap);
    }
}
