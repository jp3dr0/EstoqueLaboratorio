package com.jp3dr0.estoquelaboratorio.Fragments;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jp3dr0.estoquelaboratorio.Api;
import com.jp3dr0.estoquelaboratorio.Entidades.Item;
import com.jp3dr0.estoquelaboratorio.Entidades.Vidraria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VidrariaFragment extends AbstractFragment {

    RecyclerView recyclerView;

    public VidrariaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(com.jp3dr0.estoquelaboratorio.R.layout.fragment_vidraria, container, false);

        recyclerView = (RecyclerView) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.recyclerView);

        Api api = Api.retrofit.create(Api.class);

        final Call<List<Vidraria>> request = api.getVidrarias();

        request.enqueue(new Callback<List<Vidraria>>() {
            @Override
            public void onResponse(Call<List<Vidraria>> call, Response<List<Vidraria>> response) {
                List<Vidraria> vidrarias = response.body();
                List<Item> lista = getItens();

                configurarRecyclerView(recyclerView, vidrarias, "Vidraria");

                configurarToolbar(recyclerView, vidrarias, "Vidraria");
            }

            @Override
            public void onFailure(Call<List<Vidraria>> call, Throwable t) {

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
