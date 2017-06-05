package com.eguzeler.rest.view;

import com.eguzeler.rest.converters.UserDetail;

/**
 * Created by root on 03.06.2017.
 */

public interface UserDetailView {

    void onLoadUserDetail(UserDetail userDetail);
    void onLoadError(String err);
    void onUpdateMessage(String msg);

}
