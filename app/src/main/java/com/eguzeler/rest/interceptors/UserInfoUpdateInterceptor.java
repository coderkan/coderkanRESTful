package com.eguzeler.rest.interceptors;

import android.net.Uri;

import com.eguzeler.rest.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by root on 04.06.2017.
 */

public class UserInfoUpdateInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Response response = null;

        String jsonString = Constants.USERS_DETAILED_LIST_STRINGS;
        String urlString = chain.request().url().toString();

        Uri uri = Uri.parse(urlString);
        String id = uri.getQueryParameter("id");
        String phone = uri.getQueryParameter("phone");
        String address = uri.getQueryParameter("address");

        String msg = "";

        if(id == null){

            msg = "Not Found User Info";

            response = new Response.Builder()
                    .code(404)
                    .message(msg)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_2)
                    .body(ResponseBody.create(MediaType.parse("application/json"), msg.getBytes()))
                    .addHeader("content-type","application/json")
                    .build();

        }else{
            //userdetailedlists
            JSONObject resObject = null;

            try {

                JSONObject jObject = new JSONObject(jsonString);
                JSONArray jsonArray = jObject.getJSONArray("userdetailedlists");

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    if(object.getString("id").trim().equals(id.trim())){
                        resObject = new JSONObject();
                        resObject.put("id",object.getString("id"));
                        resObject.put("firstname",object.getString("firstname"));
                        resObject.put("lastname",object.getString("lastname"));
                        resObject.put("job",object.getString("job"));
                        resObject.put("phone",phone);
                        resObject.put("address",address);
                        resObject.put("user_avatar",object.getString("user_avatar"));
                        break;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(resObject == null){

                msg = "Not Found User Info";

                response = new Response.Builder()
                        .code(404)
                        .message(msg)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_2)
                        .body(ResponseBody.create(MediaType.parse("application/json"), msg.getBytes()))
                        .addHeader("content-type","application/json")
                        .build();

            }else{

                msg = "Successfully Updated User Info";
                response = new Response.Builder()
                        .code(200)
                        .message(msg)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_2)
                        .body(ResponseBody.create(MediaType.parse("application/json"), resObject.toString().getBytes()))
                        .addHeader("content-type","application/json")
                        .build();

            }
        }

        return response;
    }
}