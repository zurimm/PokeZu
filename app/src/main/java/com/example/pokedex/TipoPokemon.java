package com.example.pokedex;

public class TipoPokemon {
    private int id;
    private String nombre;

    public TipoPokemon() {

    }

    public TipoPokemon(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}