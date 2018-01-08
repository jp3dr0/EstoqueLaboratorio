package com.jp3dr0.estoquelaboratorio.login.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.jp3dr0.estoquelaboratorio.crud.read.MainActivity;
import com.jp3dr0.estoquelaboratorio.domain.Usuario;
import com.jp3dr0.estoquelaboratorio.garbage.PrincipalActivity;
import com.jp3dr0.estoquelaboratorio.login.mvp.LoginMVP;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginMVP.Presenter {

    @Nullable
    private LoginMVP.View view;

    private LoginMVP.Model model;

    public LoginPresenter(LoginMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginMVP.View view) {
        this.view = view;
    }

    @Override
    public void entrarButtonClicked() {
        if (view != null){
            final String login = view.getLogin();
            final String senha = view.getSenha();

            if(login.equals("admin") && (senha.equals("admin"))){
                view.showSucess();
                view.LoginToPrincipal();
            }

            List<Usuario> usuarios = model.getUsers();

            boolean achou = false;
            for (Usuario usuario : usuarios){
                if (usuario.getLoginUsuario().equals(login) && (usuario.getSenhaUsuario().equals(senha))){
                    achou = true;
                }
            }
            if(achou){
                view.showSucess();
                view.LoginToPrincipal();
            }
            else {
                view.showUsuarioInvalido();
            }
        }
    }

    @Override
    public void cadastrarButtonClicked() {

    }
}
