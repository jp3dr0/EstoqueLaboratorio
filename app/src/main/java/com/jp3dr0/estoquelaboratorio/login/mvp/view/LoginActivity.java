package com.jp3dr0.estoquelaboratorio.login.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jp3dr0.estoquelaboratorio.login.mvp.LoginMVP;
import com.jp3dr0.estoquelaboratorio.repository.Api;
import com.jp3dr0.estoquelaboratorio.domain.Usuario;
import com.jp3dr0.estoquelaboratorio.crud.read.MainActivity;
import com.jp3dr0.estoquelaboratorio.garbage.PrincipalActivity;
import com.jp3dr0.estoquelaboratorio.R;
import com.jp3dr0.estoquelaboratorio.root.App;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View {

    Button btnCadastrar;
    Button btnEntrar;

    EditText etLogin;
    EditText etSenha;

    Toolbar toolbar;

    Usuario usuario;

    @Inject
    LoginMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App) getApplication()).getComponent().inject(this);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginToCadastrarFragment();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.entrarButtonClicked();
            }
        });


    }

    // METODOS DA ACTIVITY


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void LoginToMain() {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

    private boolean backFragment1 = false;

    private boolean backFragment2 = false;

    public void setBackFragment1(boolean backFragment1) {
        this.backFragment1 = backFragment1;
    }

    public void setBackFragment2(boolean backFragment2) {
        this.backFragment2 = backFragment2;
    }

    @Override
    public void onBackPressed() {

        //Toast.makeText(getBaseContext(), "onBackPressed - BackFragment1: " + String.valueOf(backFragment1) + " BackFragment2: " + String.valueOf(backFragment2), Toast.LENGTH_SHORT).show();

        if(backFragment1 && !backFragment2){
            findViewById(R.id.fragment_container).setVisibility(View.GONE);
            findViewById(R.id.include).setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle("Entrar");
            backFragment1 = !backFragment1;
        }
        else if ( (backFragment2) ) {
            LoginToCadastrarFragment();

            backFragment2 = !backFragment2;
            backFragment1 = true;
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Digite os campos corretamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUsuarioInvalido() {
        Toast.makeText(this, "Usuario inválido", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSucess() {
        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getLogin() {
        return etLogin.toString();
    }

    @Override
    public String getSenha() {
        return etSenha.toString();
    }

    @Override
    public void LoginToCadastrarFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new CadastrarFragment());
        ft.commit();

        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        findViewById(R.id.include).setVisibility(View.GONE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().invalidateOptionsMenu();
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        //upArrow.setColorFilter(getResources().getColor(R.color.selected_item_icon_color), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Crie uma conta");

        setBackFragment1(true);
    }

    @Override
    public void CadastrarFragmentToLogin() {

    }

    @Override
    public void CadastrarFragmentToPermissoesFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(com.jp3dr0.estoquelaboratorio.R.id.fragment_container, new PermissoesFragment());
        ft.commit();
        setBackFragment2(true);
        getSupportActionBar().setTitle("Permissões");
    }

    @Override
    public void PermissoesFragmentToLogin() {

    }

    // METODOS DO CADASTRAR FRAGMENT

    @Override
    public String getNome() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    // METODOS DO PERMISSOES FRAGMENT

    @Override
    public int getOpcaoEscolhida() {
        return 0;
    }

    // GARBAGE
    @Override
    public void LoginToPrincipal() {
        Intent it = new Intent(this, PrincipalActivity.class);
        startActivity(it);
    }
}
