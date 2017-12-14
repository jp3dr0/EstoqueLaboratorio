package com.jp3dr0.estoquelaboratorio.Entidades;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jp3dr0.estoquelaboratorio.Api;
import com.mikepenz.fastadapter.commons.utils.FastAdapterUIUtils;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.util.UIUtils;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reagente extends AbstractItem<Reagente, Reagente.ViewHolder> implements Parcelable{
    Integer idReagente;
    String imgReagente;
    Integer qtd_estoque_Reagente_lacrado;
    Integer qtd_estoque_Reagente_aberto;
    Integer qtd_estoque_Reagente_total;
    String nomeReagente;
    String comentarioReagente;
    Integer ClassificacaoReagente;
    Integer valorCapacidadeReagente;
    Integer UnidadeReagente;

    public Reagente(String imgReagente, String nomeReagente, String comentarioReagente) {
        this.imgReagente = imgReagente;
        this.nomeReagente = nomeReagente;
        this.comentarioReagente = comentarioReagente;
    }

    public Reagente(String imgReagente, String nomeReagente) {
        this.imgReagente = imgReagente;
        this.nomeReagente = nomeReagente;
    }

    public Reagente() {
    }

    public Reagente(Integer idReagente, String imgReagente, Integer qtd_estoque_Reagente_lacrado, Integer qtd_estoque_Reagente_aberto, Integer qtd_estoque_Reagente_total, String nomeReagente, String comentarioReagente, Integer classificacaoReagente, Integer valorCapacidadeReagente, Integer unidadeReagente) {
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
        return com.jp3dr0.estoquelaboratorio.R.layout.item;
    }

    @Override
    public void bindView(final Reagente.ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        Log.d("LOG", "bindView Reagente");

        Context ctx = holder.itemView.getContext();

        Api api = Api.retrofit.create(Api.class);

        final Call<List<Unidade>> request = api.getUnidade(UnidadeReagente);
        request.enqueue(new Callback<List<Unidade>>() {
            @Override
            public void onResponse(Call<List<Unidade>> call, Response<List<Unidade>> response) {
                Unidade obj = response.body().get(0);
                String unidade = obj.nomeUnidade;
                holder.desc.setText(valorCapacidadeReagente + unidade);
                Log.d("LOG", "Unidade = " + unidade);
            }

            @Override
            public void onFailure(Call<List<Unidade>> call, Throwable t) {

            }
        });

        //String desc = classificacao + " - " + valorCapacidadeReagente + unidade;

        holder.title.setText(nomeReagente);
        //holder.desc.setText(desc);
        //holder.featuredImage.setImageBitmap(null);
        holder.featuredImage.setVisibility(View.GONE);
        //set the background for the item
        int color = UIUtils.getThemeColor(ctx, com.jp3dr0.estoquelaboratorio.R.attr.colorPrimary);
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

            this.featuredImage = (ImageView) itemView.findViewById(com.jp3dr0.estoquelaboratorio.R.id.featuredImage);
            this.title = (TextView) itemView.findViewById(com.jp3dr0.estoquelaboratorio.R.id.title);
            this.desc = (TextView) itemView.findViewById(com.jp3dr0.estoquelaboratorio.R.id.desc);
            this.btnLink = (Button) itemView.findViewById(com.jp3dr0.estoquelaboratorio.R.id.btnLink);
            this.card_layout = (ConstraintLayout) itemView.findViewById(com.jp3dr0.estoquelaboratorio.R.id.card_layout);

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.idReagente);
        dest.writeString(this.imgReagente);
        dest.writeValue(this.qtd_estoque_Reagente_lacrado);
        dest.writeValue(this.qtd_estoque_Reagente_aberto);
        dest.writeValue(this.qtd_estoque_Reagente_total);
        dest.writeString(this.nomeReagente);
        dest.writeString(this.comentarioReagente);
        dest.writeValue(this.ClassificacaoReagente);
        dest.writeValue(this.valorCapacidadeReagente);
        dest.writeValue(this.UnidadeReagente);
    }

    protected Reagente(Parcel in) {
        this.idReagente = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imgReagente = in.readString();
        this.qtd_estoque_Reagente_lacrado = (Integer) in.readValue(Integer.class.getClassLoader());
        this.qtd_estoque_Reagente_aberto = (Integer) in.readValue(Integer.class.getClassLoader());
        this.qtd_estoque_Reagente_total = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nomeReagente = in.readString();
        this.comentarioReagente = in.readString();
        this.ClassificacaoReagente = (Integer) in.readValue(Integer.class.getClassLoader());
        this.valorCapacidadeReagente = (Integer) in.readValue(Integer.class.getClassLoader());
        this.UnidadeReagente = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Reagente> CREATOR = new Creator<Reagente>() {
        @Override
        public Reagente createFromParcel(Parcel source) {
            return new Reagente(source);
        }

        @Override
        public Reagente[] newArray(int size) {
            return new Reagente[size];
        }
    };
}
