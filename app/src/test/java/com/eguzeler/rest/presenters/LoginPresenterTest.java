package com.eguzeler.rest.presenters;

import android.content.Context;

import com.eguzeler.rest.Constants;
import com.eguzeler.rest.R;
import com.eguzeler.rest.converters.LoginUser;
import com.eguzeler.rest.rest.RetrofitInterface;
import com.eguzeler.rest.view.LoginView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class LoginPresenterTest {

    @Mock
    private Context context;

    @Mock
    private LoginView view;

    private LoginPresenter presenter;

    @Rule public final MockWebServer server = new MockWebServer();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(context, view);
    }

    @Test
    public void showUserNameEmptyError() throws Exception {

        when(view.getUserNameText()).thenReturn("");
        when(view.getPasswordText()).thenReturn("wer");
        presenter.login(view.getUserNameText(), view.getPasswordText());
        verify(view).onUserNameTextError(context.getString(R.string.error_field_required));
        verify(view).onFocusView(1);
    }

    @Test
    public void showUserPasswordEmptyError() throws Exception {
        when(view.getUserNameText()).thenReturn("dfsdf");
        when(view.getPasswordText()).thenReturn("");
        presenter.login(view.getUserNameText(), view.getPasswordText());
        verify(view).onPasswordTextError(context.getString(R.string.error_field_required));
        verify(view).onFocusView(2);
    }

    @Test
    public void testProgressBar() throws Exception {
        when(view.getUserNameText()).thenReturn("coderkan");
        when(view.getPasswordText()).thenReturn("12345ds");
        presenter.login(view.getUserNameText(), view.getPasswordText());

        verify(view).onShowProgress(true);
    }

    /*
    * Test login Control Call async if username and password is correct
    * */
    @Test
    public void testCall200Async() throws Exception {

        when(view.getUserNameText()).thenReturn("coderkan");
        when(view.getPasswordText()).thenReturn("1234");

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);
        Assert.assertTrue("Service is Null", service.hashCode() != 0);

        server.enqueue(new MockResponse().setResponseCode(200).setBody(Constants.LOGIN_USER_STRINGS));

        service.getLoginUser(view.getUserNameText(), view.getPasswordText()).enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {

                Assert.assertTrue("Wrong Response Code", response.code() == 200);
                Assert.assertTrue("Wrong Username And Password", response.body().getUsername().equals(view.getUserNameText()) && response.body().getUserpassword().equals(view.getPasswordText()));
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
            }
        });
    }

    /**
     * Test Call method if response code 404
     * */

    @Test
    public void testCall404() throws Exception {

        when(view.getUserNameText()).thenReturn("coderkanX");
        when(view.getPasswordText()).thenReturn("1234");

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);
        Assert.assertTrue("Service is Null", service.hashCode() != 0);

        server.enqueue(new MockResponse().setResponseCode(404).setBody(Constants.LOGIN_USER_STRINGS));
        final Response<LoginUser> loginUser = service.getLoginUser(view.getUserNameText(), view.getPasswordText()).execute();
        Assert.assertTrue("Login Failed", loginUser.code() == 404);

    }


    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void login() throws Exception {

    }
    
    

}