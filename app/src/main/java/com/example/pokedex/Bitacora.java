package com.example.pokedex;

public class Bitacora {
    private int idBitacora;
    private String nombre;
    private String accion;
    private String fecha;


    public Bitacora() {
    }


    public Bitacora(int idBitacora, String nombre, String accion, String fecha) {
        this.idBitacora = idBitacora;
        this.nombre = nombre;
        this.accion = accion;
        this.fecha = fecha;
    }


    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Bitacora{" +
                "idBitacora=" + idBitacora +
                ", nombre='" + nombre + '\'' +
                ", accion='" + accion + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
