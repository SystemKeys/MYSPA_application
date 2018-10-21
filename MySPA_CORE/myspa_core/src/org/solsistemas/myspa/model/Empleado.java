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
 *  @author LiveGrios
 */
public class Empleado
{
    private int id;
    private String numeroEmpleado;
    private String puesto;
    private int status;
    private String foto;
    private String rutaFoto;
    private Persona persona;
    private Usuario usuario;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNumeroEmpleado()
    {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado)
    {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getPuesto()
    {
        return puesto;
    }

    public void setPuesto(String puesto)
    {
        this.puesto = puesto;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    public String getRutaFoto()
    {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto)
    {
        this.rutaFoto = rutaFoto;
    }

    public Persona getPersona()
    {
        return persona;
    }

    public void setPersona(Persona persona)
    {
        this.persona = persona;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    
}
