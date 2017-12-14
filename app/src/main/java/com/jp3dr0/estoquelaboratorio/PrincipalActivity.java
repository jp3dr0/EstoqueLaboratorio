package com.jp3dr0.estoquelaboratorio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    FastAdapter fastAdapter;

    Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnView = (Button) findViewById(R.id.btnView);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
        //llm.setOrientation(LinearLayoutManager.VERTICAL);
        //llm.setReverseLayout(true);
        //recyclerView.setLayoutManager(llm);

        // STAGGERED GRID LAYOUT
        //StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        //recyclerView.setLayoutManager(llm);

        GridLayoutManager llm = new GridLayoutManager(getBaseContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);

        //create the ItemAdapter holding your Items
        itemAdapter = new ItemAdapter();
        //create the managing FastAdapter, by passing in the itemAdapter
        fastAdapter = FastAdapter.with(itemAdapter);

        //set our adapters to the RecyclerView
        recyclerView.setAdapter(fastAdapter);

        //set the items to your ItemAdapter
        itemAdapter.add(getItens());
        //getItens();

        //configure our mFastAdapter
        //as we provide id's for the items we want the hasStableIds enabled to speed up things
        fastAdapter.withSelectable(true);
        fastAdapter.withMultiSelect(true);
        fastAdapter.withSelectOnLongClick(true);

        fastAdapter.withOnClickListener(new OnClickListener() {
            @Override
            public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
                Toast.makeText(getApplicationContext(), "Clique na posição " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private boolean trocarView = false;

    public void onClickBtn (View v){
        Toast.makeText(getApplicationContext(), "onclick ", Toast.LENGTH_SHORT).show();
        trocarView = !trocarView;
        if(trocarView){
            LinearLayoutManager llm = new LinearLayoutManager(getBaseContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            //llm.setReverseLayout(true);
            recyclerView.setLayoutManager(llm);
        }
        else {
            GridLayoutManager llm = new GridLayoutManager(getBaseContext(), 2, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(llm);
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