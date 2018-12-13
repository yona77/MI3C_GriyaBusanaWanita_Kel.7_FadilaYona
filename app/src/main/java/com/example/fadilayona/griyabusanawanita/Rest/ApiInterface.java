package com.example.fadilayona.griyabusanawanita.Rest;
import com.example.fadilayona.griyabusanawanita.Model.GetPembeli;
import com.example.fadilayona.griyabusanawanita.Model.GetTransaksi;
import com.example.fadilayona.griyabusanawanita.Model.PostPutDelTransaksi;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET("transaksi/user")
    Call<GetTransaksi> getTransaksi();
    @FormUrlEncoded
    Call<PostPutDelTransaksi> postTransaksi
            (@Field("id_transaksi") String idTransaksi,
             @Field("id_pembeli") String idPembeli,
             @Field("total_harga") String totalHarga,
             @Field("id_baju") String idBaju);
    @FormUrlEncoded
    @PUT("transaksi/user")
    Call<PostPutDelTransaksi> putTransaksi(
            @Field("id_transaksi") String idTransaksi,
            @Field("id_pembeli") String idPembeli,
            @Field("total_harga") String totalHarga,
            @Field("id_baju") String idBaju);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "transaksi/user", hasBody = true)
    Call<PostPutDelTransaksi> deleteTransaksi(@Field("id_transaksi") String idTransaksi);

    @GET("pembeli/all")
    Call<GetPembeli> getPembeli();

    @Multipart
    @POST("pembeli/all")
    Call<GetPembeli> postPembeli(
            @Part MultipartBody.Part file,
            @Part("nama") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("telp") RequestBody telpn,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("pembeli/all")
    Call<GetPembeli> putPembeli(
            @Part MultipartBody.Part file,
            @Part("id_pembeli") RequestBody idPembeli,
            @Part("nama") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("telp") RequestBody telpn,
            @Part("action") RequestBody action
    );

    @Multipart
    @POST("pembeli/all")
    Call<GetPembeli> deletePembeli(
            @Part("id_pembeli") RequestBody idPembeli,
            @Part("action") RequestBody action);
}

