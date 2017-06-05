package com.eguzeler.rest.interceptors;

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

public class UserListInterceptor implements Interceptor{

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Response response = null;

        String jsonString = Constants.USER_LISTS_STRINGS;

        response = new Response.Builder()
                .code(200)
                .message(jsonString)
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .body(ResponseBody.create(MediaType.parse("application/json"), jsonString.getBytes()))
                .addHeader("content-type","application/json")
                .build();

        return response;
    }
}