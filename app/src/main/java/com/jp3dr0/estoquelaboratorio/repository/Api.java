package com.jp3dr0.estoquelaboratorio.repository;

import com.jp3dr0.estoquelaboratorio.domain.Classificacao;
import com.jp3dr0.estoquelaboratorio.domain.Reagente;
import com.jp3dr0.estoquelaboratorio.domain.Tamanho;
import com.jp3dr0.estoquelaboratorio.domain.Unidade;
import com.jp3dr0.estoquelaboratorio.domain.Usuario;
import com.jp3dr0.estoquelaboratorio.domain.Vidraria;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    //public static final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2/estoquelab/index.php/").addConverterFactory(GsonConverterFactory.create()).build();

    //public static final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://professorlindo.000webhostapp.com/estoque_lab/index.php/").addConverterFactory(GsonConverterFactory.create()).build();

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor).build();
    public static final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://vaidebochando.16mb.com/index.php/")
            //.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    @GET("classificacao/{id}")
    Call<List<Classificacao>> getClassificacao(@Path("id") int idClassificacao);

    @GET("classificacao")
    Call<List<Classificacao>> getClassificacoes();

    @GET("tamanho/{id}")
    Call<List<Tamanho>> getTamanho(@Path("id") int idTamanho);

    @GET("tamanho")
    Call<List<Tamanho>> getTamanhos();

    @GET("unidade/{id}")
    Call<List<Unidade>> getUnidade(@Path("id") int idUnidade);

    @GET("unidade")
    Call<List<Unidade>> getUnidades();

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
    /*
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
     */

    @PUT("reagente/{id}")
    Call<ResponseBody> updateReagente(@Path("id") int idReagente, @Body RequestBody params);

    @POST("reagente")
    Call<ResponseBody> insertReagente(@Body RequestBody params);

    @DELETE("reagente/{id}")
    Call<ResponseBody> deleteReagente(@Path("id") int idReagente);

    @GET("vidraria")
    Call<List<Vidraria>> getVidrarias();

    @GET("vidraria/{id}")
    Call<List<Vidraria>> getVidraria(@Path("id") int idVidraria);
    /*
    @FormUrlEncoded
    @POST("/vidraria")
    Call<Vidraria> insertVidraria(@Field("nomeVidraria") String nomeVidraria,
                                  @Field("imgVidraria") String imgVidraria,
                                  @Field("qtd_estoque_Vidraria") Integer qtd_estoque_Vidraria,
                                  @Field("comentarioVidraria") String comentarioVidraria,
                                  @Field("valorCapacidadeVidraria") Integer valorCapacidadeVidraria,
                                  @Field("UnidadeVidraria") Integer UnidadeVidraria,
                                  @Field("tamanhoCapacidadeVidraria") Integer tamanhoCapacidadeVidraria);

    @FormUrlEncoded
    @PUT("vidraria/{id}")
    Call<ResponseBody> updateVidraria(@Path("id") Integer idVidraria,
                                  @Field("nomeVidraria") String nomeVidraria,
                                  @Field("imgVidraria") String imgVidraria,
                                  @Field("qtd_estoque_Vidraria") Integer qtd_estoque_Vidraria,
                                  @Field("comentarioVidraria") String comentarioVidraria,
                                  @Field("valorCapacidadeVidraria") Integer valorCapacidadeVidraria,
                                  @Field("UnidadeVidraria") Integer UnidadeVidraria,
                                  @Field("tamanhoCapacidadeVidraria") Integer tamanhoCapacidadeVidraria);


    @PUT("vidraria/{id}")
    Call<ResponseBody> updateVidraria(@Path("id") Integer idVidraria,
                                      @Body Vidraria vidraria);
    */
    @PUT("vidraria/{id}")
    Call<ResponseBody> updateVidraria(@Path("id") Integer idVidraria,
                                      @Body RequestBody params);

    @POST("vidraria")
    Call<ResponseBody> insertVidraria(@Body RequestBody params);

    @DELETE("vidraria/{id}")
    Call<ResponseBody> deleteVidraria(@Path("id") int idVidraria);
}