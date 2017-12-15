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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Vidraria extends AbstractItem<Vidraria, Vidraria.ViewHolder> implements Parcelable{
    Integer idVidraria;
    String imgVidraria;
    Integer qtd_estoque_Vidraria;
    String nomeVidraria;
    String comentarioVidraria;
    Integer valorCapacidadeVidraria;
    Integer tamanhoCapacidadeVidraria;
    Integer UnidadeVidraria;

    public Vidraria(String imgVidraria, String nomeVidraria) {
        this.imgVidraria = imgVidraria;
        this.nomeVidraria = nomeVidraria;
    }

    public Vidraria() {
    }

    public Vidraria(Integer idVidraria, String imgVidraria, Integer qtd_estoque_Vidraria, String nomeVidraria, String comentarioVidraria, Integer valorCapacidadeVidraria, Integer tamanhoCapacidadeVidraria, Integer unidadeVidraria) {
        this.idVidraria = idVidraria;
        this.imgVidraria = imgVidraria;
        this.qtd_estoque_Vidraria = qtd_estoque_Vidraria;
        this.nomeVidraria = nomeVidraria;
        this.comentarioVidraria = comentarioVidraria;
        this.valorCapacidadeVidraria = valorCapacidadeVidraria;
        this.tamanhoCapacidadeVidraria = tamanhoCapacidadeVidraria;
        UnidadeVidraria = unidadeVidraria;
    }

    public int getIdVidraria() {
        return idVidraria;
    }

    public void setIdVidraria(int idVidraria) {
        this.idVidraria = idVidraria;
    }

    public String getImgVidraria() {
        return imgVidraria;
    }

    public void setImgVidraria(String imgVidraria) {
        this.imgVidraria = imgVidraria;
    }

    public int getQtd_estoque_Vidraria() {
        return qtd_estoque_Vidraria;
    }

    public void setQtd_estoque_Vidraria(int qtd_estoque_Vidraria) {
        this.qtd_estoque_Vidraria = qtd_estoque_Vidraria;
    }

    public String getNomeVidraria() {
        return nomeVidraria;
    }

    public void setNomeVidraria(String nomeVidraria) {
        this.nomeVidraria = nomeVidraria;
    }

    public String getComentarioVidraria() {
        return comentarioVidraria;
    }

    public void setComentarioVidraria(String comentarioVidraria) {
        this.comentarioVidraria = comentarioVidraria;
    }

    public int getValorCapacidadeVidraria() {
        return valorCapacidadeVidraria;
    }

    public void setValorCapacidadeVidraria(int valorCapacidadeVidraria) {
        this.valorCapacidadeVidraria = valorCapacidadeVidraria;
    }

    public int getTamanhoCapacidadeVidraria() {
        return tamanhoCapacidadeVidraria;
    }

    public void setTamanhoCapacidadeVidraria(int tamanhoCapacidadeVidraria) {
        this.tamanhoCapacidadeVidraria = tamanhoCapacidadeVidraria;
    }

    public int getUnidadeVidraria() {
        return UnidadeVidraria;
    }

    public void setUnidadeVidraria(int unidadeVidraria) {
        UnidadeVidraria = unidadeVidraria;
    }

    @Override
    public Vidraria.ViewHolder getViewHolder(View v) {
        return new Vidraria.ViewHolder(v);
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
    public void bindView(final Vidraria.ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        Log.d("LOG", "bindView Vidraria");

        Context ctx = holder.itemView.getContext();

        Api api = Api.retrofit.create(Api.class);

        if(UnidadeVidraria != null){
            final Call<List<Unidade>> request = api.getUnidade(UnidadeVidraria);
            request.enqueue(new Callback<List<Unidade>>() {
                @Override
                public void onResponse(Call<List<Unidade>> call, Response<List<Unidade>> response) {
                    Unidade obj = response.body().get(0);
                    String unidade = obj.nomeUnidade;
                    holder.desc.setText(valorCapacidadeVidraria + unidade);
                    Log.d("LOG", "Unidade = " + unidade);
                }

                @Override
                public void onFailure(Call<List<Unidade>> call, Throwable t) {

                }
            });
        }
        else if (tamanhoCapacidadeVidraria != null){
            final Call<List<Tamanho>> request = api.getTamanho(tamanhoCapacidadeVidraria);
            request.enqueue(new Callback<List<Tamanho>>() {
                @Override
                public void onResponse(Call<List<Tamanho>> call, Response<List<Tamanho>> response) {
                    Tamanho obj = response.body().get(0);
                    String tamanho = obj.nomeTamanho;
                    holder.desc.setText(tamanho);
                }

                @Override
                public void onFailure(Call<List<Tamanho>> call, Throwable t) {

                }
            });
        }

        holder.title.setText(nomeVidraria);
        //holder.desc.setText(desc);
        //holder.featuredImage.setImageBitmap(null);
        holder.featuredImage.setVisibility(View.GONE);
        //set the background for the item
        int color = UIUtils.getThemeColor(ctx, com.jp3dr0.estoquelaboratorio.R.attr.colorPrimary);
        //holder.card_layout.setForeground(FastAdapterUIUtils.getSelectablePressedBackground(ctx, FastAdapterUIUtils.adjustAlpha(color, 100), 50, true));
    }

    @Override
    public void unbindView(Vidraria.ViewHolder holder) {
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
        dest.writeValue(this.idVidraria);
        dest.writeString(this.imgVidraria);
        dest.writeValue(this.qtd_estoque_Vidraria);
        dest.writeString(this.nomeVidraria);
        dest.writeString(this.comentarioVidraria);
        dest.writeValue(this.valorCapacidadeVidraria);
        dest.writeValue(this.tamanhoCapacidadeVidraria);
        dest.writeValue(this.UnidadeVidraria);
    }

    protected Vidraria(Parcel in) {
        this.idVidraria = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imgVidraria = in.readString();
        this.qtd_estoque_Vidraria = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nomeVidraria = in.readString();
        this.comentarioVidraria = in.readString();
        this.valorCapacidadeVidraria = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tamanhoCapacidadeVidraria = (Integer) in.readValue(Integer.class.getClassLoader());
        this.UnidadeVidraria = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Vidraria> CREATOR = new Creator<Vidraria>() {
        @Override
        public Vidraria createFromParcel(Parcel source) {
            return new Vidraria(source);
        }

        @Override
        public Vidraria[] newArray(int size) {
            return new Vidraria[size];
        }
    };
}