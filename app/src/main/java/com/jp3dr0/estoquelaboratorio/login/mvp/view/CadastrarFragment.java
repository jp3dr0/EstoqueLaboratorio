package com.jp3dr0.estoquelaboratorio.login.mvp.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class CadastrarFragment extends Fragment {

    Button btnProximo;
    EditText etNome;
    EditText etLogin;
    EditText etEmail;
    EditText etSenha;

    LoginActivity activity;

    public CadastrarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginActivity activity = (LoginActivity) getActivity();
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
                activity.CadastrarFragmentToLogin();
            }
        });

        return view;
    }


}
