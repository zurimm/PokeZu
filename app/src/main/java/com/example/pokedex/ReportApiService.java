package com.example.pokedex;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ReportApiService {
    @GET("pokemon/report")
    Call<ResponseBody> getPokemonReport();
}
