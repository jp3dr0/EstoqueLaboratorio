package com.jp3dr0.estoquelaboratorio;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RootFragment extends Fragment {

    private static final String TAG = "RootFragment";

    String escolha;
    String trocarview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_root, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            escolha = bundle.getString("escolha");
            trocarview = bundle.getString("trocarview");
        }

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */

		Fragment frag = new Fragment();

		if(!escolha.isEmpty()){
            if(escolha.equals("reagente")){
                frag = new ReagenteFragment();
            }
            else if (escolha.equals("vidraria")){
                frag = new VidrariaFragment();
            }
        }

        try {
            if(!trocarview.isEmpty()){
                Bundle bundleObject = new Bundle();
                bundleObject.putString("trocarview", trocarview);
                frag.setArguments(bundleObject);
            }
        }
        catch (Exception e){

        }

        transaction.replace(R.id.root_frame, frag);

        transaction.commit();

        return view;
    }

}