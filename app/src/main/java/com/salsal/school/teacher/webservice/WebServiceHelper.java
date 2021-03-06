package com.salsal.school.teacher.webservice;

import android.content.Context;


import com.salsal.school.teacher.R;
import com.salsal.school.teacher.interfaces.ApiInterface;
import com.salsal.school.teacher.utils.PreferenceManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Kasper on 11/16/2015.
 */
public class WebServiceHelper {

    private WebServiceHelper() {
    }

    public static ApiInterface get(String url) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                //  .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }

    public static ApiInterface get(Context context) {
        OkHttpClient okClient = new OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PreferenceManager.getUrl() != null ? PreferenceManager.getUrl() : context.getString(R .string.BASE_URL))
                //  .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }


}

