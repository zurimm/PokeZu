package com.example.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerViewPokemones;
    private PokemonApiService apiService;
    private PokemonFormato adapter;
    private List<Pokemon> pokemonList;
    private List<Pokemon> filteredPokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddPokemon = findViewById(R.id.fabAddPokemon);
        fabAddPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPokemonActivity.class);
                startActivity(intent);
            }
        });

        recyclerViewPokemones = findViewById(R.id.recyclerViewPokemones);
        recyclerViewPokemones.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columnas en la cuadrícula

        apiService = RetrofitClient.getClient("http://10.0.2.2:3000/").create(PokemonApiService.class);

        fetchPokemones();

        // Configurar SearchView
        SearchView searchView = findViewById(R.id.searchViewPokemon);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchPokemones(); // Actualiza la lista cada vez que se reanuda la actividad
    }

    private void fetchPokemones() {
        Call<List<Pokemon>> callP = apiService.getPokemones();
        Log.d(TAG, "Iniciando la llamada a la API");
        callP.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(@NonNull Call<List<Pokemon>> call, @NonNull Response<List<Pokemon>> response) {
                Log.d(TAG, "Respuesta recibida"); // Log para depuración
                if (response.isSuccessful() && response.body() != null) {
                    pokemonList = response.body();
                    filteredPokemonList = new ArrayList<>(pokemonList);
                    adapter = new PokemonFormato(filteredPokemonList, MainActivity.this);
                    recyclerViewPokemones.setAdapter(adapter);
                } else {
                    Log.e(TAG, "Error en la respuesta: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                // Manejar errores
                Log.e(TAG, "Error en la llamada: " + t.getMessage());
                t.printStackTrace(); // Imprimir el error en la consola
            }
        });
    }

    // Método para filtrar la lista de Pokémon segun consulta del usuario
    private void filter(String text) {
        Log.d(TAG, "Filtrando con texto: " + text);
        if (filteredPokemonList == null) {
            filteredPokemonList = new ArrayList<>();
            Log.d(TAG, "filteredPokemonList era null, se ha inicializado.");
        } else {
            filteredPokemonList.clear();
        }

        if (pokemonList == null) {
            Log.e(TAG, "pokemonList es null.");
            return;
        }

        if (text.isEmpty()) {
            filteredPokemonList.addAll(pokemonList);
        } else {
            for (Pokemon pokemon : pokemonList) {
                if (pokemon.getNOMBRE().toLowerCase().contains(text.toLowerCase())) {
                    filteredPokemonList.add(pokemon);
                }
            }
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged(); // Notifica al adapter sobre los cambios
        } else {
            Log.e(TAG, "adapter es null.");
        }
    }
}
