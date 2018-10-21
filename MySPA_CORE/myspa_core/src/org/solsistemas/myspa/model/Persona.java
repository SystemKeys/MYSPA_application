/* 
 * Version:         1.0
 * Date:            11/04/2018 10:00:00
 * Author:          Miguel Angel Gil Rios
 * Email:           angel.grios@gmail.com
 * Comments:        Esta clase contiene los datos necesarios para
 *                  describir loas datos personales (Persona) de los 
 *                  empleados (Empleado) y clientes (Cliente) de acuerdo a los 
 *                  requerimientos del sistema "My Spa".
 */
package org.solsistemas.myspa.model;

/**
 *  Esta clase contiene los datos necesarios para
 *  describir loas datos personales (Persona) de los 
 *  empleados (Empleado) y clientes (Cliente) de acuerdo a los 
 *  requerimientos del sistema "My Spa".
 * 
 *  @author LiveGrios
 */
public class Persona
{
    private int id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String genero;
    private String domicilio;
    private String telefono;
    private String rfc;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellidoPaterno()
    {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno)
    {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno()
    {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno)
    {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getGenero()
    {
        return genero;
    }

    public void setGenero(String genero)
    {
        this.genero = genero;
    }
    
    public String getDomicilio()
    {
        return domicilio;
    }

    public void setDomicilio(String domicilio)
    {
        this.domicilio = domicilio;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getRfc()
    {
        return rfc;
    }

    public void setRfc(String rfc)
    {
        this.rfc = rfc;
    }
    
    
}
