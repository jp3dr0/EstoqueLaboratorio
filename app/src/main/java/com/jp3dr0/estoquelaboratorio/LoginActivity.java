package com.jp3dr0.estoquelaboratorio;

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

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Button btnCadastrar;
    Button btnEntrar;

    EditText etLogin;
    EditText etSenha;

    Toolbar toolbar;

    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ScrollView layout = (ScrollView) findViewById(R.id.include);
        //layout.setVisibility(View.VISIBLE);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etSenha = (EditText) findViewById(R.id.etSenha);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pega o FragmentManager
                FragmentManager fm = getSupportFragmentManager();
                // Abre uma transação e adiciona
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.fragment_container, new CadastrarFragment());
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
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://professorlindo.000webhostapp.com/estoque_lab/index.php/").addConverterFactory(GsonConverterFactory.create()).build();

        api = retrofit.create(Api.class);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String login = etLogin.getText().toString();
                final String senha = etSenha.getText().toString();

                if(login.equals("admin") && (senha.equals("admin"))){
                    Toast.makeText(getApplicationContext(), "Entrando na MainActivity...", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(it);
                }

                final Call<List<Usuario>> request = api.getUsuarios();
                request.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        int code = response.code();
                        Toast.makeText(getApplicationContext(), "COde: " + code, Toast.LENGTH_LONG).show();
                        if(code == 423){
                            Toast.makeText(getApplicationContext(), "Servidor está dormindo por ser um servidor gratuito.", Toast.LENGTH_LONG).show();
                        }
                        else if (code == 200){
                            try{
                                List<Usuario> usuarios;
                                usuarios = response.body();
                                boolean achou = false;
                                for (Usuario usuario : usuarios){
                                    if(Objects.equals(usuario.getLoginUsuario(), login) && Objects.equals(usuario.getSenhaUsuario(), senha)) {
                                        achou = true;
                                    }
                                }
                                if(achou){
                                    Toast.makeText(getApplicationContext(), "Entrando na MainActivity...", Toast.LENGTH_LONG).show();
                                    Intent it = new Intent(getApplicationContext(), PrincipalActivity.class);
                                    startActivity(it);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Nenhum usuário encontrado. Tente novamente", Toast.LENGTH_LONG).show();
                                }
                            }
                            catch (Exception e){
                                Toast.makeText(getApplicationContext(), "200, porém, nenhum usuário encontrado. Tente novamente", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Erro. Codigo desconhecido", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Erro no servidor. Tente novamente", Toast.LENGTH_LONG).show();
                    }
                });

                /*
                switch(metodo){
                    case "GET E":
                        final Call<List<Usuario>> request = api.getUsuario(id);
                        request.enqueue(new Callback<List<Usuario>>() {
                            @Override
                            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                                Usuario usuario = response.body().get(0);
                                Toast.makeText(getBaseContext(), "Nome do usuário: " + usuario.getNomeUsuario() + " Email: " + usuario.getEmailUsuario(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Erro no servidor", Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                    case "GET C":
                        final Call<List<Usuario>> request2 = api.getUsuarios();
                        request2.enqueue(new Callback<List<Usuario>>() {
                            @Override
                            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                                List<Usuario> usuarios;
                                usuarios = response.body();
                                for (Usuario usuario : usuarios){
                                    Toast.makeText(getBaseContext(), "Nome do usuário: " + usuario.getNomeUsuario() + " Email: " + usuario.getEmailUsuario(), Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Erro no servidor", Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                    case "POST":
                        final Call<Usuario> request3 = api.insertUsuario("Teste", 0, "teste123", "jekdjek@ijsdwi", "testao");
                        request3.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                Toast.makeText(getApplicationContext(), "Usuario inserido com sucesso", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Erro no servidor", Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                    case "PUT":
                        final Call<Usuario> request4 = api.updateUsuario(id,"BOmba patch", 0, "teste123", "jekdjek@ijsdwi", "testao");
                        request4.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                Toast.makeText(getApplicationContext(), "Usuario atualizado com sucesso" + response.toString(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Erro no servidor", Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                    case "DELETE":
                        final Call<Usuario> request5 = api.deleteUsuario(id);
                        request5.enqueue(new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                Toast.makeText(getApplicationContext(), "Usuario deletado com sucesso", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Erro no servidor", Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                }
                */
            }
        });


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
            iniciarFragment1();

            backFragment2 = !backFragment2;
            backFragment1 = true;
        }
        else{
            super.onBackPressed();
        }
    }

    public void iniciarFragment1() {
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
    }
}
