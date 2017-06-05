package com.eguzeler.rest.presenters;

import android.content.Context;

import com.eguzeler.rest.R;
import com.eguzeler.rest.converters.UserDetail;
import com.eguzeler.rest.rest.RetrofitClient;
import com.eguzeler.rest.rest.RetrofitInterface;
import com.eguzeler.rest.view.UserDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 03.06.2017.
 */

public class UserDetailInfoPresenter {

    private Context mContext = null;
    private UserDetailView mUserDetailView = null;

    public UserDetailInfoPresenter(Context context, UserDetailView view){
        this.mContext = context;
        this.mUserDetailView = view;
    }

    public void loadUserDetailInfo(String userId){

        if(userId.isEmpty()){
            mUserDetailView.onLoadError(this.mContext.getString(R.string.error_empty_user_id));
            return;
        }

        RetrofitInterface service = RetrofitClient.getRetrofitUserDetailInfoInstance().create(RetrofitInterface.class);

        Call<UserDetail> userCall = service.getUserDetail(userId);

        userCall.enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {

                if(response.code() == 200){
                    if(response.body() != null){
                        mUserDetailView.onLoadUserDetail(response.body());
                    }
                }

                if(response.code() == 404){
                    mUserDetailView.onLoadError(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
                mUserDetailView.onLoadError(t.getMessage());
            }

        });

    }


    public void updateUserInfo(String mUserId, String phone, String address) {

        if(mUserId == null){
            mUserDetailView.onLoadError(mContext.getString(R.string.error_empty_user_id));
            return;
        }

        RetrofitInterface service = RetrofitClient.getRetrofitUserDetailUpdateInstance().create(RetrofitInterface.class);

        Call<UserDetail> userUpdateCall = service.updateUserDetail(mUserId, phone, address);

        userUpdateCall.enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                if(response.code() == 200){
                    if(response.body() != null){
                        mUserDetailView.onUpdateMessage(response.message());
                    }
                }
                if(response.code() == 404){
                    mUserDetailView.onUpdateMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
                mUserDetailView.onLoadError(t.getMessage());
            }
        });

    }
}
