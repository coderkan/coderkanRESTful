package com.eguzeler.rest;

import android.app.Application;

import com.eguzeler.rest.dagger2.component.DaggerNetworkComponent;
import com.eguzeler.rest.dagger2.component.NetworkComponent;
import com.eguzeler.rest.dagger2.module.ApplicationModule;
import com.eguzeler.rest.dagger2.module.NetworkModule;

public class CoderkanApplication extends Application {

    private NetworkComponent mNetworkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetworkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule("http://www.erkanguzeler.com"))
                .build();

    }

    public NetworkComponent getNetworkComponent(){
        return this.mNetworkComponent;
    }
}
