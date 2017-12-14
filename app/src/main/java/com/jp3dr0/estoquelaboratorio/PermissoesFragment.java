package com.jp3dr0.estoquelaboratorio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class PermissoesFragment extends Fragment {
    Button btnConcluido;
    RadioGroup radio;
    RadioButton radioButton;
    public PermissoesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permissoes, container, false);
        btnConcluido = (Button) view.findViewById(R.id.btnConcluido);
        radio = (RadioGroup) view.findViewById(R.id.radio);
        btnConcluido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radio.getCheckedRadioButtonId();

                switch(selectedId) {
                    case R.id.radioAluno:
                        Toast.makeText(getContext(), "ALUNO", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioProfessor:
                        Toast.makeText(getContext(), "PROFESSOR", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioTecnico:
                        Toast.makeText(getContext(), "TECNICO", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return view;
    }

}