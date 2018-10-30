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
import org.solsistemas.myspa.db.ConexionMySQL;
import org.solsistemas.myspa.model.Horario;
import org.solsistemas.myspa.model.Sala;

/**
 *
 * @author diegg
 */
public class ControllerSalaHorario {
 
    public void insert(Sala s, Horario h) throws Exception{
        String sql = "INSERT INTO sala_horario VALUES (?,?)";
              
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexiÃ³n con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        ///Con este objeto ejecutaremos la sentencia SQL que realiza la 
        //inserciÃ³n en la tabla. Debemos especificarle que queremos que nos
        //devuelva el ID que se genera al realizar la inserciÃ³n del registro:
        PreparedStatement pstmt = conn.prepareStatement(sql);   
        //Establecemos los parÃ¡metros de los datos personales en el orden
        //en que los pide el procedimiento almacenado, comenzando en 1:         

         pstmt.setInt(1, s.getId());
         pstmt.setInt(2, h.getId());
        //Ejecutamos el Stored Procedure:
         pstmt.executeUpdate();
    
        pstmt.close();
        connMySQL.cerrar();                               
    }
}
