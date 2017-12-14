package com.jp3dr0.estoquelaboratorio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class VidrariaFragment extends AbstractFragment {

    String escolha;
    String trocarview;

    public VidrariaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vidraria, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            escolha = bundle.getString("escolha");
            trocarview = bundle.getString("trocarview");
        }

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        super.configurarRecyclerView(recyclerView, listVidrarias());

        super.configurarToolbar(listVidrarias());

        return v;
    }

    public List<Vidraria> listVidrarias() {
        final List<Vidraria> vidrarias = new ArrayList<Vidraria>();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://professorlindo.000webhostapp.com/estoque_lab/index.php/").addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        final Call<List<Vidraria>> request = api.getVidrarias();

        request.enqueue(new Callback<List<Vidraria>>() {
            @Override
            public void onResponse(Call<List<Vidraria>> call, Response<List<Vidraria>> response) {
                List<Vidraria> lista = response.body();
                for (Vidraria v: lista) {
                    vidrarias.add(v);
                }
            }

            @Override
            public void onFailure(Call<List<Vidraria>> call, Throwable t) {

            }
        });

        return vidrarias;
    }

}
