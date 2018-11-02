/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systemkeys.myspa.rest;

import flexjson.JSONSerializer;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.solsistemas.myspa.controller.ControllerCliente;
import org.solsistemas.myspa.controller.ControllerProducto;
import org.solsistemas.myspa.model.Cliente;
import org.solsistemas.myspa.model.Persona;
import org.solsistemas.myspa.model.Producto;
import org.solsistemas.myspa.model.Usuario;


/**
 *
 * @author Darckar
 */
@Path("/")
public class RESTCliente extends Application{
    @GET
    @Path("getAllCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        ControllerCliente cp= new ControllerCliente();
        
        List<Cliente> clientes=null;
        JSONSerializer jss= new JSONSerializer();
        String out= null;
        
        try {
            clientes = cp.getAll("",1);
            out=jss.serialize(clientes);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
    @POST
    @Path("insertCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("apellidoPaterno")@DefaultValue("") String apellidoPaterno,
                           @FormParam("apellidoMaterno")@DefaultValue("") String apellidoMaterno,
                           @FormParam("genero")@DefaultValue("") String genero,
                           @FormParam("domicilio")@DefaultValue("") String domicilio,
                           @FormParam("telefono")@DefaultValue("") String telefono,
                           @FormParam("rfc")@DefaultValue("") String rfc,
                           
                           
                           @FormParam("correo")@DefaultValue("") String correo,
                           @FormParam("nombreUsuario")@DefaultValue("") String nombreUsuario,
                           @FormParam("contrasenia")@DefaultValue("") String contrasenia
                           ){
        
        ControllerCliente cc = new ControllerCliente();
        JSONSerializer jss= new JSONSerializer();
        String out = null;
        Cliente c = new Cliente();
        Persona p = new Persona();
        Usuario u = new Usuario();
        
        try {                                 
           p.setNombre(nombre);
           p.setApellidoPaterno(apellidoPaterno);
           p.setApellidoMaterno(apellidoMaterno);
           p.setDomicilio(domicilio);
           p.setRfc(rfc);
           p.setTelefono(telefono);
           p.setGenero(genero);           
           //Cliente                      
           c.setCorreo(correo);
           c.setEstatus(1);  
           //Usuario           
           u.setNombreUsuario(nombreUsuario);
           u.setContrasenia(contrasenia);
           u.setRol("Cliente");                
           c.setUsuario(u);
           c.setPersona(p);
           
           cc.insert(c);
           
            if(c.getId() > 0)
                out = jss.serialize(c);
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }    
    @POST
    @Path("updateCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
                           @FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("apellidoPaterno")@DefaultValue("") String apellidoPaterno,
                           @FormParam("apellidoMaterno")@DefaultValue("") String apellidoMaterno,
                           @FormParam("genero")@DefaultValue("") String genero,
                           @FormParam("domicilio")@DefaultValue("") String domicilio,
                           @FormParam("telefono")@DefaultValue("") String telefono,
                           @FormParam("rfc")@DefaultValue("") String rfc,
                           @FormParam("correo")@DefaultValue("") String correo,                           
                           @FormParam("nombreUsuario")@DefaultValue("") String nombreUsuario,
                           @FormParam("contrasenia")@DefaultValue("") String contrasenia,
                           @FormParam("idCliente")@DefaultValue("0") int idCliente,
                           @FormParam("idUsuario")@DefaultValue("0") int idUsuario,
                           @FormParam("idPersona")@DefaultValue("0") int idPersona)
                            {
        ControllerCliente cc= new ControllerCliente();        
        String out = null;
        JSONSerializer jss= new JSONSerializer();
        Cliente c = new Cliente();
        Persona p = new Persona();
        Usuario u = new Usuario();      
     // precioUso = 0.5f;
        try {
           p.setNombre(nombre);
           p.setApellidoPaterno(apellidoPaterno);
           p.setApellidoMaterno(apellidoMaterno);
           p.setDomicilio(domicilio);
           p.setRfc(rfc);
           p.setTelefono(telefono);
           p.setGenero(genero);
           p.setId(idPersona);
           //Cliente                      
           c.setCorreo(correo);
           c.setEstatus(1); 
           c.setId(idCliente);
           //Usuario           
           u.setNombreUsuario(nombreUsuario);
           u.setContrasenia(contrasenia);
           u.setRol("Cliente");                
           u.setId(idUsuario);
           c.setUsuario(u);
           c.setPersona(p);
           cc.update(c);
            
            if(c.getId() > 0)
                out = jss.serialize(c);
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @POST
    @Path("deleteCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("idCliente")@DefaultValue("0") int idCliente){
        ControllerCliente cc = new ControllerCliente();        
        String out = null;
        
        try{
          cc.delete(idCliente);
          out = "{\"response:\":\"Eliminado Correctamente.\"}";
        }catch(Exception e){
            e.printStackTrace();
             out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
