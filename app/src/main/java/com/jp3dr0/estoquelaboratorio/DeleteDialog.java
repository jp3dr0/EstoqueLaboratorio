package com.jp3dr0.estoquelaboratorio;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

import com.jp3dr0.estoquelaboratorio.Entidades.Reagente;
import com.jp3dr0.estoquelaboratorio.Entidades.Vidraria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteDialog extends AppCompatDialogFragment {

    int id;
    String escolha;

    public static DeleteDialog newInstance(int id, String escolha) {

        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putString("escolha", escolha);
        DeleteDialog fragment = new DeleteDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
        escolha = getArguments().getString("escolha");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alerta").setMessage("Realmente deseja deletar esse item?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Api api = Api.retrofit.create(Api.class);
                        if(escolha.equals("Reagente")){
                            Call<Reagente> request = api.deletereagente(id);
                            request.enqueue(new Callback<Reagente>() {
                                @Override
                                public void onResponse(Call<Reagente> call, Response<Reagente> response) {
                                    Toast.makeText(getActivity(), "Reagente excluído.", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<Reagente> call, Throwable t) {
                                    Toast.makeText(getActivity(), "Erro ao deletar Reagente.", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        else if (escolha.equals("Vidraria")){
                            Call<Vidraria> request2 = api.deleteVidraria(id);
                            request2.enqueue(new Callback<Vidraria>() {
                                @Override
                                public void onResponse(Call<Vidraria> call, Response<Vidraria> response) {
                                    Toast.makeText(getActivity(), "Vidraria excluída.", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<Vidraria> call, Throwable t) {
                                    Toast.makeText(getActivity(), "Erro ao deletar Vidraria.", Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }
}
