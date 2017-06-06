package com.eguzeler.rest.dagger2.component;


import com.eguzeler.rest.LoginActivity;
import com.eguzeler.rest.dagger2.module.ApplicationModule;
import com.eguzeler.rest.dagger2.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {
    void inject(LoginActivity loginActivity);
}
