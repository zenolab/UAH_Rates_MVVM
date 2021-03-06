package com.uah_rates.grd.uahrates;

import android.app.Application;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uah_rates.grd.uahrates.viewmodel.interactor.ApiResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class App extends Application {
   //-------------------------------------------------------------------------------------------------------------------
   public static class CacheResponse {

       private static ApiResponse cacheResponse;

       public static ApiResponse getCacheResponse() {
           return cacheResponse;
       }

       public static void setCacheResponse(ApiResponse cacheResponse) {
           CacheResponse.cacheResponse = cacheResponse;
       }
   }

   //-------------------------------------------------------------------------------------------------------------------
  public static class RetrofitClientInstance {


    private static Retrofit retrofit;
    private static final String BASE_URL = "https://bank.gov.ua/";

    static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build();


    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    //Global scope
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

   }
   //-------------------------------------------------------------------------------------------------------------------


}
