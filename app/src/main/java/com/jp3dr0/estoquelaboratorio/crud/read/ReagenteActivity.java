package com.jp3dr0.estoquelaboratorio.crud.read;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jp3dr0.estoquelaboratorio.R;
import com.jp3dr0.estoquelaboratorio.dialog.DeleteDialog;
import com.jp3dr0.estoquelaboratorio.domain.Classificacao;
import com.jp3dr0.estoquelaboratorio.domain.Reagente;
import com.jp3dr0.estoquelaboratorio.domain.Unidade;
import com.jp3dr0.estoquelaboratorio.repository.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReagenteActivity extends AppCompatActivity {


    private Reagente reagente;
    Api api;
    public Toolbar toolbar;
    TextView nome,qtdAberto,qtdLacrado,qtdTotal, classificacao, unidade, valor, comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reagente);

        reagente = getIntent().getExtras().getParcelable("obj");

        nome = (TextView) findViewById(R.id.nome);
        qtdTotal = (TextView) findViewById(R.id.qtdTotal);
        qtdLacrado = (TextView) findViewById(R.id.qtdLacrado);
        qtdAberto = (TextView) findViewById(R.id.qtdAberto);
        classificacao = (TextView) findViewById(R.id.classificacao);
        unidade = (TextView) findViewById(R.id.unidade);
        valor = (TextView) findViewById(R.id.valor);
        comentario = (TextView) findViewById(R.id.comentario);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(reagente.getNomeReagente());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        nome.setText(reagente.getNomeReagente());
        qtdTotal.setText(Integer.toString(reagente.getQtd_estoque_Reagente_total()));
        qtdAberto.setText(Integer.toString(reagente.getQtd_estoque_Reagente_aberto()));
        qtdLacrado.setText(Integer.toString(reagente.getQtd_estoque_Reagente_lacrado()));

        api = Api.retrofit.create(Api.class);

        if (reagente.getComentarioReagente() != null){
            comentario.setText(reagente.getComentarioReagente());
        }
        else {
            comentario.setVisibility(View.GONE);
        }

        Call<List<Classificacao>> request2 = api.getClassificacao(reagente.getClassificacaoReagente());

        request2.enqueue(new Callback<List<Classificacao>>() {
            @Override
            public void onResponse(Call<List<Classificacao>> call, Response<List<Classificacao>> response) {
                Classificacao obj = response.body().get(0);
                String cf = obj.nomeClassificacao;
                classificacao.setText(cf);
            }

            @Override
            public void onFailure(Call<List<Classificacao>> call, Throwable t) {
                classificacao.setVisibility(View.GONE);
            }
        });

        Call<List<Unidade>> request = api.getUnidade(reagente.getUnidadeReagente());
        request.enqueue(new Callback<List<Unidade>>() {
            @Override
            public void onResponse(Call<List<Unidade>> call, Response<List<Unidade>> response) {
                Unidade obj = response.body().get(0);
                String un = obj.nomeUnidade;
                unidade.setText(un);
                valor.setText(Integer.toString(reagente.getValorCapacidadeReagente()));
            }

            @Override
            public void onFailure(Call<List<Unidade>> call, Throwable t) {
                unidade.setVisibility(View.GONE);
                valor.setVisibility(View.GONE);
            }
        });
    }
    public boolean back = false;
    @Override
    public void onBackPressed() {
        if(back){
            back = !back;
            findViewById(R.id.container_content).setVisibility(View.VISIBLE);
            findViewById(R.id.container_frag).setVisibility(View.GONE);
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.action_excluir:
                //Toast.makeText(getApplicationContext(), "Excluir...", Toast.LENGTH_LONG).show();
                openDialog();
                /*
                AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
                alertDialog.setTitle("Atenção");
                alertDialog.setMessage("Deseja realmente deletar esse item?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Call<ResponseBody> request = api.deleteReagente(reagente.getIdReagente());
                                request.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    }
                                });
                            }
                        });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Toast.makeText(getApplicationContext(), "OK, nada feito.", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.show();

                break;
            case R.id.action_editar:
                Toast.makeText(getApplicationContext(), "Editar...", Toast.LENGTH_LONG).show();
                // Pega o FragmentManager
                FragmentManager fm = getSupportFragmentManager();
                // Abre uma transação e adiciona
                FragmentTransaction ft = fm.beginTransaction();
                findViewById(R.id.container_content).setVisibility(View.GONE);
                findViewById(R.id.container_frag).setVisibility(View.VISIBLE);
                ft.add(R.id.container_frag, EditReagenteFragment.newInstance(reagente));
                ft.commit();
                */
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openDialog(){
        DeleteDialog deleteDialog = DeleteDialog.newInstance(reagente.getIdReagente(), "Reagente");
        deleteDialog.show(getSupportFragmentManager(), "delete Dialog");
    }
}
