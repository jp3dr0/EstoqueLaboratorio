package com.jp3dr0.estoquelaboratorio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @GET("classificacao/{id}")
    Call<List<Classificacao>> getClassificacao(@Path("id") int idClassificacao);

    @GET("tamanho/{id}")
    Call<List<Tamanho>> getTamanho(@Path("id") int idTamanho);

    @GET("unidade/{id}")
    Call<List<Unidade>> getUnidade(@Path("id") int idUnidade);

    @GET("usuario")
    Call<List<Usuario>> getUsuarios();

    @GET("usuario/{id}")
    Call<List<Usuario>> getUsuario(@Path("id") int idUsuario);

    @FormUrlEncoded
    @POST("/usuario")
    Call<Usuario> insertUsuario(@Field("nomeUsuario") String nomeUsuario,
                                @Field("Nivel_idNivel") int Nivel_idNivel,
                                @Field("senhaUsuario") String senhaUsuario,
                                @Field("emailUsuario") String emailUsuario,
                                @Field("loginUsuario") String loginUsuario);

    @FormUrlEncoded
    @PUT("usuario/{id}")
    Call<Usuario> updateUsuario(@Path("id") int id,
                                @Field("nomeUsuario") String nomeUsuario,
                                @Field("Nivel_idNivel") int Nivel_idNivel,
                                @Field("senhaUsuario") String senhaUsuario,
                                @Field("emailUsuario") String emailUsuario,
                                @Field("loginUsuario") String loginUsuario);

    @DELETE("usuario/{id}")
    Call<Usuario> deleteUsuario(@Path("id") int id);

    @GET("reagente")
    Call<List<Reagente>> getReagentes();

    @GET("reagente/{id}")
    Call<List<Reagente>> getReagente(@Path("id") int idReagente);

    @FormUrlEncoded
    @POST("/reagente")
    Call<Reagente> insertReagente(@Field("nomeReagente") String nomeReagente,
                                  @Field("imgReagente") String imgReagente,
                                  @Field("qtd_estoque_Reagente_lacrado") int qtd_estoque_Reagente_lacrado,
                                  @Field("qtd_estoque_Reagente_aberto") int qtd_estoque_Reagente_aberto,
                                  @Field("qtd_estoque_Reagente_total") int qtd_estoque_Reagente_total,
                                  @Field("comentarioReagente") String comentarioReagente,
                                  @Field("ClassificacaoReagente") int ClassificacaoReagente,
                                  @Field("valorCapacidadeReagente") int valorCapacidadeReagente,
                                  @Field("UnidadeReagente") int UnidadeReagente);

    @FormUrlEncoded
    @PUT("reagente/{id}")
    Call<Reagente> updateReagente(@Path("id") int idReagente,
                                  @Field("nomeReagente") String nomeReagente,
                                  @Field("imgReagente") String imgReagente,
                                  @Field("qtd_estoque_Reagente_lacrado") int qtd_estoque_Reagente_lacrado,
                                  @Field("qtd_estoque_Reagente_aberto") int qtd_estoque_Reagente_aberto,
                                  @Field("qtd_estoque_Reagente_total") int qtd_estoque_Reagente_total,
                                  @Field("comentarioReagente") String comentarioReagente,
                                  @Field("ClassificacaoReagente") int ClassificacaoReagente,
                                  @Field("valorCapacidadeReagente") int valorCapacidadeReagente,
                                  @Field("UnidadeReagente") int UnidadeReagente);

    @DELETE("reagente/{id}")
    Call<Reagente> deletereagente(@Path("id") int idReagente);

    @GET("vidraria")
    Call<List<Vidraria>> getVidrarias();

    @GET("vidraria/{id}")
    Call<List<Vidraria>> getVidraria(@Path("id") int idVidraria);

    @FormUrlEncoded
    @POST("/vidraria")
    Call<Vidraria> insertVidraria(@Field("nomeVidraria") String nomeVidraria,
                                  @Field("imgVidraria") String imgVidraria,
                                  @Field("qtd_estoque_Vidraria") int qtd_estoque_Vidraria,
                                  @Field("comentarioVidraria") String comentarioVidraria,
                                  @Field("valorCapacidadeVidraria") int valorCapacidadeVidraria,
                                  @Field("UnidadeVidraria") int UnidadeVidraria,
                                  @Field("tamanhoCapacidadeVidraria") int tamanhoCapacidadeVidraria);

    @FormUrlEncoded
    @PUT("vidraria/{id}")
    Call<Vidraria> updateVidraria(@Path("id") int idVidraria,
                                  @Field("nomeVidraria") String nomeVidraria,
                                  @Field("imgVidraria") String imgVidraria,
                                  @Field("qtd_estoque_Vidraria") int qtd_estoque_Vidraria,
                                  @Field("comentarioVidraria") String comentarioVidraria,
                                  @Field("valorCapacidadeVidraria") int valorCapacidadeVidraria,
                                  @Field("UnidadeVidraria") int UnidadeVidraria,
                                  @Field("tamanhoCapacidadeVidraria") int tamanhoCapacidadeVidraria);

    @DELETE("vidraria/{id}")
    Call<Vidraria> deleteVidraria(@Path("id") int idVidraria);
}