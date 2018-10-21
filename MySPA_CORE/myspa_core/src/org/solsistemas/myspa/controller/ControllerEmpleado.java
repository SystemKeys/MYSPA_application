/*
 *  Version:        1.0
 *  Date:           11/04/2018 13:25:00
 *  Author:         SOL SISTEMAS
 *  Email:          dieg_evw@hotmail.com
 *  Comments:       Esta clase contiene los métodos necesarios para mantener la
 *                  persistencia y consulta de informacion de los empleados 
 *                  en la base de datos.
 */
package org.solsistemas.myspa.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.solsistemas.myspa.db.ConexionMySQL;
import org.solsistemas.myspa.model.Empleado;
import org.solsistemas.myspa.model.Persona;
import org.solsistemas.myspa.model.Usuario;

/**
 *  Esta clase contiene los métodos necesarios para mantener la
 *  persistencia y consulta de informacion de los empleados 
 *  en la base de datos.
 * @author LiveGrios
 */
public class ControllerEmpleado{
    /**
     * Inserta un registro de {@link Empleado} en la base de datos.
     * 
     * @param e Es el objeto de tipo {@link Empleado}, el cual
     *          contiene los datos que seran insertados dentro del nuevo
     *          registro.
     * @return  Devuelve el ID que se genera para el Empleado, después de su
     *          insercion.
     * @throws Exception 
     */
    public int insert(Empleado e) throws Exception {
        //Definimos la consulta SQL que invoca al Stored Procedure:
        String sql =    "{call insertarEmpleado(?, ?, ?, ?, ?, ?, ?, " + //Datos Persona
                                               "?, ?, ?, " +   //Datos Usuario
                                               "?, ?, ?, " + //Datos Empleado
                                               "?, ?, ?, ?)}"; //Valores de Retorno
        
        //Aquí guardaremoslos ID's que se generarán:
        int idPersonaGenerado = -1;
        int idUsuarioGenerado = -1;
        int idEmpleadoGenerado = -1;
        String numEmpleadoGenerado = "";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto invocaremos al StoredProcedure:
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Establecemos los parámetros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString(1, e.getPersona().getNombre());
        cstmt.setString(2, e.getPersona().getApellidoPaterno());
        cstmt.setString(3, e.getPersona().getApellidoMaterno());
        cstmt.setString(4, e.getPersona().getGenero());
        cstmt.setString(5, e.getPersona().getDomicilio());
        cstmt.setString(6, e.getPersona().getTelefono());
        cstmt.setString(7, e.getPersona().getRfc());
        
        //Establecemos los parámetros de los datos de Usuario:
        cstmt.setString(8, e.getUsuario().getNombreUsuario());
        cstmt.setString(9, e.getUsuario().getContrasenia());
        cstmt.setString(10, e.getUsuario().getRol());
        
        //Establecemos los parámetros de los datos de Empleado:        
        cstmt.setString(11, e.getPuesto());
        cstmt.setString(12, e.getFoto());
        cstmt.setString(13, e.getRutaFoto());
        
        
        //Registramos los parámetros de salida:
        cstmt.registerOutParameter(14, Types.INTEGER);
        cstmt.registerOutParameter(15, Types.INTEGER);
        cstmt.registerOutParameter(16, Types.INTEGER);
        cstmt.registerOutParameter(17, Types.VARCHAR);
        
        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();
        
        //Recuperamos los ID's generados:
        idPersonaGenerado = cstmt.getInt(14);
        idUsuarioGenerado = cstmt.getInt(15);
        idEmpleadoGenerado = cstmt.getInt(16);
        numEmpleadoGenerado = cstmt.getString(17);
        
        //Los guardamos en el objeto Empleado que nos pasaron como parámetro:
        e.getPersona().setId(idPersonaGenerado);
        e.getUsuario().setId(idUsuarioGenerado);
        e.setId(idEmpleadoGenerado);
        e.setNumeroEmpleado(numEmpleadoGenerado);
        
        //Cerramos los objetos de Base de Datos:
        cstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el ID de Empleado generado:
        return idEmpleadoGenerado;
    }
    
    /**
     * Actualiza un registro de {@link Empleado}, previamente existente, 
     * en la base de datos.
     * 
     * @param e Es el objeto de tipo {@link Empleado}, el cual
     *          contiene los datos que seran insertados dentro del nuevo
     *          registro.
     * @throws Exception 
     */
    public void update(Empleado e) throws Exception
    {
        String sql =    "{call actualizarEmpleado( ?, ?, ?, ?, ?, ?, ?, " + //Datos Persona 7
                                                  "?, ?, ?,  " +   //Datos Usuario  10
                                                  "?, ?, ?, ?, " + //Datos Empleado  14
                                                  "?, ?, ?)}"; //Valores de Relaciones 17
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Establecemos los valores de los parametros de la consulta, basados
        //en los signos de interrogacion:
        //Datos Persona
        cstmt.setString(1, e.getPersona().getNombre());
        cstmt.setString(2, e.getPersona().getApellidoPaterno());
        cstmt.setString(3, e.getPersona().getApellidoMaterno());
        cstmt.setString(4, e.getPersona().getGenero());
        cstmt.setString(5, e.getPersona().getDomicilio());
        cstmt.setString(6, e.getPersona().getTelefono());
        cstmt.setString(7, e.getPersona().getRfc());
        
        //Establecemos los parámetros de los datos de Usuario:
        cstmt.setString(8, e.getUsuario().getNombreUsuario());
        cstmt.setString(9, e.getUsuario().getContrasenia());
        cstmt.setString(10,e.getUsuario().getRol());
        
        //Establecemos los parámetros de los datos de Empleado:        
        cstmt.setString(11, e.getPuesto());        
        cstmt.setString(12, e.getFoto());
        cstmt.setString(13, e.getRutaFoto());
        cstmt.setInt(14, e.getStatus());
        
        //Establecemos las llaves foraneas
        cstmt.setInt(15, e.getPersona().getId());
        cstmt.setInt(16, e.getUsuario().getId());
        cstmt.setInt(17, e.getId());
        
        //Ejecutamos la consulta:
        cstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
        cstmt.close();
        connMySQL.cerrar();
    }
    
    /**
     * Elimina un registro de {@link Empleado} en la base de datos.
     * 
     * @param id Es el ID del {@link Empleado} que se desea eliminar.
     * @throws Exception 
     */
    public void delete(int id) throws Exception {
        String sql = "UPDATE empleado SET estatus = 0 WHERE idEmpleado = ?";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Establecemos el valor del ID de la Sucursal a dar de baja:       
        pstmt.setInt(1, id);
        
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
        pstmt.close();
        connMySQL.cerrar();
    }
    
    /**
     * Busca un registro de {@link Empleado} en la base de datos, por su ID.
     * 
     * @param id Es el ID del {@link Empleado} que se desea buscar.
     * @return  Devuelve el {@link Empleado} que se encuentra en la base de datos,
     *          basado en la coincidencia del ID (id) pasado como parámetro.
     *          Si no es encontrado un {@link Empleado} con el ID especificado,
     *          el método devolvera <code>null</code>.
     * @throws Exception 
     */
    public Empleado findById(int id) throws Exception
    {
        //Definimos la consulta SQL:
        String sql = "SELECT * FROM v_empleados WHERE idEmpleado = ?;";
        
        //Una variable temporal para guardar la Sucursal consultada
        //(si es que se encuentra alguno):
        Empleado e = null;
        Persona p = null;
        Usuario u = null;
        
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //consulta de productos:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Aquí guardaremos el resultado de la consulta:
        ResultSet rs = null;
        
        //Establecemos el valor del ID del empleado:
        pstmt.setInt(1, id);
        
        //Ejecutamos la consulta:
        rs = pstmt.executeQuery();
        
        //Evaluamos si se devolvio algun registro:
        if (rs.next()){
            //Creamos una nueva instancia de de todos los objetos necesarios:
            e = new Empleado();
            p = new Persona();
            u = new Usuario();
            //Llenamos sus propiedades:
            //Persona  
            p.setId(rs.getInt("idPersona"));           
            p.setNombre(rs.getString("nombre"));
            p.setApellidoPaterno(rs.getString("apellidoPaterno"));
            p.setApellidoMaterno(rs.getString("apellidoMaterno"));
            p.setDomicilio(rs.getString("domicilio"));
            p.setRfc(rs.getString("rfc"));
            p.setTelefono(rs.getString("telefono"));
            p.setGenero(rs.getString("genero"));
            //Empleado
            e.setId(rs.getInt("idEmpleado"));
            e.setFoto(rs.getString("foto"));
            e.setFoto(rs.getString("rutaFoto"));
            e.setStatus(rs.getInt("estatus"));
            e.setNumeroEmpleado(rs.getString("numeroEmpleado"));
            e.setPuesto(rs.getString("puesto"));
            //Usuario
            u.setId(rs.getInt("idUsuario"));
            u.setNombreUsuario(rs.getString("nombreUsuario"));
            u.setContrasenia(rs.getString("contrasenia"));
            u.setRol(rs.getString("rol"));

            e.setUsuario(u);
            e.setPersona(p);
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la Sucursal:
        return e;
       
    }
    
    /**
     * Consulta y devuelve los registros de empleados encontrados, basados en
     * las coincidencias parciales del valor del parametro <code>filtro</code>.
     * 
     * Los registros encontrados se devuelven en forma de una lista dinamica
     * (List&lt;{@link Empleado}&rt;) que contiene dentro los objetos de tipo 
     * {@link Empleado}.
     * 
     * @param filtro    Es el termino de coincidencia parcial que condicionara
     *                  la búsqueda solo a aquellos registros coincidentes con
     *                  el valor especificado.
     * @return  Devuelve el listado de empleados encontrados 
     *          en la base de datos, en forma de una lista dinamica
     *          <code>List&lt;{@link Empleado}&rt;</code> basado en la coincidencia 
     *          parcial del <code>filtro</code> pasado como parámetro.
     *          Si la base de datos no tiene algun registro de empleado, se 
     *          devuelve una lista vacia (NO SE DEVUELVE <code>null</code>!).
     * @throws Exception 
     */
    public List<Empleado> getAll(String filtro, int estatus) throws Exception{
        //La consulta SQL a ejecutar:
        String sql = "SELECT * FROM v_empleados WHERE estatus = ?" ;
        
        //La lista dinámica donde guardaremos objetos de tipo Empleado
        //por cada registro que devuelva la BD:
        List<Empleado> empleados = new ArrayList<Empleado>();
        
        //Una variable temporal para crear nuevos objetos de tipo Persona:
        Persona p = null;
        
        //Una variable temporal para crear nuevos objetos de tipo Usuario:
        Usuario u = null;
        
        //Una variable temporal para crear nuevos objetos de tipo Empleado:
        Empleado e = null;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);
     
        //Establemos el filtro de la consulta
        pstmt.setInt(1, estatus);
        
        //Aquí guardaremos los resultados de la consulta:
        ResultSet rs = pstmt.executeQuery();

        //Iteramos el conjunto de registros devuelto por la BD.
        //"Si hay un siguiente registro, nos movemos":
        while (rs.next())
        {
            //Creamos un nuevo objeto de tipo Persona:
            p = new Persona();
            
            //Llenamos sus datos:
            p.setApellidoMaterno(rs.getString("apellidoMaterno"));
            p.setApellidoPaterno(rs.getString("apellidoPaterno"));
            p.setDomicilio(rs.getString("domicilio"));
            p.setGenero(rs.getString("genero"));
            p.setId(rs.getInt("idPersona"));
            p.setNombre(rs.getString("nombre"));
            p.setRfc(rs.getString("rfc"));
            p.setTelefono(rs.getString("telefono"));
            
            //Creamos un nuevo objeto de tipo Usuario:
            u = new Usuario();
            u.setContrasenia(rs.getString("contrasenia"));
            u.setId(rs.getInt("idUsuario"));
            u.setNombreUsuario(rs.getString("nombreUsuario"));
            u.setRol(rs.getString("rol"));
            
            //Creamos un nuevo objeto de tipo Empleado:
            e = new Empleado();
            
            //Establecemos sus datos personales:
            e.setFoto(rs.getString("foto"));
            e.setId(rs.getInt("idEmpleado"));
            e.setNumeroEmpleado(rs.getString("numeroEmpleado"));           
            e.setStatus(rs.getInt("estatus"));
            e.setPuesto(rs.getString("puesto"));
            e.setRutaFoto(rs.getString("rutaFoto"));
            
            
            //Establecemos su persona:
            e.setPersona(p);
            
            //Establecemos su Usuario:
            e.setUsuario(u);
            
            //Agregamos el objeto de tipo Empleado a la lista dinámica:
            empleados.add(e);
        }
        
        //Cerramos los objetos de BD:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la lista dinámica con objetos de tipo Empleado dentro:
        return empleados;
    }
}
