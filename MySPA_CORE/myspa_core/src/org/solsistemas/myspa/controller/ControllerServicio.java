package org.solsistemas.myspa.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.solsistemas.myspa.db.ConexionMySQL;
import org.solsistemas.myspa.model.Producto;
import org.solsistemas.myspa.model.Servicio;
import org.solsistemas.myspa.model.ServicioTratamiento;

/**
 *
 * @author diegg
 */
public class ControllerServicio {
    //no deja cambiar el valor del objeto
    public static final DateTimeFormatter DTF = DateTimeFormat.forPattern("yyyy/MM/ss HH:mm:ss");
    
    public int insert(Servicio s) throws Exception{
        String sqlServicio = "INSERT INTO servicio(fecha, idReservacion, idEmpleado)" +
                             "VALUES(?,?,?)";
        String sqlServicioTratamiento = "INSERT INTO servicio_tratamiento(idTratamiento, idServicio) " +
                                        "VALUES(?,?)";
        String sqlServicioTratamientoProducto = "INSERT INTO servicio_tratamiento_producto " + 
                                                        "(idServicioTratamiento, idProducto, precioUso) " +
                                                        "VALUES(?,?,?)";
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.abrir();
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        
        ResultSet rs = null;
        
        int cont = 0;
        List<ServicioTratamiento> tratamientos = null;
        List<Producto> productos = null;
        //Deshabilitamos la persistencia automatica de datos:
        conn.setAutoCommit(false);        
        try{
            //Generamos un prepared statement que ejecutara la consulta y le indicamos que nos devuelva
            // los id´s generados
            pstmt1 = conn.prepareStatement(sqlServicio, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt1.setString(1, DTF.print(s.getFecha()));
            pstmt1.setInt(2, s.getReservacion().getId());
            pstmt1.setInt(3, s.getEmpleado().getId());
            
            //Ejecutamos la consulta
            pstmt1.executeUpdate();
            //Recuperamos el id generado
            rs = pstmt1.getGeneratedKeys();
            rs.next();
            s.setId(rs.getInt(1));
            rs.close();
            
            //Preparamos la segunda consulta:
            pstmt2 = conn.prepareStatement(sqlServicioTratamiento, PreparedStatement.RETURN_GENERATED_KEYS);
            //Preparamos un lote ejecuciones SQL:
            for(int i = 0; i < s.getTratamientos().size(); i++){
                //Llenamos los datos del prepared statement
                pstmt2.setInt(1, s.getTratamientos().get(i).getId());
                pstmt2.setInt(2, s.getId());
                //agregamos la consulta al lote
                pstmt2.addBatch();          
            }
            //Ejecutamos todo el LOTE de instrucciones
            pstmt2.executeBatch();
            
            //Recuperamos los ID´s generados:
            rs = pstmt2.getGeneratedKeys();
            
            while(rs.next()){
                s.getTratamientos().get(cont).setId(rs.getInt(1));
                cont++;
                // s.getTratamientos().get(cont++).setId(rs.getInt(1));
                //cont++ ejecuta la instruccion y luego suma
            }
            //Cerramos el resultset
            rs.close();
            pstmt3 = conn.prepareStatement(sqlServicioTratamientoProducto);
            tratamientos = s.getTratamientos();
            //por cada servicio tratamiento en la lista de servicio tratamiento
            //for(ServicioTratamiento st : s.getTratamientos()){                 
            //}            
            //Recorremos la lista que contiene los servicioTratamiento
            for(int i = 0; i < tratamientos.size(); i++){
                //Recuperamos el servicio tratamiento de la posicion i 
                //y le pedimos sus productos
                productos = tratamientos.get(i).getProductos();
                //Recorremos la lista de productos
                for(int j = 0; j < productos.size(); j++){
                    //Llenamos los parametros del prepared statement
                     pstmt3.setInt(1, tratamientos.get(i).getId());
                     pstmt3.setInt(2, productos.get(j).getId());
                     pstmt3.setFloat(3, productos.get(j).getPrecioUso());
                     //Agregamos la consulta al LOTE
                     pstmt3.addBatch();
                }
            }                        
            //Ejecutamos el LOTE de instrucciones
            pstmt3.executeBatch();
            //Persistimos los cambios realizados en la BD:
            conn.commit();
        }catch(Exception e){
            //Si algo falla, imprimimos la exception
            e.printStackTrace();
            //hacemos un rollback
            conn.rollback();
            //cerramos la conexion
            connMySQL.cerrar();
            //Lanzamos la excepcion
            throw e;
                        
        }
        conn.setAutoCommit(true);
        pstmt1.close();
        pstmt2.close();
        pstmt3.close();
        connMySQL.cerrar();
        
        return s.getId();
    } 
}
