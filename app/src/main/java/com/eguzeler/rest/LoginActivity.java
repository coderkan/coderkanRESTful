package com.eguzeler.rest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eguzeler.rest.presenters.LoginPresenter;
import com.eguzeler.rest.view.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.login_form)
    View mLoginFormView;

    @BindView(R.id.login_progress)
    View mProgressView;

    @BindView(R.id.user_password)
    EditText mPasswordView;

    @BindView(R.id.user_name)
    EditText mUserNameView;

    private LoginPresenter mLoginPresenter = null;


    @Inject
    Retrofit retrofit;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ((CoderkanApplication) getApplication()).getNetworkComponent().inject(this);

        this.context = getApplicationContext();

        if(mLoginPresenter == null)
            mLoginPresenter = new LoginPresenter(retrofit, this);
    }

    @OnClick(R.id.email_sign_in_button) void loginClick(){
        attemptLogin();
    }

    private void attemptLogin() {
        mUserNameView.setError(null);
        mPasswordView.setError(null);
        mLoginPresenter.login(getUserNameText(), getPasswordText());
    }


    /**
     * Google's default LoginActivity progress bar control method.
     * */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onLoginSuccess() {
        showProgress(false);
        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(LoginActivity.this, UserListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFailed(String msg) {
        showProgress(false);
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordTextError(String err) {
        mPasswordView.setError(err);
    }

    @Override
    public void onUserNameTextError(String err) {
        mUserNameView.setError(err);
    }

    @Override
    public void onShowProgress(boolean prg) {
        showProgress(prg);
    }

    @Override
    public void onFocusView(int which) {
        // UserName focus
        if(which == 1)
            mUserNameView.requestFocus();
        else if(which == 2) // password focus
            mPasswordView.requestFocus();
    }

    @Override
    public String getUserNameText() {
        return mUserNameView.getText().toString();
    }

    @Override
    public String getPasswordText() {
        return mPasswordView.getText().toString();
    }

    @Override
    public Context getContext() {
        return this.context;
    }

}

