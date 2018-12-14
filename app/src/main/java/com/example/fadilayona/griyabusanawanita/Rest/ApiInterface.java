package com.example.fadilayona.griyabusanawanita.Rest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

import com.example.fadilayona.griyabusanawanita.Model.GetBaju;
import com.example.fadilayona.griyabusanawanita.Model.GetTransaksi;
import com.example.fadilayona.griyabusanawanita.Model.PostPutDelTransaksi;


public interface ApiInterface {
    @GET("transaksi/pembeli")
    Call<GetTransaksi> getTransaksi();

    @FormUrlEncoded
    @POST("transaksi/pembeli")
    Call<PostPutDelTransaksi> postTransaksi
            (@Field("id_transaksi") String idTransaksi, @Field("id_baju") String idBaju,
             @Field("id_pembeli") String idPembeli, @Field("totalharga") String totalharga);

    @FormUrlEncoded
    @PUT("transaksi/pembeli")
    Call<PostPutDelTransaksi> putTransaksi(
            @Field("id_transaksi") String idTransaksi, @Field("id_baju") String idBaju,
            @Field("id_pembeli") String idPembeli, @Field("totalharga") String totalharga);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "transaksi/pembeli", hasBody = true)
    Call<PostPutDelTransaksi> deleteTransaksi(@Field("id_transaksi") String idTransaksi);

    @GET("baju/all")
    Call<GetBaju> getBaju();

    @Multipart
    @POST("baju/all")
    Call<GetBaju> postBaju(
            @Part MultipartBody.Part file,
            @Part("nama baju") RequestBody namabaju,
            @Part("kategori") RequestBody kategori,
            @Part("harga") RequestBody harga,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("baju/all")
    Call<GetBaju> putBaju(
            @Part MultipartBody.Part file,
            @Part("id_baju") RequestBody idLaptop,
            @Part("namabaju") RequestBody merk,
            @Part("kategori") RequestBody kategori,
            @Part("harga") RequestBody harga,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("baju/all")
    Call<GetBaju> deleteLaptop(
            @Part("id_baju") RequestBody idLaptop,
            @Part("action") RequestBody action);

    Call<PostPutDelTransaksi> putTransaksi(String s, String s1, String s2);

    Call<PostPutDelTransaksi> postTransaksi(String s, String s1, String s2);
//

}
