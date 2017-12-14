package com.jp3dr0.estoquelaboratorio.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jp3dr0.estoquelaboratorio.LoginActivity;


public class CadastrarFragment extends Fragment {

    Button btnProximo;
    EditText etNome;
    EditText etLogin;
    EditText etEmail;
    EditText etSenha;

    public CadastrarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(com.jp3dr0.estoquelaboratorio.R.layout.fragment_cadastrar, container, false);
        btnProximo = (Button) view.findViewById(com.jp3dr0.estoquelaboratorio.R.id.btnProximo);
        etNome = (EditText) view.findViewById(com.jp3dr0.estoquelaboratorio.R.id.etNome);
        etLogin = (EditText) view.findViewById(com.jp3dr0.estoquelaboratorio.R.id.etLogin);
        etSenha = (EditText) view.findViewById(com.jp3dr0.estoquelaboratorio.R.id.etSenha);
        etEmail = (EditText) view.findViewById(com.jp3dr0.estoquelaboratorio.R.id.etEmail);

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity activity = (LoginActivity) getActivity();
                FragmentManager fm = activity.getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(com.jp3dr0.estoquelaboratorio.R.id.fragment_container, new PermissoesFragment());
                ft.commit();
                activity.setBackFragment2(true);

                activity.getSupportActionBar().setTitle("Permissões");
            }
        });

        return view;
    }


}
