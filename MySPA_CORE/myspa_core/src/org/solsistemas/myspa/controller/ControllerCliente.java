/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.solsistemas.myspa.model.Persona;
import org.solsistemas.myspa.model.Usuario;
import org.solsistemas.myspa.model.Cliente;

/**
 *
 * @author diegg_
 */
public class ControllerCliente {
    /**
     * Inserta un registro de {@link Cliente} en la base de datos.
     * 
     * @param c Es el objeto de tipo {@link Cliente}, el cual
     *          contiene los datos que seran insertados dentro del nuevo
     *          registro.
     * @return  Devuelve el ID que se genera para el Cliente, después de su
     *          insercion.
     * @throws Exception 
     */
    public int insert(Cliente c) throws Exception
    {
        //Definimos la consulta SQL que invoca al Stored Procedure:
        String sql =    "{call insertarCliente(?, ?, ?, ?, ?, ?, ?, " + //Datos Persona
                                               "?, ?, ?, " +   //Datos Usuario
                                               "?, " +      //Datos Cliente
                                               "?, ?, ?, ?)}"; //Valores de Retorno
        
        //Aquí guardaremoslos ID's que se generarán:
        int idPersonaGenerado = -1;
        int idUsuarioGenerado = -1;
        int idClienteGenerado = -1;
        String numeroUnicoGenerado = "";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto invocaremos al StoredProcedure:
        CallableStatement cstmt = conn.prepareCall(sql);
        
        //Establecemos los parámetros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
        cstmt.setString(1, c.getPersona().getNombre());
        cstmt.setString(2, c.getPersona().getApellidoPaterno());
        cstmt.setString(3, c.getPersona().getApellidoMaterno());
        cstmt.setString(4, c.getPersona().getGenero());
        cstmt.setString(5, c.getPersona().getDomicilio());
        cstmt.setString(6, c.getPersona().getTelefono());
        cstmt.setString(7, c.getPersona().getRfc());
        
        //Establecemos los parámetros de los datos de Usuario:
        cstmt.setString(8, c.getUsuario().getNombreUsuario());
        cstmt.setString(9, c.getUsuario().getContrasenia());
        cstmt.setString(10, c.getUsuario().getRol());
        
        //Establecemos los parámetros de los datos de Cliente:        
        cstmt.setString(11, c.getCorreo());
        
        
        //Registramos los parámetros de salida:
        cstmt.registerOutParameter(12, Types.INTEGER);
        cstmt.registerOutParameter(13, Types.INTEGER);
        cstmt.registerOutParameter(14, Types.INTEGER);
        cstmt.registerOutParameter(15, Types.VARCHAR);
        
        //Ejecutamos el Stored Procedure:
        cstmt.executeUpdate();
        
        //Recuperamos los ID's generados:
        idPersonaGenerado = cstmt.getInt(12);
        idUsuarioGenerado = cstmt.getInt(13);
        idClienteGenerado = cstmt.getInt(14);
        numeroUnicoGenerado = cstmt.getString(15);
        
        //Los guardamos en el objeto Cliente que nos pasaron como parámetro:
        
        c.getPersona().setId(idPersonaGenerado);
        c.getUsuario().setId(idUsuarioGenerado);
        c.setId(idClienteGenerado);
        c.setNumeroUnico(numeroUnicoGenerado);
        //Cerramos los objetos de Base de Datos:
        cstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el ID de Empleado generado:
        return idClienteGenerado;
    }
    
    /**
     * Actualiza un registro de {@link Cliente}, previamente existente, 
     * en la base de datos.
     * 
     * @param c Es el objeto de tipo {@link Cliente}, el cual
     *          contiene los datos que seran insertados dentro del nuevo
     *          registro.
     * @throws Exception 
     */
    public void update(Cliente c) throws Exception
    {
        String sql =    "{call actualizarCliente( ?, ?, ?, ?, ?, ?, ?, " + //Datos Persona
                                                  "?, ?, ?,  " +   //Datos Usuario
                                                  "?, ?, " + //Datos Cliente
                                                  "?, ?, ?)}"; //Valores de Relaciones
        
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
        cstmt.setString(1, c.getPersona().getNombre());
        cstmt.setString(2, c.getPersona().getApellidoPaterno());
        cstmt.setString(3, c.getPersona().getApellidoMaterno());
        cstmt.setString(4, c.getPersona().getGenero());
        cstmt.setString(5, c.getPersona().getDomicilio());
        cstmt.setString(6, c.getPersona().getTelefono());
        cstmt.setString(7, c.getPersona().getRfc());
        
        //Establecemos los parámetros de los datos de Usuario:
        cstmt.setString(8, c.getUsuario().getNombreUsuario());
        cstmt.setString(9, c.getUsuario().getContrasenia());
        cstmt.setString(10,c.getUsuario().getRol());
        
        //Establecemos los parámetros de los datos de Empleado:        
        cstmt.setString(11, c.getCorreo());
        cstmt.setInt(12, c.getEstatus());
        
        //Establecemos las llaves foraneas
        cstmt.setInt(13, c.getPersona().getId());
        cstmt.setInt(14, c.getUsuario().getId());
        cstmt.setInt(15, c.getId());
        
        //Ejecutamos la consulta:
        cstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
        cstmt.close();
        connMySQL.cerrar();
    }
    
    /**
     * Elimina un registro de {@link Cliente} en la base de datos.
     * 
     * @param id Es el ID del {@link Cliente} que se desea eliminar.
     * @throws Exception 
     */
    public void delete(int id) throws Exception
    {
        String sql = "UPDATE cliente SET estatus = 0 WHERE idCliente = ?";
        //delete cliente where idcliente = ?;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Establecemos el valor del ID de la Sucursal a dar de baja:       
        pstmt.setInt(1, id);
        //(1,id)
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
        pstmt.close();
        connMySQL.cerrar();
    }
    
    /**
     * Busca un registro de {@link Cliente} en la base de datos, por su ID.
     * 
     * @param id Es el ID del {@link Cliente} que se desea buscar.
     * @return  Devuelve el {@link Cliente} que se encuentra en la base de datos,
     *          basado en la coincidencia del ID (id) pasado como parámetro.
     *          Si no es encontrado un {@link Cliente} con el ID especificado,
     *          el método devolvera <code>null</code>.
     * @throws Exception 
     */
    public Cliente findById(int id) throws Exception
    {
        //Definimos la consulta SQL:
        String sql = "SELECT * FROM v_clientes WHERE idCliente = ?;";
        
        //Una variable temporal para guardar la Sucursal consultada
        //(si es que se encuentra alguno):
        Cliente c = null;
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
        
        //Establecemos el valor del ID de la Sucursal:
        pstmt.setInt(1, id);
        
        //Ejecutamos la consulta:
        rs = pstmt.executeQuery();
        
        //Evaluamos si se devolvio algun registro:
        if (rs.next()){
            //Creamos una nueva instancia de de todos los objetos necesarios:
            c = new Cliente();
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
           
           //Cliente
           c.setId(rs.getInt("idCliente"));
           c.setNumeroUnico(rs.getString("numeroUnico"));
           c.setCorreo(rs.getString("correo"));
           c.setEstatus(rs.getInt("estatus"));  
           //Usuario
           u.setId(rs.getInt("idUsuario"));
           u.setNombreUsuario(rs.getString("nombreUsuario"));
           u.setContrasenia(rs.getString("contrasenia"));
           u.setRol(rs.getString("rol"));
                
           c.setUsuario(u);
           c.setPersona(p);
            
                 
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el Cliente:
        return c;
       
    }
    
    /**
     * Consulta y devuelve los registros de clientes encontrados, basados en
     * las coincidencias parciales del valor del parametro <code>filtro</code>.
     * 
     * Los registros encontrados se devuelven en forma de una lista dinamica
     * (List&lt;{@link Cliente}&rt;) que contiene dentro los objetos de tipo 
     * {@link Cliente}.
     * 
     * @param filtro    Es el termino de coincidencia parcial que condicionara
     *                  la búsqueda solo a aquellos registros coincidentes con
     *                  el valor especificado.
     * @return  Devuelve el listado de Clientes encontrados 
     *          en la base de datos, en forma de una lista dinamica
     *          <code>List&lt;{@link Cliente}&rt;</code> basado en la coincidencia 
     *          parcial del <code>filtro</code> pasado como parámetro.
     *          Si la base de datos no tiene algun registro de Cliente, se 
     *          devuelve una lista vacia (NO SE DEVUELVE <code>null</code>!).
     * @throws Exception 
     */
    public List<Cliente> getAll(String filtro, int estatus) throws Exception
    {
        //La consulta SQL a ejecutar:
        String sql = "SELECT * FROM v_clientes WHERE estatus = ?";
        
        //La lista dinámica donde guardaremos objetos de tipo Empleado
        //por cada registro que devuelva la BD:
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        //Una variable temporal para crear nuevos objetos de tipo Persona:
        Persona p = null;
        
        //Una variable temporal para crear nuevos objetos de tipo Usuario:
        Usuario u = null;
        
        //Una variable temporal para crear nuevos objetos de tipo Empleado:
        Cliente c = null;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        // Establecemos filtro de busqueda  
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
            c = new Cliente();
            
            //Establecemos sus datos personales:
            
            c.setId(rs.getInt("idCliente"));
            c.setNumeroUnico(rs.getString("numeroUnico"));           
            c.setEstatus(rs.getInt("estatus"));
            c.setCorreo(rs.getString("correo"));
            
            //Establecemos su persona:
            c.setPersona(p);
            
            //Establecemos su Usuario:
            c.setUsuario(u);
            
            //Agregamos el objeto de tipo Empleado a la lista dinámica:
            clientes.add(c);
       
        }
        //Cerramos los objetos de BD:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la lista dinámica con objetos de tipo Empleado dentro:
        return clientes;
    }
    
    
    public Cliente findByNumeroUnico(String numeroUnico) throws Exception
    {
        //La consulta SQL a ejecutar:
        String sql = "SELECT * FROM v_clientes WHERE  numeroUnico = ?";
        
        //La lista dinámica donde guardaremos objetos de tipo Empleado
        //por cada registro que devuelva la BD:        
        
        //Una variable temporal para crear nuevos objetos de tipo Persona:
        Persona p = null;
        
        //Una variable temporal para crear nuevos objetos de tipo Usuario:
        Usuario u = null;
        
        //Una variable temporal para crear nuevos objetos de tipo Empleado:
        Cliente c = null;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = null;
        // Establecemos filtro de busqueda  
         pstmt.setString(1, numeroUnico);
        
        //Aquí guardaremos los resultados de la consulta:
        rs = pstmt.executeQuery();
        
        
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
            c = new Cliente();
            
            //Establecemos sus datos personales:
            
            c.setId(rs.getInt("idCliente"));
            c.setNumeroUnico(rs.getString("numeroUnico"));           
            c.setEstatus(rs.getInt("estatus"));
            c.setCorreo(rs.getString("correo"));
            
            //Establecemos su persona:
            c.setPersona(p);
            
            //Establecemos su Usuario:
            c.setUsuario(u);
            
            //Agregamos el objeto de tipo Empleado a la lista dinámica:                   
        }
        //Cerramos los objetos de BD:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la lista dinámica con objetos de tipo Empleado dentro:
        return c;
    }
    
}