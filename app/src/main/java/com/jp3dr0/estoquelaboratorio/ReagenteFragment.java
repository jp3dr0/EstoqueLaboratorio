package com.jp3dr0.estoquelaboratorio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;

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
public class ReagenteFragment extends AbstractFragment {

    RecyclerView recyclerView;

    Button btnView;

    public ReagenteFragment() {
        // Required empty public constructor
    }

    String escolha;
    String trocarview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reagente, container, false);
        // Inflate the layout for this fragment

        Bundle bundle = getArguments();
        if (bundle != null) {
            escolha = bundle.getString("escolha");
            trocarview = bundle.getString("trocarview");
        }

        btnView = (Button) v.findViewById(R.id.btnView);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        super.configurarRecyclerView(recyclerView, listReagentes());

        super.configurarToolbar(listReagentes());

        return v;
    }

    public List<Reagente> listReagentes(){
        final List<Reagente> reagentes = new ArrayList<Reagente>();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://professorlindo.000webhostapp.com/estoque_lab/index.php/").addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        final Call<List<Reagente>> request = api.getReagentes();

        request.enqueue(new Callback<List<Reagente>>() {
            @Override
            public void onResponse(Call<List<Reagente>> call, Response<List<Reagente>> response) {
                List<Reagente> lista = response.body();
                for (Reagente r : lista) {
                    reagentes.add(r);
                }
            }

            @Override
            public void onFailure(Call<List<Reagente>> call, Throwable t) {

            }
        });

        return reagentes;
    }
}
