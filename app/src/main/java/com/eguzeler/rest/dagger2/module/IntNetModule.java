package com.eguzeler.rest.dagger2.module;

import com.eguzeler.rest.interceptors.LoginInterceptor;
import com.eguzeler.rest.rest.RetrofitInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by coderkan on 13.07.2017.
 */

@Module
public class IntNetModule {
    private String mBaseURL;
    private Interceptor mInterceptor;

    public IntNetModule(String baseURL, Interceptor interceptor){
        this.mBaseURL = baseURL;
        this.mInterceptor = interceptor;
    }

    @Provides
    @Singleton
    Interceptor proveInterceptor(){
        return this.mInterceptor; //new LoginInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(Interceptor interceptor){
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
        return client;
    }

    @Singleton
    @Provides
    Gson provideGson(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Gson gson){
        return new Retrofit.Builder()
                .baseUrl(mBaseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    @Provides
    @Singleton
    RetrofitInterface provideRetrofitInterface(Retrofit retrofit){
        return retrofit.create(RetrofitInterface.class);
    }

}
