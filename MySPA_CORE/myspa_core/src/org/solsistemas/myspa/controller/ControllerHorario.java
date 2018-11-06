/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solsistemas.myspa.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.solsistemas.myspa.db.ConexionMySQL;
import org.solsistemas.myspa.model.Horario;
import org.solsistemas.myspa.model.Reservacion;

/**
 *
 * @author diegg
 */
public class ControllerHorario {
    
    public List<Horario> getAllWithoutUsed(int salaId, String fecha) throws Exception{        
        String sql = "SELECT * FROM horario";
        String sqlOcupados = "SELECT * FROM horarios_ocupados WHERE idSala = ?  and FechaHoraInicio like ?;";                        
        
        List<Horario> horariosOcupados = new ArrayList<>();
        List <Horario> horarios = new ArrayList<>();
        
        Horario h = null;
        Horario hO = null;
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //consulta de horarios:
        PreparedStatement pstmt = conn.prepareStatement(sql);                
        ResultSet rs = pstmt.executeQuery();        
        //Recorremos el ResultSet, comenzando por el primer registro:
        while (rs.next()){
            //Creamos una nueva instancia de Horario:                        
           h = new Horario();            
           //Llenamos sus propiedades:
           h.setId(rs.getInt("idHorario"));
           h.setHoraInicio(rs.getString("horaInicio"));
           h.setHoraFin(rs.getString("horaFin"));                        
           //Agregamos el horario a la lista:
           horarios.add(h);
        }
         rs.close();
         pstmt.close();
        //prepared statement para obtener los horarios ocupados:
        PreparedStatement pstmtHO = conn.prepareStatement(sqlOcupados);   
        //establecemos los valores necesarios para la consulta
        pstmtHO.setInt(1, salaId);     
        pstmtHO.setString(2, fecha);
        ResultSet rsHO = pstmtHO.executeQuery(); 
        //Recorremos el resulSet
        while(rsHO.next()){
            //creamos el objeto en donde se guardaran los valores
            hO = new Horario();
            //establecemos la propiedad que necesitamos
             hO.setId(rsHO.getInt("idHorario"));
            //Agregamos el horario Ocupado a la lista:
            horariosOcupados.add(hO);
        }
        for(int i = 0; i < horariosOcupados.size(); i++){
            for(int j = 0; j < horarios.size(); j++){
                if(horarios.get(j).getId() == horariosOcupados.get(i).getId()){
                    horarios.remove(j);                
                    }
                }    
            }        
        //Cerramos todos los objetos de conexión con la B.D.:
       
        rsHO.close();
        pstmtHO.close();
        
        connMySQL.cerrar();        
        //Devolvemos la lista dinámica con los productos generados al 
        //realizar la consulta en la Base de Datos.
        return horarios;
    }
    /*
    public List<Horario> getAll(String filtro, int estatus) throws Exception{
          String sql = "SELECT H.* , SH.idSala FROM horario H left JOIN sala_horario SH ON SH.idHorario = H.idHorario where SH.idSala = 1;";
          return 7;
    }
*/
}