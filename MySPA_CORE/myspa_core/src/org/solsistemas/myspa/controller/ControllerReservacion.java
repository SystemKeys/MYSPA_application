/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.solsistemas.myspa.db.ConexionMySQL;
import org.solsistemas.myspa.model.Cliente;
import org.solsistemas.myspa.model.Persona;
import org.solsistemas.myspa.model.Reservacion;
import org.solsistemas.myspa.model.Sala;
import org.solsistemas.myspa.model.Sucursal;

/**
 *
 * @author Vanessa
 */
public class ControllerReservacion {
     public int insert(Reservacion r) throws Exception
    {
        //Definimos la consulta SQL que invoca al Stored Procedure:
        String sql =  "INSERT INTO reservacion (fechaHoraInicio,fechaHoraFin,estatus, idCliente, idSala) " + 
                        "VALUES (?, ?, ?,?,?)";
        
        
        //AquÃ­ guardaremoslos ID's que se generarÃ¡n:
        int idGenerado = -1;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexiÃ³n con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        ///Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //inserciÃ³n en la tabla. Debemos especificarle que queremos que nos
        //devuelva el ID que se genera al realizar la inserciÃ³n del registro:
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        
        //En este objeto guardaremos el resultado de la consulta, la cual
        //nos devolverÃ¡ los ID's que se generaron. En este caso, solo se 
        //generarÃ¡ un ID:
        ResultSet rs = null;
        
        //Establecemos los parÃ¡metros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:
         pstmt.setString(1,r.getFechaHoraInicio());
         pstmt.setString(2,r.getFechaHoraFin());
         pstmt.setInt(3, r.getEstatus());
         pstmt.setInt(4, r.getCliente().getId());
         pstmt.setInt(5, r.getSala().getId());
        //Ejecutamos el Stored Procedure:
         pstmt.executeUpdate();
        
       //Le pedimos al PreparedStatement los valores de las claves generados,
        //que en este caso, solo es un valor:
        rs = pstmt.getGeneratedKeys();
        
        if (rs.next())
        {
            idGenerado = rs.getInt(1);
            r.setId(idGenerado);
        }
        
       
        //Cerramos todos los objetos de conexiÃ³n con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el ID generado:
        return idGenerado;
    }
    
    /**
     * Actualiza un registro de {@link Reservacion}, previamente existente, 
     * en la base de datos.
     * 
     * @param r Es el objeto de tipo {@link Reservacion}, el cual
     *          contiene los datos que seran insertados dentro del nuevo
     *          registro.
     * @throws Exception 
     */
    public void update(Reservacion r) throws Exception
    {
          //Definimos la consulta SQL que realiza la inserciÃ³n del registro:
        String sql =    "UPDATE reservacion SET fechaHoraInicio = ?, fechaHoraFin = ?, estatus = ?, idCliente = ?, idSala = ? WHERE idReservacion = ?"; 
                      
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexiÃ³n con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Establecemos los valores de los parametros de la consulta, basados
        //en los signos de interrogacion:
        pstmt.setString(1, r.getFechaHoraInicio());
        pstmt.setString(2, r.getFechaHoraFin());
        pstmt.setInt(3, r.getEstatus());
        pstmt.setInt(4, r.getCliente().getId());
        pstmt.setInt(5, r.getSala().getId());
        pstmt.setInt(6, r.getId());        
        
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexiÃ³n con la B.D.:
        pstmt.close();
        connMySQL.cerrar();
    }
    
    /**
     * Elimina un registro de {@link Reservacion} en la base de datos.
     * 
     * @param id Es el ID del {@link Reservacion} que se desea eliminar.
     * @throws Exception 
     */
    public void delete(int id) throws Exception
    {
      //Definimos la consulta SQL que realiza la inserciÃ³n del registro:
        String sql =    "UPDATE reservacion SET estatus = 0 WHERE idReservacion = ?";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexiÃ³n con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Establecemos el valor del ID del Producto a dar de baja:       
        pstmt.setInt(1, id);
        
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexiÃ³n con la B.D.:
        pstmt.close();
        connMySQL.cerrar();
    }
    
    /**
     * Busca un registro de {@link Reservacion} en la base de datos, por su ID.
     * 
     * @param id Es el ID del {@link Reservacion} que se desea buscar.
     * @return  Devuelve el {@link Reservacion} que se encuentra en la base de datos,
     *          basado en la coincidencia del ID (id) pasado como parÃ¡metro.
     *          Si no es encontrado un {@link Reservacion} con el ID especificado,
     *          el mÃ©todo devolvera <code>null</code>.
     * @throws Exception 
     */
    public Reservacion findById(int id) throws Exception
    {
        //Definimos la consulta SQL:
        String sql = "SELECT * FROM reservacion WHERE idReservacion = ?";
        
        //Una variable temporal para guardar el Producto consultado
        //(si es que se encuentra alguno):
        Reservacion r = null;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexiÃ³n con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //consulta de productos:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //AquÃ­ guardaremos el resultado de la consulta:
        ResultSet rs = null;
        
        //Establecemos el valor del ID del Producto:
        pstmt.setInt(1, id);
        
        //Ejecutamos la consulta:
        rs = pstmt.executeQuery();
        
        //Evaluamos si se devolvio algun registro:
        if (rs.next())
        {
            //Creamos una nueva instancia de Reservacion:
            r = new Reservacion();
            
            //Llenamos sus propiedades:
            
            r.setEstatus(rs.getInt("estatus"));
            r.setId(rs.getInt("idReservacion"));
            r.setFechaHoraFin(rs.getString("fechaHoraFin"));
            r.setFechaHoraInicio(rs.getString("fechaHoraInicio"));
                      
        }
        
        //Cerramos todos los objetos de conexiÃ³n con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el Producto:
        return r;
    }
    
    /**
     * Consulta y devuelve los registros de Reservacion encontrados, basados en
     * las coincidencias parciales del valor del parametro <code>filtro</code>.
     * 
     * Los registros encontrados se devuelven en forma de una lista dinamica
     * (List&lt;{@link Reservacion}&rt;) que contiene dentro los objetos de tipo 
     * {@link Reservacion}.
     * 
     * @param filtro    Es el termino de coincidencia parcial que condicionara
     *                  la bÃºsqueda solo a aquellos registros coincidentes con
     *                  el valor especificado.
     * @return  Devuelve el listado de clientes encontrados 
     *          en la base de datos, en forma de una lista dinamica
     *          <code>List&lt;{@link Cliente}&rt;</code> basado en la coincidencia 
     *          parcial del <code>filtro</code> pasado como parÃ¡metro.
     *          Si la base de datos no tiene algun registro de cliente, se 
     *          devuelve una lista vacia (NO SE DEVUELVE <code>null</code>!).
     * @throws Exception 
     */
    public List<Reservacion> getAll(String filtro, int estatus) throws Exception
    {
        //La consulta SQL a ejecutar:
        String sql = "SELECT * FROM v_reservacion WHERE estatus = ? OR estatus = 2";
        
        //La lista dinÃ¡mica donde guardaremos objetos de tipo Reservacion
        //por cada registro que devuelva la BD:
        List<Reservacion> reservacion = new ArrayList<Reservacion>();
        
        Reservacion r = null;
        //Una variable temporal para crear nuevos objetos de tipo Persona:
        Persona p = null;
        
        //Una variable temporal para crear nuevos objetos de tipo Cliente:
        Cliente c = null;
        
        ////Una variable temporal para crear nuevos objetos de tipo Sala:
        Sala s = null;
        Sucursal su = null;
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexiÃ³n con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la consulta:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // Establecemos filtro de busqueda  
         pstmt.setInt(1, estatus);
        //AquÃ­ guardaremos los resultados de la consulta:
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
            
            //Creamos un nuevo objeto de tipo Empleado:
            c = new Cliente();
            
            //Establecemos sus datos personales:
            c.setId(rs.getInt("idCliente"));
            c.setNumeroUnico(rs.getString("numeroUnico"));           
            c.setCorreo(rs.getString("correo"));
            
            
            r = new Reservacion();
            r.setEstatus(rs.getInt("estatus"));
            r.setFechaHoraInicio(rs.getString("fechaHoraInicio"));
            r.setFechaHoraFin(rs.getString("fechaHoraFin"));
            r.setId(rs.getInt("idReservacion"));
            
            s = new Sala();
            s.setEstatus(rs.getInt("estatusSala"));
            s.setNombre(rs.getString("nombreSala"));
            
            s.setDescripcion(rs.getString("descripcion"));
            s.setId(rs.getInt("idSala"));
            
            su = new Sucursal();
            su.setId(rs.getInt("idSucursal"));
            su.setNombre(rs.getString("nombreSucursal"));
            su.setLatitud(rs.getDouble("latitud"));
            su.setLongitud(rs.getDouble("longitud"));
            su.setDomicilio(rs.getString("domicilioSucursal"));
            su.setEstatus(rs.getInt("estatus"));
            //Establecemos su persona:
            c.setPersona(p);
            r.setCliente(c);
            s.setSucursal(su);
            r.setSala(s);
            
           
            //Agregamos el objeto de tipo Reservacion a la lista dinÃ¡mica:
            reservacion.add(r);
        }
        
        //Cerramos los objetos de BD:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la lista dinÃ¡mica con objetos de tipo Empleado dentro:
        return reservacion;
    }
}


