package com.aditya.zibly.network;//Created by aditya on 6/19/2015

import android.graphics.Bitmap;
import android.util.LruCache;

import com.aditya.zibly.application.MyApplication;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton sInstance = null;
    ImageLoader imageLoader;
    RequestQueue mRequestQueue;
    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private LruCache<String, Bitmap> cache = new LruCache<>((int)Runtime.getRuntime().maxMemory()/1024/8);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static VolleySingleton getInstance(){
        if(sInstance==null){
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }
    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}
