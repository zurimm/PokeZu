package com.example.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddPokemonActivity extends AppCompatActivity {

    private EditText editTextPokemonName;
    private EditText editTextPokemonType;
    private EditText editTextPokemonLife;
    private EditText editTextPokemonAttack;
    private EditText editTextPokemonDefense;
    private EditText editTextPokemonResistance;
    private Button buttonBack;
    private Button buttonSavePokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pokemon);

        editTextPokemonName = findViewById(R.id.editTextPokemonName);
        editTextPokemonType = findViewById(R.id.editTextPokemonType);
        editTextPokemonLife = findViewById(R.id.editTextPokemonLife);
        editTextPokemonAttack = findViewById(R.id.editTextPokemonAttack);
        editTextPokemonDefense = findViewById(R.id.editTextPokemonDefense);
        editTextPokemonResistance = findViewById(R.id.editTextPokemonResistance);
        buttonSavePokemon = findViewById(R.id.buttonSavePokemon);
        buttonBack = findViewById(R.id.buttonBack);

        buttonSavePokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePokemon();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la anterior
            }
        });
    }

    private void savePokemon() {
        String name = editTextPokemonName.getText().toString();
        int type = Integer.parseInt(editTextPokemonType.getText().toString());
        int life = Integer.parseInt(editTextPokemonLife.getText().toString());
        int attack = Integer.parseInt(editTextPokemonAttack.getText().toString());
        int defense = Integer.parseInt(editTextPokemonDefense.getText().toString());
        int resistance = Integer.parseInt(editTextPokemonResistance.getText().toString());

        Log.d("AddPokemonActivity", "Datos a enviar:");
        Log.d("AddPokemonActivity", "Nombre: " + name);
        Log.d("AddPokemonActivity", "Tipo: " + type);
        Log.d("AddPokemonActivity", "Vida: " + life);
        Log.d("AddPokemonActivity", "Ataque: " + attack);
        Log.d("AddPokemonActivity", "Defensa: " + defense);
        Log.d("AddPokemonActivity", "Resistencia: " + resistance);

        Retrofit retrofit = RetrofitClient.getClient("http://10.0.2.2:3000/");
        PokemonApiService apiService = retrofit.create(PokemonApiService.class);

        NuevoPokemon newPokemon = new NuevoPokemon(name, type, life, attack, defense, resistance);
        Call<Void> call = apiService.crearPokemon(newPokemon);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(AddPokemonActivity.this, "Pokémon guardado", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddPokemonActivity.this, "Error al guardar el Pokémon", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(AddPokemonActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
