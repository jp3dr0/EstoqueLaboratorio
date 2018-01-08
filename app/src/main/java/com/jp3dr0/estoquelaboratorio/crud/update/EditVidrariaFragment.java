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
import com.jp3dr0.estoquelaboratorio.domain.Tamanho;
import com.jp3dr0.estoquelaboratorio.domain.Unidade;
import com.jp3dr0.estoquelaboratorio.domain.Vidraria;
import com.jp3dr0.estoquelaboratorio.crud.read.VidrariaActivity;

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

public class EditVidrariaFragment extends Fragment implements Spinner.OnItemSelectedListener {

    Vidraria vidraria;

    Api api;

    EditText nome,qtd, valor, comentario;

    Spinner unidade, tamanho;

    String nTamanho, nUnidade = null;

    Button editar;

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
        View v = inflater.inflate(com.jp3dr0.estoquelaboratorio.R.layout.fragment_edit_vidraria, container, false);

        getBundle();

        configurarMenu();

        nome = (EditText) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.nome);
        qtd = (EditText) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.qtd);
        tamanho = (Spinner) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.tamanho);
        unidade = (Spinner) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.unidade);
        valor = (EditText) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.valor);
        comentario = (EditText) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.comentario);

        api = Api.retrofit.create(Api.class);

        popularLayout();

        editar = (Button) v.findViewById(com.jp3dr0.estoquelaboratorio.R.id.editar);

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
            vidraria = bundle.getParcelable("obj");
        }
    }

    public void configurarMenu(){
        final VidrariaActivity activity = (VidrariaActivity) getActivity();
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
        nome.setText(vidraria.getNomeVidraria());
        qtd.setText(Integer.toString(vidraria.getQtd_estoque_Vidraria()));

        if (vidraria.getComentarioVidraria() != null){
            comentario.setText(vidraria.getComentarioVidraria());
        }
        else {
            comentario.setVisibility(View.GONE);
        }

        try{
            if ( vidraria.getUnidadeVidraria() != 0){

                Call<List<Unidade>> request = api.getUnidades();

                request.enqueue(new Callback<List<Unidade>>() {
                    @Override
                    public void onResponse(Call<List<Unidade>> call, Response<List<Unidade>> response) {
                        try{
                            List<Unidade> unidades = response.body();
                            List<String> list = new ArrayList<String>();
                            for (Unidade u : unidades)
                                list.add(u.nomeUnidade);
                            valor.setText(Integer.toString(vidraria.getValorCapacidadeVidraria()));
                            ArrayAdapter<String> adapterUnidade = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, list);
                            unidade.setAdapter(adapterUnidade);
                            unidade.setSelection(vidraria.getUnidadeVidraria());
                            unidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    //Toast.makeText(getActivity(), "Item " + i, Toast.LENGTH_LONG).show();
                                    nUnidade = Integer.toString(i);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });

                            tamanho.setVisibility(View.GONE);
                        }
                        catch (Exception x){
                            unidade.setVisibility(View.GONE);
                            valor.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Unidade>> call, Throwable t) {

                    }
                });


            }
        }
        catch (Exception e) {
            unidade.setVisibility(View.GONE);
            valor.setVisibility(View.GONE);
            try{
                if (vidraria.getTamanhoCapacidadeVidraria() != 0){

                    Call<List<Tamanho>> request = api.getTamanhos();

                    request.enqueue(new Callback<List<Tamanho>>() {
                        @Override
                        public void onResponse(Call<List<Tamanho>> call, Response<List<Tamanho>> response) {
                            List<Tamanho> tamanhos = response.body();
                            List<String> list = new ArrayList<String>();
                            for (Tamanho t : tamanhos)
                                list.add(t.nomeTamanho);
                            ArrayAdapter<String> adapterTamanho = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, list);
                            tamanho.setAdapter(adapterTamanho);
                            tamanho.setSelection(vidraria.getTamanhoCapacidadeVidraria());

                            tamanho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    //Toast.makeText(getActivity(), "Item " + i, Toast.LENGTH_LONG).show();
                                    nTamanho = Integer.toString(i);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<Tamanho>> call, Throwable t) {

                        }
                    });
                }
            }
            catch (Exception error){
                tamanho.setVisibility(View.GONE);
            }

        }
    }

    public void onClickEditar(){
        Integer nId = vidraria.getIdVidraria();
        String nNomeVidraria = nome.getText().toString();
        String nImg = null;
        //Integer nQtd = Integer.parseInt(qtd.getText().toString());
        String nQtd = qtd.getText().toString();
        String nComentario = comentario.getText().toString();
        //Integer nValor = Integer.parseInt(valor.getText().toString());
        String nValor = valor.getText().toString();

        String[] colunas = new String[] {
                "imgVidraria",
                "qtd_estoque_Vidraria",
                "nomeVidraria",
                "comentarioVidraria",
                "valorCapacidadeVidraria",
                "tamanhoCapacidadeVidraria",
                "UnidadeVidraria"
        };

        String[] valores = new String[] {nImg, nQtd, nNomeVidraria, nComentario, nValor, nTamanho, nUnidade};

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
        //serviceCaller is the interface initialized with retrofit.create...
        Call<ResponseBody> response = api.updateVidraria(nId, body);

        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Toast.makeText(getActivity(), "Vidraria atualizada: " + response.body().string(), Toast.LENGTH_LONG).show();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(getActivity(), "Item " + i, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
