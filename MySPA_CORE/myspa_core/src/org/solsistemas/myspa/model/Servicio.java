/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.model;

import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Diego Humberto Castro
 */
public class Servicio {
    private int id;
    private DateTime fecha;
    private int estatus;
    private Reservacion reservacion;
    private Empleado empleado;
    private List<ServicioTratamiento> tratamientos; 

    public DateTime getFecha() {
        return fecha;
    }

    public void setFecha(DateTime fecha) {
        this.fecha = fecha;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Reservacion getReservacion() {
        return reservacion;
    }

    public void setReservacion(Reservacion reservacion) {
        this.reservacion = reservacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<ServicioTratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<ServicioTratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    
}
