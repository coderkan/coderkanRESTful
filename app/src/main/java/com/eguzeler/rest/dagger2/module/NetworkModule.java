package com.eguzeler.rest.dagger2.module;

import com.eguzeler.rest.interceptors.LoginInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 06.06.2017.
 */

@Module
public class NetworkModule {

    //private String BASE_URL = "http://www.erkanguzeler.com";
    private String mBaseURL;

    public NetworkModule(String baseURL){
        this.mBaseURL = baseURL;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){

        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(new LoginInterceptor())
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(mBaseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

}
