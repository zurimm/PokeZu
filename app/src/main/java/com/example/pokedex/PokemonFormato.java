package com.example.pokedex;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonFormato extends RecyclerView.Adapter<PokemonFormato.PokemonViewHolder> {

    private List<Pokemon> pokemonList;
    private Context context;

    public PokemonFormato(List<Pokemon> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.textViewPokemonName.setText(pokemon.getNOMBRE());

        // Log
        Log.d("PokemonFormato", "Pokémon en posición " + position + ": " + pokemon.getNOMBRE());


        holder.buttonViewDetails.setOnClickListener(v -> {
            Intent intent = new Intent(context, PokemonDetailActivity.class);
            intent.putExtra("pokemon", pokemon); // Pasar el objeto Pokemon
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPokemonName;
        Button buttonViewDetails;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPokemonName = itemView.findViewById(R.id.textViewPokemonName);
            buttonViewDetails = itemView.findViewById(R.id.buttonViewDetails);
        }
    }
}




