package com.eguzeler.rest.rest;

import com.eguzeler.rest.interceptors.LoginInterceptor;
import com.eguzeler.rest.interceptors.UserDetailInceptor;
import com.eguzeler.rest.interceptors.UserInfoUpdateInterceptor;
import com.eguzeler.rest.interceptors.UserListInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 03.06.2017.
 */

public class RetrofitClient {
    public static String BASE_URL = "http://www.erkanguzeler.com";

    public static Retrofit retrofitLogin = null;

    public static Retrofit retrofitUserList = null;

    public static Retrofit retrofitUserDetailInfo = null;

    public static Retrofit retrofitUserDetailUpdate = null;

    public static Retrofit getRetrofitLoginInstance(){
        if(retrofitLogin == null){

            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(new LoginInterceptor())
                    .build();

            Gson gson = new GsonBuilder().setLenient().create();

            retrofitLogin = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitLogin;
    }

    public static Retrofit getRetrofitUserListInstance(){
        if(retrofitUserList == null){

            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(new UserListInterceptor())
                    .build();

            Gson gson = new GsonBuilder().setLenient().create();

            retrofitUserList = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitUserList;
    }

    public static Retrofit getRetrofitUserDetailInfoInstance(){
        if(retrofitUserDetailInfo == null){

            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(new UserDetailInceptor())
                    .build();

            Gson gson = new GsonBuilder().setLenient().create();

            retrofitUserDetailInfo = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitUserDetailInfo;
    }

    public static Retrofit getRetrofitUserDetailUpdateInstance(){
        if(retrofitUserDetailUpdate == null){

            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(new UserInfoUpdateInterceptor())
                    .build();

            Gson gson = new GsonBuilder().setLenient().create();

            retrofitUserDetailUpdate = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitUserDetailUpdate;
    }

}
