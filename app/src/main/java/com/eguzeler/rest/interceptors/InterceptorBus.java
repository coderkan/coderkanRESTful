package com.eguzeler.rest.interceptors;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;

/**
 * Created by coderkan on 18.07.2017.
 */

public class InterceptorBus {

    private Interceptor mInterceptor = new LoginInterceptor();
    private static InterceptorBus mInstance = null;

//    @Inject
//    public static InterceptorBus getInstance(){
//        if(mInstance == null)
//            mInstance = new InterceptorBus();
//        return mInstance;
//    }

    public InterceptorBus() {
    }

    public void setInterceptor(Interceptor interceptor){
        this.mInterceptor = interceptor;
    }

    public Interceptor getInterceptor(){
        return this.mInterceptor;
    }

}
