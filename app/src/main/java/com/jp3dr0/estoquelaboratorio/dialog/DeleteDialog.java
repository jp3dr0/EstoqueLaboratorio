package com.jp3dr0.estoquelaboratorio.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

import com.jp3dr0.estoquelaboratorio.repository.Api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteDialog extends AppCompatDialogFragment {

    int id;
    String escolha;
    Context context;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Alerta").setMessage("Realmente deseja deletar esse item?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        Api api = Api.retrofit.create(Api.class);

                        if(escolha.equals("Reagente")){
                            Call<ResponseBody> request = api.deleteReagente(id);
                            request.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    //Intent intent = new Intent(context, MainActivity.class);
                                    //startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }
                        else if (escolha.equals("Vidraria")){
                            Call<ResponseBody> request2 = api.deleteVidraria(id);
                            request2.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    //Intent intent = new Intent(context, MainActivity.class);
                                    //startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }

                        //Toast.makeText(getActivity(), "Deletar.", Toast.LENGTH_LONG).show();
                        //Intent intent = new Intent(getActivity(), MainActivity.class);
                        //startActivity(intent);
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Toast.makeText(context, "OK, nada feito.", Toast.LENGTH_LONG).show();
            }
        });
        return builder.create();
    }
}
