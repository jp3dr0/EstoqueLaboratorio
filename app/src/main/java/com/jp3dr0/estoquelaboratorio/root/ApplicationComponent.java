package com.jp3dr0.estoquelaboratorio.root;

import com.jp3dr0.estoquelaboratorio.login.dependencyInjection.LoginModule;
import com.jp3dr0.estoquelaboratorio.login.mvp.view.LoginActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity loginActivity);

}
