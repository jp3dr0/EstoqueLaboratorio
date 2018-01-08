package com.jp3dr0.estoquelaboratorio.root;

import android.app.Application;

import com.jp3dr0.estoquelaboratorio.login.dependencyInjection.LoginModule;

public class App extends Application {

    private ApplicationComponent component;

    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }
}
