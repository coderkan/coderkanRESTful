package com.eguzeler.rest.view;

import com.eguzeler.rest.converters.User;

import java.util.List;

/**
 * Created by root on 03.06.2017.
 */

public interface UserListView {

    void onLoadUserList(List<User> list);
    void onLoadError(String msg);
}
