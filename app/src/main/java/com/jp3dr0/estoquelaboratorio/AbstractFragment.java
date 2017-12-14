package com.jp3dr0.estoquelaboratorio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

public class AbstractFragment extends Fragment {

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    FastAdapter fastAdapter;

    public AbstractFragment() {
        // Required empty public constructor
    }

    private boolean trocarView = false;

    public void configurarToolbar(final List list){
        MainActivity activity = (MainActivity) getActivity();
        Toolbar toolbar = activity.toolbar;

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case android.R.id.home:
                        //finish();
                        break;
                    case R.id.action_search:
                        Toast.makeText(getContext(), "search", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_change_recycler:
                        trocarView = !trocarView;
                        if (trocarView) {
                            item.setIcon(R.drawable.ic_view_quilt_black_24dp);
                        }
                        else {
                            item.setIcon(R.drawable.ic_view_stream_black_24dp);
                        }
                        configurarRecyclerView(recyclerView, list);
                        break;
                }
                return true;
            }
        });
    }

    public void configurarRecyclerView (RecyclerView rv, List list){
        this.recyclerView = rv;

        trocarLayoutManager();

        //create the ItemAdapter holding your Items
        itemAdapter = new ItemAdapter();
        //create the managing FastAdapter, by passing in the itemAdapter
        fastAdapter = FastAdapter.with(itemAdapter);

        //set our adapters to the RecyclerView
        recyclerView.setAdapter(fastAdapter);

        //set the items to your ItemAdapter
        itemAdapter.add(list);
        //getItens();

        //configure our mFastAdapter
        //as we provide id's for the items we want the hasStableIds enabled to speed up things
        fastAdapter.withSelectable(true);
        fastAdapter.withMultiSelect(true);
        fastAdapter.withSelectOnLongClick(true);

        fastAdapter.withOnClickListener(new OnClickListener() {
            @Override
            public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
                Toast.makeText(getContext(), "Clique na posição " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void trocarLayoutManager(){
        if(trocarView){
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            //llm.setReverseLayout(true);
            recyclerView.setLayoutManager(llm);
        }
        else {
            GridLayoutManager llm = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(llm);

            //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    public List<Item> getItens(){
        List<Item> itens = new ArrayList<Item>();
        String[] titles = {"la furia", "mano walter", "xandinho"};
        String[] descs = {"e o bichoo", "o chicote estralaa", "faz o X do comandante"};
        String[] imgs = {"http://www.ibahia.com/fileadmin/user_upload/ibahia/2017/fevereiro/21/La_Furia_.jpg", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZUQdc-vg7CdvNB0dlzvuAwoKmAzAlH4PW5U4uSXNwTf1IXZ6LIw", "http://www.otvfoco.com.br/wp-content/uploads/2016/10/xand.jpg"};
        for (int i = 0; i < titles.length; i++) {
            itens.add(new Item(imgs[i], titles[i], descs[i]));
        }
        return itens;
    }
}