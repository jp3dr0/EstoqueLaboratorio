package com.jp3dr0.estoquelaboratorio.Fragments;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jp3dr0.estoquelaboratorio.Api;
import com.jp3dr0.estoquelaboratorio.Entidades.Reagente;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReagenteFragment extends AbstractFragment {

    RecyclerView recyclerView;

    public ReagenteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(com.jp3dr0.estoquelaboratorio.R.layout.fragment_reagente, container, false);
        // Inflate the layout for this fragment

        recyclerView = (RecyclerView) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.recyclerView);

        Api api = Api.retrofit.create(Api.class);

        final Call<List<Reagente>> request = api.getReagentes();

        request.enqueue(new Callback<List<Reagente>>() {
            @Override
            public void onResponse(Call<List<Reagente>> call, Response<List<Reagente>> response) {
                Log.d("LOG", "request Reagente");
                try{
                    List<Reagente> reagentes = response.body();
                    Log.d("LOG", "sucess request Reagente: " + response.body().size());
                    configurarRecyclerView(recyclerView, reagentes, "Reagente");
                    configurarToolbar(recyclerView, reagentes, "Reagente");

                    //Log.d("LOG", "nomeReagente: " + r.getNomeReagente() + " comentarioReagente: " + r.getComentarioReagente() + " imgReagente: " + r.getImgReagente());
                }
                catch (Exception e){
                    Log.d("LOG", "erro request Reagente");
                }
            }

            @Override
            public void onFailure(Call<List<Reagente>> call, Throwable t) {
                Log.d("LOG", "erro request Reagente");
            }
        });

        return v;
    }

    public void configurarRecyclerView(RecyclerView recyclerView, List lista, String escolha) {
        super.configurarRecyclerView(recyclerView, lista, escolha);
    }

    public void configurarToolbar(RecyclerView recyclerView, List lista, String escolha){
        super.configurarToolbar(recyclerView, lista, escolha);
    }

}
