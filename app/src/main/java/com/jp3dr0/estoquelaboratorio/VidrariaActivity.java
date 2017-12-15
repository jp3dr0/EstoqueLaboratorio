package com.jp3dr0.estoquelaboratorio;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.jp3dr0.estoquelaboratorio.Entidades.Tamanho;
import com.jp3dr0.estoquelaboratorio.Entidades.Unidade;
import com.jp3dr0.estoquelaboratorio.Entidades.Vidraria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VidrariaActivity extends AppCompatActivity {

    private Vidraria vidraria;
    Toolbar toolbar;
    TextView nome,qtd, tamanho, unidade, valor, comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidraria);

        vidraria = getIntent().getExtras().getParcelable("obj");

        nome = (TextView) findViewById(R.id.nome);
        qtd = (TextView) findViewById(R.id.qtd);
        tamanho = (TextView) findViewById(R.id.tamanho);
        unidade = (TextView) findViewById(R.id.unidade);
        valor = (TextView) findViewById(R.id.valor);
        comentario = (TextView) findViewById(R.id.comentario);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(vidraria.getNomeVidraria());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        nome.setText(vidraria.getNomeVidraria());
        qtd.setText(Integer.toString(vidraria.getQtd_estoque_Vidraria()));

        Api api = Api.retrofit.create(Api.class);

        if (vidraria.getComentarioVidraria() != null){
            comentario.setText(vidraria.getComentarioVidraria());
        }
        else {
            comentario.setVisibility(View.GONE);
        }

        try{
            if ( vidraria.getUnidadeVidraria() != 0){
                final Call<List<Unidade>> request = api.getUnidade(vidraria.getUnidadeVidraria());
                request.enqueue(new Callback<List<Unidade>>() {
                    @Override
                    public void onResponse(Call<List<Unidade>> call, Response<List<Unidade>> response) {
                        Unidade obj = response.body().get(0);
                        String un = obj.nomeUnidade;
                        unidade.setText(un);
                        valor.setText(Integer.toString(vidraria.getValorCapacidadeVidraria()));
                    }

                    @Override
                    public void onFailure(Call<List<Unidade>> call, Throwable t) {
                        unidade.setVisibility(View.GONE);
                        valor.setVisibility(View.GONE);
                    }
                });
            }
        }
        catch (Exception e) {
            unidade.setVisibility(View.GONE);
            valor.setVisibility(View.GONE);
            try{
                if (vidraria.getTamanhoCapacidadeVidraria() != 0){
                    final Call<List<Tamanho>> request = api.getTamanho(vidraria.getTamanhoCapacidadeVidraria());
                    request.enqueue(new Callback<List<Tamanho>>() {
                        @Override
                        public void onResponse(Call<List<Tamanho>> call, Response<List<Tamanho>> response) {
                            Tamanho obj = response.body().get(0);
                            String ta = obj.nomeTamanho;
                            tamanho.setText(ta);
                        }

                        @Override
                        public void onFailure(Call<List<Tamanho>> call, Throwable t) {
                            tamanho.setVisibility(View.GONE);
                        }
                    });
                }
            }
            catch (Exception error){
                tamanho.setVisibility(View.GONE);
            }

        }

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
                Toast.makeText(getApplicationContext(), "Excluir...", Toast.LENGTH_LONG).show();
                openDialog();
                break;
            case R.id.action_editar:
                Toast.makeText(getApplicationContext(), "Editar...", Toast.LENGTH_LONG).show();
                // Pega o FragmentManager
                FragmentManager fm = getSupportFragmentManager();
                // Abre uma transação e adiciona
                FragmentTransaction ft = fm.beginTransaction();
                findViewById(R.id.container_content).setVisibility(View.GONE);
                findViewById(R.id.container_frag).setVisibility(View.VISIBLE);
                ft.add(R.id.container_frag, EditVidrariaFragment.newInstance(vidraria));
                ft.commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openDialog(){
        DeleteDialog deleteDialog = DeleteDialog.newInstance(vidraria.getIdVidraria(), "Vidraria");
        deleteDialog.show(getSupportFragmentManager(), "delete Dialog");
    }
}
