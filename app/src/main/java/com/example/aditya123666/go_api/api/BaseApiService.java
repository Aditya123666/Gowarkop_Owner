package com.example.aditya123666.go_api.api;

//import io.github.adamnain.gowarkop.model.Pesan;
//import io.github.adamnain.gowarkop.model.ResponseMenu;
import com.example.aditya123666.go_api.model.ResponsePesan;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {
    @GET("pesan/lihat")
    Call<ResponsePesan> getAllPesanan(@Query("key")String key);

    @FormUrlEncoded
    @PUT("status/pesan/{id}")
    Call<ResponseBody> updateStatus(@Path("id") String id, @Field("status") String status, @Query("key") String key);



}
