package com.jp3dr0.estoquelaboratorio.repository;

import com.jp3dr0.estoquelaboratorio.domain.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository implements LoginRepository{

    Api api;

    Usuario usuario;

    List<Usuario> usuarios;

    @Override
    public List<Usuario> getUsuarios() {
        final Call<List<Usuario>> call = api.getUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                int code = response.code();

                if (code == 200){
                    try {
                        usuarios = response.body();
                    }
                    catch (Exception e){
                        usuarios = null;
                    }
                }
                else {
                    usuarios = null;
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                usuarios = null;
            }
        });

        return usuarios;
    }

    @Override
    public void saveUsuario() {

    }
}
