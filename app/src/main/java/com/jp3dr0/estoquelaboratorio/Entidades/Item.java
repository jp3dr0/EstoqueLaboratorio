package com.jp3dr0.estoquelaboratorio.Entidades;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.commons.utils.FastAdapterUIUtils;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.util.UIUtils;

import java.util.List;

/**
 * Created by joaop on 13/12/2017.
 */

public class Item extends AbstractItem<Item, Item.ViewHolder> {
    private String featuredImage;
    private String title;
    private String desc;

    public Item(String featuredImage, String title, String desc) {
        this.featuredImage = featuredImage;
        this.title = title;
        this.desc = desc;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public void bindView(ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        Log.d("LOG", "bindView Item");
        Context ctx = holder.itemView.getContext();

        title = "Titulo";
        desc = "Desc";

        holder.title.setText(title);
        holder.desc.setText(desc);
        holder.featuredImage.setImageBitmap(null);

        //set the background for the item
        int color = UIUtils.getThemeColor(ctx, com.jp3dr0.estoquelaboratorio.R.attr.colorPrimary);
        holder.card_layout.setForeground(FastAdapterUIUtils.getSelectablePressedBackground(ctx, FastAdapterUIUtils.adjustAlpha(color, 100), 50, true));

        //load glide
        Glide.clear(holder.featuredImage);
        Glide.with(ctx).load( featuredImage )
                .animate(com.jp3dr0.estoquelaboratorio.R.anim.alpha_on)
                .into(holder.featuredImage);
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);

        Glide.clear(holder.featuredImage);
        holder.featuredImage.setImageDrawable(null);
        holder.title.setText(null);
        holder.desc.setText(null);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return com.jp3dr0.estoquelaboratorio.R.layout.item;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
}
