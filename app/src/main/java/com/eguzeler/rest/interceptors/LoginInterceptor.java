package com.eguzeler.rest.interceptors;

import android.net.Uri;

import com.eguzeler.rest.Constants;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by root on 03.06.2017.
 */

public class LoginInterceptor implements Interceptor{
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Response response = null;
        String jsonString = Constants.LOGIN_USER_STRINGS;
        String urlString = chain.request().url().toString();
        Uri uri = Uri.parse(urlString);
        String username = uri.getQueryParameter("username");
        String password = uri.getQueryParameter("password");

        // if Correct User build Correct Response
        if(username.equals("coderkan") && password.equals("1234")){

            response = new Response.Builder()
                    .code(200)
                    .message(jsonString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_2)
                    .body(ResponseBody.create(MediaType.parse("application/json"), jsonString.getBytes()))
                    .addHeader("content-type","application/json")
                    .build();

        }else if(username.equals("coderkan") && !password.equals("1234")){

            String msg ="Password Incorrect";

            response = new Response.Builder()
                    .code(404)
                    .message(msg)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_2)
                    .body(ResponseBody.create(MediaType.parse("application/json"), msg.getBytes()))
                    .addHeader("content-type","application/json")
                    .build();

        }else{

            String msg = "Username or Password incorrect";

            response = new Response.Builder()
                    .code(404)
                    .message(msg)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_2)
                    .body(ResponseBody.create(MediaType.parse("application/json"), msg.getBytes()))
                    .addHeader("content-type","application/json")
                    .build();

        }

        return response;

    }
}

