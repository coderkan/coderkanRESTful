package com.eguzeler.rest.rest;

import com.eguzeler.rest.converters.LoginUser;
import com.eguzeler.rest.converters.UserDetail;
import com.eguzeler.rest.converters.UserList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by root on 03.06.2017.
 */

public interface RetrofitInterface {

    @GET("/login")
    Call<LoginUser> getLoginUser(@Query("username") String username, @Query("password") String password );

    @GET("/userlists")
    Call<UserList> getUserLists();

    @GET("/userdetail")
    Call<UserDetail> getUserDetail(@Query("id") String id);

    @PUT("/userupdate")
    Call<UserDetail> updateUserDetail(@Query("id") String id, @Query("phone") String phone, @Query("address") String address);

}
