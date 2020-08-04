package com.mobile.laporperjadin.api;




import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET("api_android/get_nama.php")
    Call<ResponseBody> getNama();

    @GET("api_android/get_kota.php")
    Call<ResponseBody> getKota();



}
