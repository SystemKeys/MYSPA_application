package org.solsistemas.myspa.model;

/**
 *
 * @author diegg
 */
public class Tratamiento {
 private int id;
 private String nombre;
 private String descripcion;
 private int estatus;

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
 
 // agregar estatus
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
 
 
}
