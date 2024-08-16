package com.example.pokedex;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface PokemonApiService {


    @GET("pokemones")
    Call<List<Pokemon>> getPokemones();

    @POST("crearPOKEMON")
    Call<Void> crearPokemon(@Body NuevoPokemon pokemon);

    @DELETE("/eliminarPOKEMON")
    Call<ResponseBody> eliminarPokemon(@Query("nombre") String nombre);



    @GET("BitacoraTotal")
    Call<List<Bitacora>> getBitacoraTotal();

    @POST("crearRegistroBitacora")
    Call<Void> crearRegistroBitacora(@Body Bitacora bitacora);

}