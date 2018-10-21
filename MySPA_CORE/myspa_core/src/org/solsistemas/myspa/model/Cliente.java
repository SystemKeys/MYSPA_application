/*
 *  Version:        1.0
 *  Date:           11/04/2018 10:00:00
 *  Author:         Diego Castro
 *  Email:          dieg_evw@hotmail.com
 *  Comments:       Esta clase contiene los datos necesarios para
 *                  describir a un Empleado, de acuerdo a los requerimientos
 *                  del sistema "My Spa"
 */
package org.solsistemas.myspa.model;

/**
 *  Esta clase contiene los datos necesarios para
 *  describir a un Empleado, de acuerdo a los requerimientos
 *  del sistema "My Spa"
 * 
 *  @author Diego Castro
 */
public class Cliente {
    private int id;
    private int estatus;
    private String correo;
    private String numeroUnico;
    private Usuario usuario;
    private Persona persona;

    public String getNumeroUnico() {
        return numeroUnico;
    }

    public void setNumeroUnico(String numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return persona.getNombre()+" "+persona.getApellidoPaterno()+ " "+persona.getApellidoMaterno();
    }
    
    
}
