
package org.solsistemas.myspa.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.solsistemas.myspa.db.ConexionMySQL;
import org.solsistemas.myspa.model.Tratamiento;

/**
 *
 * @author ramir
 */
public class ControllerTratamiento {
    /**
     * Inserta un registro de Producto en la base de datos.
     * 
     * @param t Es el objeto de tipo {@link Tratamiento}, el cual
     *          contiene los datos que seran insertados dentro del nuevo
     *          registro.
     * @return  Devuelve el ID que se genera para el {@link Tratamiento}, después de su
     *          insercion.
     * @throws Exception 
     */
    public int insert(Tratamiento t) throws Exception
    {
        //Definimos la consulta SQL que realiza la inserción del registro:
        String sql =    "INSERT INTO tratamiento (nombre, descripcion, estatus ) " + 
                        "VALUES (?, ?, ?)";
        
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
        
        //En este objeto guardaremos el resultado de la consulta, la cual
        //nos devolverá los ID's que se generaron. En este caso, solo se 
        //generará un ID:
        ResultSet rs = null;
        
        pstmt.setString(1, t.getNombre());
        pstmt.setString(2, t.getDescripcion());
        pstmt.setDouble(3, 1);
       
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Le pedimos al PreparedStatement los valores de las claves generados,
        //que en este caso, solo es un valor:
        rs = pstmt.getGeneratedKeys();
        
        if (rs.next())
        {
            idGenerado = rs.getInt(1);
            t.setId(idGenerado);
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el ID generado:
        return idGenerado;
    }
    
    /**
     * Actualiza un registro de {@link Tratamiento}, previamente existente, 
     * en la base de datos.
     * 
     * @param t Es el objeto de tipo {@link Tratamiento}, el cual
     *          contiene los datos que seran insertados dentro del nuevo
     *          registro.
     * @throws Exception 
     */
    public void update(Tratamiento t) throws Exception
    {
        //Definimos la consulta SQL que realiza la inserción del registro:
        String sql =    "UPDATE tratamiento SET nombre = ?,descripcion = ?, estatus = ? " + 
                        "WHERE idTratamiento = ?";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Establecemos los valores de los parametros de la consulta, basados
        //en los signos de interrogacion:
        pstmt.setString(1, t.getNombre());
        pstmt.setString(2, t.getDescripcion());
        pstmt.setDouble(3, t.getEstatus());
        pstmt.setInt(4, t.getId());
        
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
        pstmt.close();
        connMySQL.cerrar();
    }
    
    /**
     * Elimina un registro de {@link Tratamiento} en la base de datos.
     * 
     * @param id Es el ID del {@link Tratamiento} que se desea eliminar.
     * @throws Exception 
     */
    public void delete(int id) throws Exception
    {
        //Definimos la consulta SQL que realiza la inserción del registro:
        String sql =    "update tratamiento SET estatus=0 WHERE idTratamiento = ?";
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //actualizacion en la tabla.
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Establecemos el valor del ID del Producto a dar de baja:       
        pstmt.setInt(1, id);
        
        //Ejecutamos la consulta:
        pstmt.executeUpdate();
        
        //Cerramos todos los objetos de conexión con la B.D.:
        pstmt.close();
        connMySQL.cerrar();
    }
    
    /**
     * Busca un registro de {@link Tratamiento} en la base de datos, por su ID.
     * 
     * @param id Es el ID del {@link Tratamiento} que se desea buscar.
     * @return  Devuelve el {@link Tratamiento} que se encuentra en la base de datos,
     *          basado en la coincidencia del ID (id) pasado como parámetro.
     *          Si no es encontrado un {@link Tratamiento} con el ID especificado,
     *          el método devolvera <code>null</code>.
     * @throws Exception 
     */
    public Tratamiento findById(int id) throws Exception
    {
        //Definimos la consulta SQL:
        String sql = "SELECT * FROM tratamiento WHERE idTratamiento = ?";
        
        //Una variable temporal para guardar la Scursal consultado
        //(si es que se encuentra alguno):
        Tratamiento t = null;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //consulta de productos:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Aquí guardaremos el resultado de la consulta:
        ResultSet rs = null;
        
        //Establecemos el valor del ID del Tratamiento:
        pstmt.setInt(1, id);
        
        //Ejecutamos la consulta:
        rs = pstmt.executeQuery();
        
        //Evaluamos si se devolvio algun registro:
        if (rs.next())
        {
            //Creamos una nueva instancia de Sucursal:
            t = new Tratamiento();
            
            //Llenamos sus propiedades:
            t.setEstatus(rs.getInt("estatus"));
            t.setId(rs.getInt("idTratamiento"));
            t.setDescripcion(rs.getString("descripcion"));  
            t.setNombre(rs.getString("nombre"));            
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos el Tratamiento:
        return t;
    }
    
    /**
     * Consulta y devuelve los registros de productos encontrados, basados en
     * las coincidencias parciales del valor del parametro <code>filtro</code>.
     * 
     * Los registros encontrados se devuelven en forma de una lista dinamica
     * (List&lt;{@link Tratamiento}&rt;) que contiene dentro los objetos de tipo 
     * {@link Tratamiento}.
     * 
     * @param filtro    Es el termino de coincidencia parcial que condicionara
     *                  la búsqueda solo a aquellos registros coincidentes con
     *                  el valor especificado.
     * @return  Devuelve el listado de tratamientos encontrados 
     *          en la base de datos, en forma de una lista dinamica
     *          <code>List&lt;{@link Tratamiento}&rt;</code> basado en la coincidencia 
     *          parcial del <code>filtro</code> pasado como parámetro.
     *          Si la base de datos no tiene algun registro de empleado, se 
     *          devuelve una lista vacia (NO SE DEVUELVE <code>null</code>!).
     * @throws Exception 
     */
    public List<Tratamiento> getAll(String filtro) throws Exception
    {
        //Definimos la consulta SQL:
        String sql = "SELECT * FROM Tratamiento WHERE estatus = 1";
        
        //Aquí guardaremos objetos de tipo Tratamiento. Una lista es un "contenedor"
        //dinamico de objetos. En este caso, queremos un contenedor de 
        //"productos". En otras palabras, queremos un contenedor que dentro
        //"contenga" objetos de tipo Tratamiento:
        List<Tratamiento> tratamientos = new ArrayList<>();
        
        //Una variable temporal para crear nuevas instancias de Producto:
        Tratamiento t = null;
        
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //consulta de productos:
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        //Aquí guardaremos el resultado de la consulta:
        ResultSet rs = pstmt.executeQuery();
        
        //Recorremos el ResultSet, comenzando por el primer registro:
        while (rs.next())
        {
            //Creamos una nueva instancia de Producto:
            t = new Tratamiento();
            
            //Llenamos sus propiedades:
            t.setEstatus(rs.getInt("estatus"));
            t.setId(rs.getInt("idTratamiento"));
            t.setDescripcion(rs.getString("descripcion"));  
            t.setNombre(rs.getString("nombre"));    
            
            //Agregamos el producto a la lista:
            tratamientos.add(t);
        }
        
        //Cerramos todos los objetos de conexión con la B.D.:
        rs.close();
        pstmt.close();
        connMySQL.cerrar();
        
        //Devolvemos la lista dinámica con los productos generados al 
        //realizar la consulta en la Base de Datos.
        return tratamientos;
    }
}

