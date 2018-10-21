/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.model;

import java.util.List;
/** 
 *  
 * @author diegg
 */ 
public class ServicioTratamiento {
    private int id;
    //private int estatus;
    private Tratamiento tratamiento;
    private List<Producto> productos;
    
    public List<Producto> getProductos() {
        return productos;
    }

    // Ver las relaciones   private Servicio servicio;
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }
    
    
}
