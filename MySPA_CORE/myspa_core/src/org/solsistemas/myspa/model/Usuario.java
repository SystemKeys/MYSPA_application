/* 
 * Version:         1.0
 * Date:            11/04/2018 10:00:00
 * Author:          Miguel Angel Gil Rios
 * Email:           angel.grios@gmail.com
 * Comments:        Esta clase contiene los datos necesarios para
 *                  describir un Usuario, de acuerdo a los requerimientos
 *                  del sistema "My Spa".
 */
package org.solsistemas.myspa.model;
/**
 *  Esta clase contiene los datos necesarios para
 *  describir a un Usuario, de acuerdo a los requerimientos
 *  del sistema "My Spa"
 * 
 *  @author LiveGrios
 */
public class Usuario
{
    private int id;
    private String nombreUsuario;
    private String contrasenia;
    private String rol;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNombreUsuario()
    {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario)
    {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia()
    {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    public String getRol()
    {
        return rol;
    }

    public void setRol(String rol)
    {
        this.rol = rol;
    }
}
