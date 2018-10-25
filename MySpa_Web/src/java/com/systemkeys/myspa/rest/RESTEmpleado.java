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
import org.solsistemas.myspa.controller.ControllerEmpleado;
import org.solsistemas.myspa.model.Empleado;
import org.solsistemas.myspa.model.Persona;
import org.solsistemas.myspa.model.Usuario;

/**
 *
 * @author diegg
 */
@Path("/")
public class RESTEmpleado extends Application{
    @GET
    @Path("getAllEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        ControllerEmpleado cp = new ControllerEmpleado();
        
        List<Empleado> empleados=null;
        JSONSerializer jss= new JSONSerializer();
        String out= null;
        
        try {
            empleados = cp.getAll("",1);
            out=jss.serialize(empleados);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
    @POST
    @Path("insertEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("apellidoPaterno")@DefaultValue("") String apellidoPaterno,
                           @FormParam("apellidoMaterno")@DefaultValue("") String apellidoMaterno,
                           @FormParam("genero")@DefaultValue("") String genero,
                           @FormParam("domicilio")@DefaultValue("") String domicilio,
                           @FormParam("telefono")@DefaultValue("") String telefono,
                           @FormParam("rfc")@DefaultValue("") String rfc,
                           @FormParam("puesto")@DefaultValue("") String puesto,
                           @FormParam("foto")@DefaultValue("") String foto,                                                       
                           @FormParam("rutaFoto")@DefaultValue("") String rutaFoto,                           
                         
                           @FormParam("nombreUsuario")@DefaultValue("") String nombreUsuario,
                           @FormParam("contrasenia")@DefaultValue("") String contrasenia,
                           @FormParam("rol")@DefaultValue("") String rol
                           ){
        
        ControllerEmpleado ce = new ControllerEmpleado();        
        String out = null;
        Empleado e = new Empleado();
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
           //Cliente                      
           e.setPuesto(puesto);
           e.setRutaFoto(rutaFoto);
           e.setFoto(foto);
           e.setStatus(1);  
           //Usuario           
           u.setNombreUsuario(nombreUsuario);
           u.setContrasenia(contrasenia);
           u.setRol(rol);                
           e.setUsuario(u);
           e.setPersona(p);
           
           ce.insert(e);
            if(e.getId() > 0)
                out = "{\"result\":" + e.getId() + "}";
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"exception:\":\"" + ex.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }    
    @POST
    @Path("updateEmpleado")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
                           @FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("apellidoPaterno")@DefaultValue("") String apellidoPaterno,
                           @FormParam("apellidoMaterno")@DefaultValue("") String apellidoMaterno,
                           @FormParam("genero")@DefaultValue("") String genero,
                           @FormParam("domicilio")@DefaultValue("") String domicilio,
                           @FormParam("telefono")@DefaultValue("") String telefono,
                           @FormParam("rfc")@DefaultValue("") String rfc,
                           @FormParam("puesto")@DefaultValue("") String puesto,
                           @FormParam("foto")@DefaultValue("") String foto,                                                       
                           @FormParam("rutaFoto")@DefaultValue("") String rutaFoto,                           
                           @FormParam("nombreUsuario")@DefaultValue("") String nombreUsuario,
                           @FormParam("contrasenia")@DefaultValue("") String contrasenia,
                           @FormParam("rol")@DefaultValue("") String rol,
                           @FormParam("idEmpleado")@DefaultValue("0") int idEmpleado,
                           @FormParam("idUsuario")@DefaultValue("0") int idUsuario,
                           @FormParam("idPersona")@DefaultValue("0") int idPersona){
                
        String out = null;
        ControllerEmpleado ce = new ControllerEmpleado();
        Empleado e = new Empleado();
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
           //Empleado                      
           e.setPuesto(puesto);
           e.setRutaFoto(rutaFoto);
           e.setFoto(foto);
           e.setStatus(1);  
           e.setId(idEmpleado);
           //Usuario           
           u.setNombreUsuario(nombreUsuario);
           u.setContrasenia(contrasenia);
           u.setRol(rol);                
           u.setId(idUsuario);
           e.setUsuario(u);
           e.setPersona(p);
           ce.update(e);
            
            if(e.getId() > 0)
                out = "{\"result\":" + e.getId() + "}";
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"exception:\":\"" + ex.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
    
@POST
@Path("deleteEmpleado")
@Produces(MediaType.APPLICATION_JSON)
public Response delete(@FormParam("idEmpleado")@DefaultValue("0") int idEmpleado){
    ControllerEmpleado ce = new ControllerEmpleado();        
    String out = null;
   
    try{
        ce.delete(idEmpleado);
        out = "{\"response:\":\"Eliminado Correctamente.\"}";
    }catch(Exception e){
        e.printStackTrace();
        out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    } 
}
