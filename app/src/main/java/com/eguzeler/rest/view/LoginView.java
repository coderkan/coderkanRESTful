package com.eguzeler.rest.view;

import android.content.Context;

import com.eguzeler.rest.rest.RetrofitInterface;

/**
 * Created by root on 03.06.2017.
 */

public interface LoginView {

    void onLoginSuccess();
    void onLoginFailed(String msg);
    void onPasswordTextError(String err);
    void onUserNameTextError(String err);
    void onShowProgress(boolean prg);
    void onFocusView(int which);
    RetrofitInterface getRetrofitService();
    String getUserNameText();
    String getPasswordText();

    Context getContext();
}
