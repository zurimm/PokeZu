package com.example.pokedex;
import java.io.Serializable;

public class Pokemon implements Serializable {
    private int ID;
    private String NOMBRE;
    private int VIDA;
    private int TIPO;
    private int PUNTAJE_ATAQUE;
    private int PUNTAJE_DEFENSA;
    private int PUNTAJE_RESISTENCIA;

    public Pokemon(String NOMBRE, int VIDA, int TIPO, int PUNTAJE_ATAQUE, int PUNTAJE_DEFENSA, int PUNTAJE_RESISTENCIA) {
        this.NOMBRE = NOMBRE;
        this.VIDA = VIDA;
        this.TIPO = TIPO;
        this.PUNTAJE_ATAQUE = PUNTAJE_ATAQUE;
        this.PUNTAJE_DEFENSA = PUNTAJE_DEFENSA;
        this.PUNTAJE_RESISTENCIA = PUNTAJE_RESISTENCIA;
    }

    // Getters y setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public int getVIDA() {
        return VIDA;
    }

    public void setVIDA(int VIDA) {
        this.VIDA = VIDA;
    }

    public int getTIPO() {
        return TIPO;
    }

    public void setTIPO(int TIPO) {
        this.TIPO = TIPO;
    }

    public int getPUNTAJE_ATAQUE() {
        return PUNTAJE_ATAQUE;
    }

    public void setPUNTAJE_ATAQUE(int PUNTAJE_ATAQUE) {
        this.PUNTAJE_ATAQUE = PUNTAJE_ATAQUE;
    }

    public int getPUNTAJE_DEFENSA() {
        return PUNTAJE_DEFENSA;
    }

    public void setPUNTAJE_DEFENSA(int PUNTAJE_DEFENSA) {
        this.PUNTAJE_DEFENSA = PUNTAJE_DEFENSA;
    }

    public int getPUNTAJE_RESISTENCIA() {
        return PUNTAJE_RESISTENCIA;
    }

    public void setPUNTAJE_RESISTENCIA(int PUNTAJE_RESISTENCIA) {
        this.PUNTAJE_RESISTENCIA = PUNTAJE_RESISTENCIA;
    }

    @Override
    public String toString() {
        return "ID: " + ID + ", Nombre: " + NOMBRE + ", Vida: " + VIDA +
                ", Tipo: " + TIPO + ", Ataque: " + PUNTAJE_ATAQUE +
                ", Defensa: " + PUNTAJE_DEFENSA + ", Resistencia: " + PUNTAJE_RESISTENCIA;
    }
}

