package com.jp3dr0.estoquelaboratorio.crud.update;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jp3dr0.estoquelaboratorio.repository.Api;
import com.jp3dr0.estoquelaboratorio.domain.Classificacao;
import com.jp3dr0.estoquelaboratorio.domain.Reagente;
import com.jp3dr0.estoquelaboratorio.domain.Unidade;
import com.jp3dr0.estoquelaboratorio.crud.read.ReagenteActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditReagenteFragment extends Fragment {

    Reagente reagente;

    Api api;

    EditText nome,qtdAberto,qtdLacrado,qtdTotal, valor, comentario;

    Spinner classificacao, unidade;

    String nUnidade, nClassificacao;

    public EditReagenteFragment() {
        // Required empty public constructor
    }

    public static EditReagenteFragment newInstance(Reagente reagente) {

        Bundle args = new Bundle();
        args.putParcelable("obj", reagente);
        EditReagenteFragment fragment = new EditReagenteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(com.jp3dr0.estoquelaboratorio.R.layout.fragment_edit_reagente, container, false);

        getBundle();

        api = Api.retrofit.create(Api.class);

        configurarMenu();

        nome = (EditText) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.nome);
        qtdTotal = (EditText) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.qtdTotal);
        qtdLacrado = (EditText) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.qtdLacrado);
        qtdAberto = (EditText) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.qtdAberto);
        classificacao = (Spinner) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.classificacao);
        unidade = (Spinner) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.unidade);
        valor = (EditText) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.valor);
        comentario = (EditText) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.comentario);

        popularLayout();

        Button editar = (Button) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.editar);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickEditar();
            }
        });

        return v;
    }

    public void getBundle(){
        Bundle bundle = getArguments();
        if (bundle != null) {
            reagente = bundle.getParcelable("obj");
        }
    }

    public void configurarMenu(){
        final ReagenteActivity activity = (ReagenteActivity) getActivity();
        Toolbar toolbar = activity.toolbar;
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case android.R.id.home:
                        //Toast.makeText(getContext(), "onClick home no fragment...", Toast.LENGTH_LONG).show();
                        activity.back = true;
                        activity.onBackPressed();
                        break;
                    case android.R.id.edit:
                        //Toast.makeText(getContext(), "onClick editar no fragment...", Toast.LENGTH_LONG).show();
                        activity.back = true;
                        activity.onBackPressed();
                        break;
                }
                return false;
            }
        });
    }

    public void popularLayout(){
        nome.setText(reagente.getNomeReagente());
        qtdTotal.setText(Integer.toString(reagente.getQtd_estoque_Reagente_total()));
        qtdAberto.setText(Integer.toString(reagente.getQtd_estoque_Reagente_aberto()));
        qtdLacrado.setText(Integer.toString(reagente.getQtd_estoque_Reagente_lacrado()));
        valor.setText(Integer.toString(reagente.getValorCapacidadeReagente()));

        if (reagente.getComentarioReagente() != null){
            comentario.setText(reagente.getComentarioReagente());
        }
        else {
            comentario.setVisibility(View.GONE);
        }

        Call<List<Unidade>> request1 = api.getUnidades();
        request1.enqueue(new Callback<List<Unidade>>() {
            @Override
            public void onResponse(Call<List<Unidade>> call, Response<List<Unidade>> response) {
                try{
                    List<Unidade> unidades = response.body();
                    List<String> list = new ArrayList<String>();
                    for (Unidade u : unidades)
                        list.add(u.nomeUnidade);
                    ArrayAdapter<String> adapterUnidade = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, list);
                    unidade.setAdapter(adapterUnidade);
                    unidade.setSelection(reagente.getUnidadeReagente());
                    unidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            nUnidade = Integer.toString(i);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                catch (Exception e){
                    unidade.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Unidade>> call, Throwable t) {

            }
        });

        Call<List<Classificacao>> request2 = api.getClassificacoes();
        request2.enqueue(new Callback<List<Classificacao>>() {
            @Override
            public void onResponse(Call<List<Classificacao>> call, Response<List<Classificacao>> response) {
                try{
                    List<Classificacao> classificacoes = response.body();
                    List<String> list = new ArrayList<String>();
                    for (Classificacao c : classificacoes){
                        list.add(c.nomeClassificacao);
                    }
                    ArrayAdapter<String> adapterClassificacao = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, list);
                    classificacao.setAdapter(adapterClassificacao);
                    classificacao.setSelection(reagente.getClassificacaoReagente());
                    classificacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            nClassificacao = Integer.toString(i);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                catch (Exception erro){
                    classificacao.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Classificacao>> call, Throwable t) {

            }
        });
    }

    public void onClickEditar(){
        Integer nId = reagente.getIdReagente();
        String nNomeReagente = nome.getText().toString();
        String nImg = null;
        String nComentario = comentario.getText().toString();
        //Integer nValor = Integer.parseInt(valor.getText().toString());
        String nValor = valor.getText().toString();
        //Integer nQtd = Integer.parseInt(qtd.getText().toString());
        String nQtdLacrado = qtdLacrado.getText().toString();
        String nQtdAberto = qtdAberto.getText().toString();
        String nQtdTotal = qtdTotal.getText().toString();

        String[] colunas = new String[] {
                "imgReagente",
                "qtd_estoque_Reagente_lacrado",
                "qtd_estoque_Reagente_aberto",
                "qtd_estoque_Reagente_total",
                "nomeReagente",
                "comentarioReagente",
                "ClassificacaoReagente",
                "valorCapacidadeReagente",
                "UnidadeReagente"
        };

        String[] valores = new String[] {nImg, nQtdLacrado, nQtdAberto, nQtdTotal, nNomeReagente, nComentario, nClassificacao, nValor, nUnidade};

        Map<String, Object> map = new ArrayMap<>();
        //put something inside the map, could be null
        JSONObject jsonParams = new JSONObject();

        for (int i = 0; i < colunas.length; i++){
            if(valores[i] == null){
                valores[i] = "";
            }
            try {
                jsonParams.put(colunas[i], valores[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),jsonParams.toString());
        Log.d("LOG", jsonParams.toString());
        Toast.makeText(getActivity(), jsonParams.toString(), Toast.LENGTH_LONG).show();

        Call<ResponseBody> request3 = api.updateReagente(nId, body);

        request3.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Toast.makeText(getActivity(), "Reagente atualizado: " + response.body().string(), Toast.LENGTH_LONG).show();
                    Log.d("LOG", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}
