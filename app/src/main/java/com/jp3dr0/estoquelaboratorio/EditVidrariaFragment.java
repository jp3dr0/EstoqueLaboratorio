package com.jp3dr0.estoquelaboratorio;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jp3dr0.estoquelaboratorio.Entidades.Tamanho;
import com.jp3dr0.estoquelaboratorio.Entidades.Unidade;
import com.jp3dr0.estoquelaboratorio.Entidades.Vidraria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditVidrariaFragment extends Fragment {

    Vidraria vidraria;

    EditText nome,qtd, tamanho, unidade, valor, comentario;

    public EditVidrariaFragment() {
        // Required empty public constructor
    }

    public static EditVidrariaFragment newInstance(Vidraria vidraria) {

        Bundle args = new Bundle();
        args.putParcelable("obj", vidraria);
        EditVidrariaFragment fragment = new EditVidrariaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit_vidraria, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            vidraria = bundle.getParcelable("obj");
        }

        final VidrariaActivity activity = (VidrariaActivity) getActivity();
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
        qtd = (EditText) v.findViewById(R.id.qtd);
        tamanho = (EditText) v.findViewById(R.id.tamanho);
        unidade = (EditText) v.findViewById(R.id.unidade);
        valor = (EditText) v.findViewById(R.id.valor);
        comentario = (EditText) v.findViewById(R.id.comentario);

        nome.setText(vidraria.getNomeVidraria());
        qtd.setText(Integer.toString(vidraria.getQtd_estoque_Vidraria()));

        final Api api = Api.retrofit.create(Api.class);

        if (vidraria.getComentarioVidraria() != null){
            comentario.setText(vidraria.getComentarioVidraria());
        }
        else {
            comentario.setVisibility(View.GONE);
        }

        try{
            if ( vidraria.getUnidadeVidraria() != 0){
                valor.setText(Integer.toString(vidraria.getValorCapacidadeVidraria()));
                unidade.setText(Integer.toString(vidraria.getUnidadeVidraria()));
            }
        }
        catch (Exception e) {
            unidade.setVisibility(View.GONE);
            valor.setVisibility(View.GONE);
            try{
                if (vidraria.getTamanhoCapacidadeVidraria() != 0){
                    tamanho.setText(Integer.toString(vidraria.getTamanhoCapacidadeVidraria()));
                }
            }
            catch (Exception error){
                tamanho.setVisibility(View.GONE);
            }

        }

        // Inflate the layout for this fragment

        Button editar = (Button) v.findViewById(R.id.editar);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Vidraria> r = api.insertVidraria(nome.getText().toString(), null, Integer.parseInt(qtd.getText().toString()), null, Integer.parseInt(valor.getText().toString()), Integer.parseInt(unidade.getText().toString()), Integer.parseInt(tamanho.getText().toString()));
                r.enqueue(new Callback<Vidraria>() {
                    @Override
                    public void onResponse(Call<Vidraria> call, Response<Vidraria> response) {
                        Toast.makeText(getActivity(), "Vidraria atualizada.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Vidraria> call, Throwable t) {

                    }
                });
            }
        });

        return v;
    }

}
