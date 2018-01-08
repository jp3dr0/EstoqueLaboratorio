package com.jp3dr0.estoquelaboratorio.login.dependencyInjection;

import com.jp3dr0.estoquelaboratorio.login.mvp.LoginMVP;
import com.jp3dr0.estoquelaboratorio.login.mvp.model.LoginModel;
import com.jp3dr0.estoquelaboratorio.login.mvp.presenter.LoginPresenter;
import com.jp3dr0.estoquelaboratorio.repository.LoginRepository;
import com.jp3dr0.estoquelaboratorio.repository.UserRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    public LoginMVP.Presenter provideLoginPresenter(LoginMVP.Model model) {
        return new LoginPresenter(model);
    }

    @Provides
    public LoginMVP.Model provideLoginModel(LoginRepository repository){
        return new LoginModel(repository);
    }

    @Provides
    public LoginRepository LoginRepository() {
        return new UserRepository();
    }

}
