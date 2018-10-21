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
import org.solsistemas.myspa.model.Sucursal;

/**
 *
 * @author Vanessa
 */
public class ControllerSucursal {
   
    /**
    * Inserta un registro de Sucursal en la base de datos.
    * 
    * @param s Es el objeto de tipo {@link Sucursal}, el cual
    *          contiene los datos que seran insertados dentro del nuevo
    *          registro.
    * @return  Devuelve el ID que se genera para la {@link Sucursal}, después de
    *          su inserción.
    * @throws Exception
    */
    
    public int insert(Sucursal s) throws Exception{
        //Definimos la consulta SQL que realiza la inserción del registro:
        String sql = "INSERT INTO sucursal (nombre, domicilio, latitud, longitud, estatus) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        //Aquí guardaremos el ID que se generará
        int idGenerado = -1;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la
        //inserción en la tabla. Debemos especificarle que queremos que nos
        //devuelva el ID que se genera al realizar la inserción del registro:
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        //En este objeto guardamos el resultado de la consulta, la cual
        //nos devolverá los ID's que se generaron. En este caso, solo se
        //generará un ID:
        ResultSet rs = null;
        
        pstmt.setString(1, s.getNombre());
        pstmt.setString(2, s.getDomicilio());
        pstmt.setDouble(3, s.getLatitud());
        pstmt.setDouble(4, s.getLongitud());
        pstmt.setInt(5, 1);
        
        //Ejecutamos la consutla:
        pstmt.executeUpdate();
        
        //Le pedimos al PreparedStatement los valores de las claves generadas,
        //que en este caso, solo es un valor:
        rs = pstmt.getGeneratedKeys();
        
        if(rs.next()){
            idGenerado = rs.getInt(1);
            s.setId(idGenerado);
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el ID generado:
        return idGenerado;  
    }
    
     /**
     * Actualiza un registro de {@link Sucursal}, previamente existente, 
     * en la base de datos.
     * 
     * @param s Es el objeto de tipo {@link Sucursal}, el cual
     *          contiene los datos que seran insertados dentro del nuevo
     *          registro.
     * @throws Exception 
     */
    
    public void update(Sucursal s) throws Exception{
        //Definimos la consulta SQL que realiza la inserción del registro:
        String sql =    "UPDATE sucursal SET nombre = ?, domicilio = ?, latitud = ?, longitud = ?, estatus = ? " + 
                        "WHERE idSucursal = ?";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Establecemos los valores de los parametros de la consulta, basados
        //en los signos de interrogacion:
        pstmt.setString(1, s.getNombre());
        pstmt.setString(2, s.getDomicilio());
        pstmt.setDouble(3, s.getLatitud());
        pstmt.setDouble(4, s.getLongitud());
        pstmt.setInt(5, s.getEstatus());
        pstmt.setInt(6, s.getId());
        
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
        pstmt.close();
        connMySQL.cerrar();
    }
    
    /**
     * Elimina un registro de {@link Sucursal} en la base de datos.
     * 
     * @param id Es el ID de la {@link Sucursal} que se desea eliminar.
     * @throws Exception 
     */
    
    public void delete(int id) throws Exception{
        //Definimos la consulta SQL que realiza la inserción del registro:
        String sql = "UPDATE sucursal SET estatus = 0 WHERE idSucursal = ?";
        
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
     * Busca un registro de {@link Sucursal} en la base de datos, por su ID.
     * 
     * @param id Es el ID de la {@link Sucursal} que se desea buscar.
     * @return  Devuelve la {@link Sucursal} que se encuentra en la base de datos,
     *          basado en la coincidencia del ID (id) pasado como parámetro.
     *          Si no es encontrado una {@link Sucursal} con el ID especificado,
     *          el método devolvera <code>null</code>.
     * @throws Exception 
     */
    
    public Sucursal findById(int id) throws Exception{
        //Definimos la consulta SQL:
        String sql = "SELECT * FROM sucursal WHERE idSucursal = ?";
        
        //Una variable temporal para guardar la Sucursal consultada
        //(si es que se encuentra alguno):
        Sucursal s = null;
        
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
            //Creamos una nueva instancia de Sucursal:
            s = new Sucursal();
            
            //Llenamos sus propiedades:
            s.setEstatus(rs.getInt("estatus"));
            s.setId(rs.getInt("idSucursal"));
            s.setNombre(rs.getString("nombre"));
            s.setDomicilio(rs.getString("domicilio"));
            s.setLongitud(rs.getDouble("longitud"));
            s.setLatitud(rs.getDouble("latitud"));            
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la Sucursal:
        return s;
    }
    
    /**
     * Consulta y devuelve los registros de sucursales encontradas, basados en
     * las coincidencias parciales del valor del parametro <code>filtro</code>.
     * 
     * Los registros encontrados se devuelven en forma de una lista dinamica
     * (List&lt;{@link Sucursal}&rt;) que contiene dentro los objetos de tipo 
     * {@link Sucursal}.
     * 
     * @param filtro    Es el termino de coincidencia parcial que condicionara
     *                  la búsqueda solo a aquellos registros coincidentes con
     *                  el valor especificado.
     * @return  Devuelve el listado de sucursales encontrados 
     *          en la base de datos, en forma de una lista dinamica
     *          <code>List&lt;{@link Sucursal}&rt;</code> basado en la coincidencia 
     *          parcial del <code>filtro</code> pasado como parámetro.
     *          Si la base de datos no tiene algun registro de sucursal, se 
     *          devuelve una lista vacia (NO SE DEVUELVE <code>null</code>!).
     * @throws Exception 
     */
    
    public List<Sucursal> getAll(String filtro, int estatus) throws Exception{
        //Definimos la consulta SQL:
        String sql = "SELECT * FROM sucursal WHERE estatus = ?";
        
        //Aquí guardaremos objetos de tipo Sucursal. Una lista es un "contenedor"
        //dinamico de objetos. En este caso, queremos un contenedor de 
        //"sucursales". En otras palabras, queremos un contenedor que dentro
        //"contenga" objetos de tipo Sucursal:
        List<Sucursal> sucursales = new ArrayList<>();
        
        //Una variable temporal para crear nuevas instancias de Sucursal:
        Sucursal s = null;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //consulta de sucursales:
        PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, estatus);
        //Aquí guardaremos el resultado de la consulta:
        ResultSet rs = pstmt.executeQuery();
        
        //Recorremos el ResultSet, comenzando por el primer registro:
        while (rs.next()){
            //Creamos una nueva instancia de Sucursal:
            s = new Sucursal();
            
            //Llenamos sus propiedades:
            s.setEstatus(rs.getInt("estatus"));
            s.setId(rs.getInt("idSucursal"));
            s.setNombre(rs.getString("nombre"));
            s.setDomicilio(rs.getString("domicilio"));
            s.setLongitud(rs.getDouble("longitud"));
            s.setLatitud(rs.getDouble("latitud"));
            
            //Agregamos la sucursal a la lista:
            sucursales.add(s);
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la lista dinámica con las sucursales generadas al 
        //realizar la consulta en la Base de Datos.
        return sucursales;
    }
}
