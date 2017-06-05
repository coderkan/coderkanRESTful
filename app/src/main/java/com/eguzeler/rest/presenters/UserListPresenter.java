package com.eguzeler.rest.presenters;


import com.eguzeler.rest.converters.User;
import com.eguzeler.rest.converters.UserList;
import com.eguzeler.rest.rest.RetrofitClient;
import com.eguzeler.rest.rest.RetrofitInterface;
import com.eguzeler.rest.view.UserListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 03.06.2017.
 */

public class UserListPresenter {

    private UserListView mUserListView = null;

    public UserListPresenter(UserListView mUserListView){
        this.mUserListView = mUserListView;
    }

    public void loadUserLists(){

        RetrofitInterface service = RetrofitClient.getRetrofitUserListInstance().create(RetrofitInterface.class);

        Call<UserList> userCall = service.getUserLists();
        userCall.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if(response.code() == 200){
                    if(response.body() != null){
                        List<User> list = response.body().getUserlists();
                        if(list.size() != 0)
                            mUserListView.onLoadUserList(list);
                    }
                }

                if(response.code() == 404){
                    mUserListView.onLoadError(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                mUserListView.onLoadError(t.getMessage());
            }
        });

    }



}
