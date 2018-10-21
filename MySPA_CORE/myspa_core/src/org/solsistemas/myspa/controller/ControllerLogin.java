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
import org.solsistemas.myspa.model.Cliente;
import org.solsistemas.myspa.model.Empleado;
import org.solsistemas.myspa.model.Persona;
import org.solsistemas.myspa.model.Usuario;

/**
 *
 * @author diegg
 */
public class ControllerLogin {

public Object login(String usuario, String password) throws Exception{
    
        String sql1 = "SELECT * FROM v_empleados " + 
                      "WHERE nombreusuario = ? AND contrasenia = ? AND estatus = 1";
        String sql2 = "SELECT * FROM v_clientes " +
                      "WHERE nombreUsuario = ? AND contrasenia = ? AND estatus = 1";
        //Con este objeto nos vamos a conectar a la Base de Datos:
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrimos la conexión con la Base de Datos:
        Connection conn = connMySQL.abrir();
        
        //Con este objeto ejecutaremos la sentencia SQL que realiza la
        //inserción en la tabla. Debemos especificarle que queremos que nos
        //devuelva el ID que se genera al realizar la inserción del registro:
        PreparedStatement pstmt = conn.prepareStatement(sql1);
     
        ResultSet rs = null;
        
        Cliente c = null;
        Empleado e = null;
        Persona p = null;
        Usuario u = null;
        
        pstmt.setString(1, usuario);
        pstmt.setString(2, password);
        
        rs = pstmt.executeQuery();
        //Validamos si hay un registro
        if(rs.next()){
            e = new Empleado();
            p = new Persona();
            u = new Usuario();
            
            p.setId(rs.getInt("idPersona"));
            p.setApellidoPaterno(rs.getString("apellidoPaterno"));
            p.setApellidoMaterno(rs.getString("apellidoMaterno"));
            p.setDomicilio(rs.getString("domicilio"));
            p.setGenero(rs.getString("genero"));
            p.setNombre(rs.getString("nombre"));
            p.setRfc(rs.getString("rfc"));
            p.setTelefono(rs.getString("telefono"));
            
            u.setId(rs.getInt("idUsuario"));
            u.setContrasenia(rs.getString("contrasenia"));
            u.setNombreUsuario(rs.getString("nombreUsuario"));
            u.setRol(rs.getString("rol"));
            
            e.setId(rs.getInt("idEmpleado"));
            e.setFoto(rs.getString("foto"));
            e.setNumeroEmpleado(rs.getString("numeroEmpleado"));
            e.setPuesto(rs.getString("puesto"));
            e.setRutaFoto(rs.getString("rutaFoto"));
            e.setStatus(rs.getInt("estatus"));
            
            e.setPersona(p);
            e.setUsuario(u);
        }
        rs.close();
        pstmt.close();
        
        if(e != null){
            connMySQL.cerrar();
            return e;
        }
        
        pstmt = conn.prepareStatement(sql2);
        pstmt.setString(1, usuario);
        pstmt.setString(2, password);
        rs = pstmt.executeQuery();
        
        if(rs.next()){
            c = new Cliente();
            p = new Persona();
            u = new Usuario();
            
            p.setId(rs.getInt("idPersona"));
            p.setApellidoPaterno(rs.getString("apellidoPaterno"));
            p.setApellidoMaterno(rs.getString("apellidoMaterno"));
            p.setDomicilio(rs.getString("domicilio"));
            p.setGenero(rs.getString("genero"));
            p.setNombre(rs.getString("nombre"));
            p.setRfc(rs.getString("rfc"));
            p.setTelefono(rs.getString("telefono"));
            
            u.setId(rs.getInt("idUsuario"));
            u.setContrasenia(rs.getString("contrasenia"));
            u.setNombreUsuario(rs.getString("nombreUsuario"));
            u.setRol(rs.getString("rol"));
            
            c.setId(rs.getInt("idCliente"));
            c.setNumeroUnico(rs.getString("numeroUnico"));           
            c.setEstatus(rs.getInt("estatus"));
            c.setCorreo("correo");
            
            
            c.setPersona(p);
            c.setUsuario(u);
            rs.close();
            pstmt.close();
            connMySQL.cerrar();
            return c;
        }
            return null;        
        
  
    
}    
}
