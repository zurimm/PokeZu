package com.example.pokedex;

public class NuevoPokemon {
    private String nombre;
    private int vida;
    private int tipo;
    private int ataque;
    private int defensa;
    private int resistencia;

    public NuevoPokemon(String nombre, int VIDA, int TIPO, int PUNTAJE_ATAQUE, int PUNTAJE_DEFENSA, int PUNTAJE_RESISTENCIA) {
        this.nombre = nombre;
        this.vida = VIDA;
        this.tipo = TIPO;
        this.ataque = PUNTAJE_ATAQUE;
        this.defensa = PUNTAJE_DEFENSA;
        this.resistencia = PUNTAJE_RESISTENCIA;
    }

    // Getters y setters

    public String getNOMBRE() {
        return nombre;
    }

    public void setNOMBRE(String NOMBRE) {
        this.nombre = NOMBRE;
    }

    public int getVIDA() {
        return vida;
    }

    public void setVIDA(int VIDA) {
        this.vida = VIDA;
    }

    public int getTIPO() {
        return tipo;
    }

    public void setTIPO(int TIPO) {
        this.tipo = TIPO;
    }

    public int getPUNTAJE_ATAQUE() {
        return ataque;
    }

    public void setPUNTAJE_ATAQUE(int PUNTAJE_ATAQUE) {
        this.ataque = PUNTAJE_ATAQUE;
    }

    public int getPUNTAJE_DEFENSA() {
        return defensa;
    }

    public void setPUNTAJE_DEFENSA(int PUNTAJE_DEFENSA) {
        this.defensa = PUNTAJE_DEFENSA;
    }

    public int getPUNTAJE_RESISTENCIA() {
        return resistencia;
    }

    public void setPUNTAJE_RESISTENCIA(int PUNTAJE_RESISTENCIA) {
        this.resistencia = PUNTAJE_RESISTENCIA;
    }

}
