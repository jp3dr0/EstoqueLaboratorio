package com.jp3dr0.estoquelaboratorio;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.commons.utils.FastAdapterUIUtils;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reagente extends AbstractItem<Reagente, Reagente.ViewHolder> {
    int idReagente;
    String imgReagente;
    int qtd_estoque_Reagente_lacrado;
    int qtd_estoque_Reagente_aberto;
    int qtd_estoque_Reagente_total;
    String nomeReagente;
    String comentarioReagente;
    int ClassificacaoReagente;
    int valorCapacidadeReagente;
    int UnidadeReagente;

    public Reagente(String imgReagente, String nomeReagente) {
        this.imgReagente = imgReagente;
        this.nomeReagente = nomeReagente;
    }

    public Reagente() {
    }

    public Reagente(int idReagente, String imgReagente, int qtd_estoque_Reagente_lacrado, int qtd_estoque_Reagente_aberto, int qtd_estoque_Reagente_total, String nomeReagente, String comentarioReagente, int classificacaoReagente, int valorCapacidadeReagente, int unidadeReagente) {
        this.idReagente = idReagente;
        this.imgReagente = imgReagente;
        this.qtd_estoque_Reagente_lacrado = qtd_estoque_Reagente_lacrado;
        this.qtd_estoque_Reagente_aberto = qtd_estoque_Reagente_aberto;
        this.qtd_estoque_Reagente_total = qtd_estoque_Reagente_total;
        this.nomeReagente = nomeReagente;
        this.comentarioReagente = comentarioReagente;
        ClassificacaoReagente = classificacaoReagente;
        this.valorCapacidadeReagente = valorCapacidadeReagente;
        UnidadeReagente = unidadeReagente;
    }

    public int getIdReagente() {
        return idReagente;
    }

    public void setIdReagente(int idReagente) {
        this.idReagente = idReagente;
    }

    public String getImgReagente() {
        return imgReagente;
    }

    public void setImgReagente(String imgReagente) {
        this.imgReagente = imgReagente;
    }

    public int getQtd_estoque_Reagente_lacrado() {
        return qtd_estoque_Reagente_lacrado;
    }

    public void setQtd_estoque_Reagente_lacrado(int qtd_estoque_Reagente_lacrado) {
        this.qtd_estoque_Reagente_lacrado = qtd_estoque_Reagente_lacrado;
    }

    public int getQtd_estoque_Reagente_aberto() {
        return qtd_estoque_Reagente_aberto;
    }

    public void setQtd_estoque_Reagente_aberto(int qtd_estoque_Reagente_aberto) {
        this.qtd_estoque_Reagente_aberto = qtd_estoque_Reagente_aberto;
    }

    public int getQtd_estoque_Reagente_total() {
        return qtd_estoque_Reagente_total;
    }

    public void setQtd_estoque_Reagente_total(int qtd_estoque_Reagente_total) {
        this.qtd_estoque_Reagente_total = qtd_estoque_Reagente_total;
    }

    public String getNomeReagente() {
        return nomeReagente;
    }

    public void setNomeReagente(String nomeReagente) {
        this.nomeReagente = nomeReagente;
    }

    public String getComentarioReagente() {
        return comentarioReagente;
    }

    public void setComentarioReagente(String comentarioReagente) {
        this.comentarioReagente = comentarioReagente;
    }

    public int getClassificacaoReagente() {
        return ClassificacaoReagente;
    }

    public void setClassificacaoReagente(int classificacaoReagente) {
        ClassificacaoReagente = classificacaoReagente;
    }

    public int getValorCapacidadeReagente() {
        return valorCapacidadeReagente;
    }

    public void setValorCapacidadeReagente(int valorCapacidadeReagente) {
        this.valorCapacidadeReagente = valorCapacidadeReagente;
    }

    public int getUnidadeReagente() {
        return UnidadeReagente;
    }

    public void setUnidadeReagente(int unidadeReagente) {
        UnidadeReagente = unidadeReagente;
    }

    @Override
    public Reagente.ViewHolder getViewHolder(View v) {
        return new Reagente.ViewHolder(v);
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item;
    }

    @Override
    public void bindView(Reagente.ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        Context ctx = holder.itemView.getContext();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://professorlindo.000webhostapp.com/estoque_lab/index.php/").addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        holder.title.setText(nomeReagente);

        String desc;
        final List temp = new ArrayList<String>();

        final Call<List<Unidade>> request = api.getUnidade(UnidadeReagente);
        request.enqueue(new Callback<List<Unidade>>() {
            @Override
            public void onResponse(Call<List<Unidade>> call, Response<List<Unidade>> response) {
                Unidade obj = response.body().get(0);
                temp.add(obj.nomeUnidade);
            }

            @Override
            public void onFailure(Call<List<Unidade>> call, Throwable t) {

            }
        });

        final Call<List<Classificacao>> request2 = api.getClassificacao(ClassificacaoReagente);
        request2.enqueue(new Callback<List<Classificacao>>() {
            @Override
            public void onResponse(Call<List<Classificacao>> call, Response<List<Classificacao>> response) {
                Classificacao obj = response.body().get(0);
                temp.add(obj.nomeClassificacao);
            }

            @Override
            public void onFailure(Call<List<Classificacao>> call, Throwable t) {

            }
        });

        desc = temp.get(0) + " - " + valorCapacidadeReagente + temp.get(1);
        holder.desc.setText(desc);
        holder.featuredImage.setImageBitmap(null);

        //set the background for the item
        int color = UIUtils.getThemeColor(ctx, R.attr.colorPrimary);
        holder.card_layout.setForeground(FastAdapterUIUtils.getSelectablePressedBackground(ctx, FastAdapterUIUtils.adjustAlpha(color, 100), 50, true));
    }

    @Override
    public void unbindView(Reagente.ViewHolder holder) {
        super.unbindView(holder);

        //Glide.clear(holder.featuredImage);
        holder.featuredImage.setImageDrawable(null);
        holder.title.setText(null);
        holder.desc.setText(null);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView featuredImage;
        public TextView title;
        public TextView desc;
        public Button btnLink;
        public ConstraintLayout card_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            this.featuredImage = (ImageView) itemView.findViewById(R.id.featuredImage);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.desc = (TextView) itemView.findViewById(R.id.desc);
            this.btnLink = (Button) itemView.findViewById(R.id.btnLink);
            this.card_layout = (ConstraintLayout) itemView.findViewById(R.id.card_layout);

            //optimization to preset the correct height for our device
            int screenWidth = itemView.getContext().getResources().getDisplayMetrics().widthPixels;
            int finalHeight = (int) (screenWidth / 1.5) / 2;
            featuredImage.setMinimumHeight(finalHeight);
            featuredImage.setMaxHeight(finalHeight);
            featuredImage.setAdjustViewBounds(false);

            //set height as layoutParameter too
            ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) featuredImage.getLayoutParams();
            lp.height = finalHeight;
            featuredImage.setLayoutParams(lp);
        }
    }
}
