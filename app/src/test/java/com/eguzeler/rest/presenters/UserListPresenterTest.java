package com.eguzeler.rest.presenters;

import com.eguzeler.rest.Constants;
import com.eguzeler.rest.converters.UserList;
import com.eguzeler.rest.rest.RetrofitInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 04.06.2017.
 */
public class UserListPresenterTest {

    @Rule
    public final MockWebServer server = new MockWebServer();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retroServiceIsCorrectTest() throws Exception {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        Assert.assertTrue("Service is Null", service != null);

    }

    /**
     * Test load correct list if taken from service
     * */

    @Test
    public void loadUserListsCorrcetTest() throws Exception {

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        Assert.assertTrue("Service is Null", service != null);

        server.enqueue(new MockResponse().setResponseCode(200).setBody(Constants.USER_LISTS_STRINGS));


        service.getUserLists().enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                Assert.assertTrue("Wrong Response Code", response.code() == 200);
                Assert.assertTrue("Successfully taken user lists", response.body() != null);
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
            }
        });
    }


    /**
     *
     * Test loadUserlist method if response 404.
     * */
    @Test
    public void loadUserListsErrorTest() throws Exception {

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        Assert.assertTrue("Service is Null", service != null);

        server.enqueue(new MockResponse().setResponseCode(404).setBody(Constants.USER_LISTS_STRINGS));

        Response<UserList> response = service.getUserLists().execute();
        Assert.assertTrue("Cannot Get User Lists", response.code() == 404);

    }

}