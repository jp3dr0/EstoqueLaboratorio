package com.jp3dr0.estoquelaboratorio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jp3dr0.estoquelaboratorio.Entidades.Classificacao;
import com.jp3dr0.estoquelaboratorio.Entidades.Reagente;
import com.jp3dr0.estoquelaboratorio.Entidades.Unidade;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditReagenteFragment extends Fragment {

    Reagente reagente;
    EditText nome,qtdAberto,qtdLacrado,qtdTotal, classificacao, unidade, valor, comentario;

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
        View v = inflater.inflate(R.layout.fragment_edit_reagente, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            reagente = bundle.getParcelable("obj");
        }

        final ReagenteActivity activity = (ReagenteActivity) getActivity();
        Toolbar toolbar = activity.toolbar;
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case android.R.id.home:
                        activity.back = true;
                        activity.onBackPressed();
                        break;
                }
                return false;
            }
        });

        nome = (EditText) v.findViewById(R.id.nome);
        qtdTotal = (EditText) v.findViewById(R.id.qtdTotal);
        qtdLacrado = (EditText) v.findViewById(R.id.qtdLacrado);
        qtdAberto = (EditText) v.findViewById(R.id.qtdAberto);
        classificacao = (EditText) v.findViewById(R.id.classificacao);
        unidade = (EditText) v.findViewById(R.id.unidade);
        valor = (EditText) v.findViewById(R.id.valor);
        comentario = (EditText) v.findViewById(R.id.comentario);

        nome.setText(reagente.getNomeReagente());
        qtdTotal.setText(Integer.toString(reagente.getQtd_estoque_Reagente_total()));
        qtdAberto.setText(Integer.toString(reagente.getQtd_estoque_Reagente_aberto()));
        qtdLacrado.setText(Integer.toString(reagente.getQtd_estoque_Reagente_lacrado()));

        final Api api = Api.retrofit.create(Api.class);

        if (reagente.getComentarioReagente() != null){
            comentario.setText(reagente.getComentarioReagente());
        }
        else {
            comentario.setVisibility(View.GONE);
        }

        classificacao.setText(Integer.toString(reagente.getClassificacaoReagente()));
        valor.setText(Integer.toString(reagente.getValorCapacidadeReagente()));

        // Inflate the layout for this fragment

        Button editar = (Button) v.findViewById(R.id.editar);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Reagente> r = api.insertReagente(nome.getText().toString(), null, Integer.parseInt(qtdLacrado.getText().toString()), Integer.parseInt(qtdAberto.getText().toString()), Integer.parseInt(qtdTotal.getText().toString()), null, Integer.parseInt(classificacao.getText().toString()), Integer.parseInt(valor.getText().toString()), Integer.parseInt(unidade.getText().toString()));

                r.enqueue(new Callback<Reagente>() {
                    @Override
                    public void onResponse(Call<Reagente> call, Response<Reagente> response) {
                        Toast.makeText(getActivity(), "Reagente atualizado.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Reagente> call, Throwable t) {

                    }
                });
            }
        });


        return v;
    }

}
