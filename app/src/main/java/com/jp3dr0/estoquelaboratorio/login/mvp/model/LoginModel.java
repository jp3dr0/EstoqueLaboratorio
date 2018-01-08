package com.jp3dr0.estoquelaboratorio.login.mvp.model;

import com.jp3dr0.estoquelaboratorio.domain.Usuario;
import com.jp3dr0.estoquelaboratorio.login.mvp.LoginMVP;
import com.jp3dr0.estoquelaboratorio.repository.LoginRepository;

import java.util.List;

public class LoginModel implements LoginMVP.Model{

    private LoginRepository repository;

    public LoginModel(LoginRepository repository){
        this.repository = repository;
    }

    @Override
    public void createUser(Usuario usuario) {

    }

    @Override
    public List<Usuario> getUsers() {
        return repository.getUsuarios();
    }
}
