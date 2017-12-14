package com.jp3dr0.estoquelaboratorio;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class Vidraria extends AbstractItem<Vidraria, Vidraria.ViewHolder>{
    int idVidraria;
    String imgVidraria;
    int qtd_estoque_Vidraria;
    String nomeVidraria;
    String comentarioVidraria;
    int valorCapacidadeVidraria;
    int tamanhoCapacidadeVidraria;
    int UnidadeVidraria;

    public Vidraria(String imgVidraria, String nomeVidraria) {
        this.imgVidraria = imgVidraria;
        this.nomeVidraria = nomeVidraria;
    }

    public Vidraria() {
    }

    public Vidraria(int idVidraria, String imgVidraria, int qtd_estoque_Vidraria, String nomeVidraria, String comentarioVidraria, int valorCapacidadeVidraria, int tamanhoCapacidadeVidraria, int unidadeVidraria) {
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
        return R.layout.item;
    }

    @Override
    public void bindView(Vidraria.ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        Context ctx = holder.itemView.getContext();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://professorlindo.000webhostapp.com/estoque_lab/index.php/").addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        holder.title.setText(nomeVidraria);

        String desc;
        final List temp = new ArrayList<String>();
        if(tamanhoCapacidadeVidraria == 0){
            final Call<List<Unidade>> request = api.getUnidade(UnidadeVidraria);
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
            desc = valorCapacidadeVidraria + (String)temp.get(0);
        }
        else {
            final Call<List<Tamanho>> request = api.getTamanho(tamanhoCapacidadeVidraria);
            request.enqueue(new Callback<List<Tamanho>>() {
                @Override
                public void onResponse(Call<List<Tamanho>> call, Response<List<Tamanho>> response) {
                    Tamanho obj = response.body().get(0);
                    temp.add(obj.nomeTamanho);
                }

                @Override
                public void onFailure(Call<List<Tamanho>> call, Throwable t) {

                }
            });
            desc = (String) temp.get(0);
        }
        holder.desc.setText(desc);
        holder.featuredImage.setImageBitmap(null);

        //set the background for the item
        int color = UIUtils.getThemeColor(ctx, R.attr.colorPrimary);
        holder.card_layout.setForeground(FastAdapterUIUtils.getSelectablePressedBackground(ctx, FastAdapterUIUtils.adjustAlpha(color, 100), 50, true));
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