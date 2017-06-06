package com.eguzeler.rest.presenters;

import com.eguzeler.rest.R;
import com.eguzeler.rest.converters.LoginUser;
import com.eguzeler.rest.rest.RetrofitInterface;
import com.eguzeler.rest.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginPresenter {

    private LoginView mLoginView = null;
    private Retrofit retrofit = null;

    public LoginPresenter(Retrofit retrofit, LoginView view){
        this.retrofit = retrofit;
        this.mLoginView = view;
    }

    public void login(String username, String password) {
        boolean usernameFocus = false, passwordFocus = false;

        if (password.isEmpty())
            passwordFocus = true;

        if(username.isEmpty())
            usernameFocus = true;

        if (usernameFocus) {
            this.mLoginView.onFocusView(1);
            this.mLoginView.onUserNameTextError(this.mLoginView.getContext().getString(R.string.error_field_required));
            return;
        }
        if (passwordFocus) {
            this.mLoginView.onFocusView(2);
            this.mLoginView.onPasswordTextError(this.mLoginView.getContext().getString(R.string.error_field_required));
            return;
        }

        //
        if (!usernameFocus && !passwordFocus) {
            this.mLoginView.onShowProgress(true);
            loginControl(username, password);
        }
    }

    private void loginControl(final String username, final String password) {

        RetrofitInterface service = retrofit.create(RetrofitInterface.class);

        Call<LoginUser> loginUserCall = service.getLoginUser(username, password);

        loginUserCall.enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                if(response.code() == 200){
                    if(response.body() != null){
                        LoginUser user = response.body();
                        if(user.getUsername().equals(username) && user.getUserpassword().equals(password))
                            mLoginView.onLoginSuccess();
                        else{
                            mLoginView.onLoginFailed(mLoginView.getContext().getString(R.string.error_incorrect_username_and_password));
                        }
                    }
                }

                if(response.code() == 404){
                    mLoginView.onLoginFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                mLoginView.onLoginFailed(t.getMessage());
            }
        });



    }

}
