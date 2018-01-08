package com.jp3dr0.estoquelaboratorio.root;

import android.app.Application;

import dagger.Module;

@Module
public class ApplicationModule {

    public Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }
}
