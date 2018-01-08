package com.jp3dr0.estoquelaboratorio.repository;

import com.jp3dr0.estoquelaboratorio.domain.Usuario;

import java.util.List;

public interface LoginRepository {
    List<Usuario> getUsuarios();
    void saveUsuario();
}
