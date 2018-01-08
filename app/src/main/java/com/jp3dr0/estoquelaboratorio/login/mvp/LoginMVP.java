package com.jp3dr0.estoquelaboratorio.login.mvp;

import com.jp3dr0.estoquelaboratorio.domain.Usuario;

import java.util.List;

public interface LoginMVP {

    interface View {

        void showInputError();
        void showUsuarioInvalido();
        void showSucess();

        String getNome();
        String getLogin();
        String getEmail();
        String getSenha();
        int getOpcaoEscolhida();

        void LoginToCadastrarFragment();
        void CadastrarFragmentToLogin();
        void CadastrarFragmentToPermissoesFragment();
        void PermissoesFragmentToLogin();

        void LoginToMain();

        void LoginToPrincipal();

        /*
        interface Login {
            String getLogin();
            String getSenha();

            void showUsuarioInvalido();
            void showSucess();
        }

        interface Cadastrar {
            String getNome();
            String getLogin();
            String getEmail();
            String getSenha();
        }

        interface Permissoes {
            int getOpcaoEscolhida();
        }
        */
    }

    interface Presenter {
        void setView(LoginMVP.View view);

        void entrarButtonClicked();
        void cadastrarButtonClicked();


    }

    interface Model {
        // CRUD
        void createUser(Usuario usuario);
        List<Usuario> getUsers();
    }
}
