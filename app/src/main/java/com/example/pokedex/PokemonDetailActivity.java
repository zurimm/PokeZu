package com.example.pokedex;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import okhttp3.RequestBody;
import okhttp3.MediaType;

public class PokemonDetailActivity extends AppCompatActivity {

    private ImageView imageViewPokemon;
    private TextView textViewPokemonName;
    private TextView textViewPokemonType;
    private TextView textViewPokemonLife;
    private TextView textViewPokemonAttack;
    private TextView textViewPokemonDefense;
    private TextView textViewPokemonResistance;
    private Button buttonBack;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        imageViewPokemon = findViewById(R.id.imageViewPokemon);
        textViewPokemonName = findViewById(R.id.textViewPokemonName);
        textViewPokemonType = findViewById(R.id.textViewPokemonType);
        textViewPokemonLife = findViewById(R.id.textViewPokemonLife);
        textViewPokemonAttack = findViewById(R.id.textViewPokemonAttack);
        textViewPokemonDefense = findViewById(R.id.textViewPokemonDefense);
        textViewPokemonResistance = findViewById(R.id.textViewPokemonResistance);
        buttonBack = findViewById(R.id.buttonBack);
        buttonDelete = findViewById(R.id.buttonDelete);

        Pokemon pokemon = (Pokemon) getIntent().getSerializableExtra("pokemon");
        if (pokemon != null) {

            // Asignar los detalles del Pokémon
            textViewPokemonName.setText(pokemon.getNOMBRE());
            textViewPokemonType.setText("Tipo: " + pokemon.getTIPO());
            textViewPokemonLife.setText("Vida: " + pokemon.getVIDA());
            textViewPokemonAttack.setText("Ataque: " + pokemon.getPUNTAJE_ATAQUE());
            textViewPokemonDefense.setText("Defensa: " + pokemon.getPUNTAJE_DEFENSA());
            textViewPokemonResistance.setText("Resistencia: " + pokemon.getPUNTAJE_RESISTENCIA());
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la anterior
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pokemon != null) {
                    // Implementa la lógica para eliminar el Pokémon
                    // Aquí podrías llamar a un método para eliminar el Pokémon de la base de datos o la lista
                    deletePokemon(pokemon.getNOMBRE());
                }
            }
        });
    }

    private void deletePokemon(String pokemonName) {
        Retrofit retrofit = RetrofitClient.getClient("http://10.0.2.2:3000/");
        PokemonApiService apiService = retrofit.create(PokemonApiService.class);

        Call<ResponseBody> call = apiService.eliminarPokemon(pokemonName);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PokemonDetailActivity.this, "Pokémon eliminado: "+ pokemonName, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PokemonDetailActivity.this, "Error al eliminar el Pokémon", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(PokemonDetailActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
