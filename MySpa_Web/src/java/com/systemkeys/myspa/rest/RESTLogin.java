/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systemkeys.myspa.rest;

import flexjson.JSONSerializer;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.solsistemas.myspa.controller.ControllerLogin;
import org.solsistemas.myspa.model.Cliente;
import org.solsistemas.myspa.model.Empleado;

/**
 *
 * @author diegg
 */
@Path("/")
public class RESTLogin extends Application{
@GET
@Path("login")
@Produces(MediaType.APPLICATION_JSON)
public Response login(@QueryParam("usuario")@DefaultValue("") String usuario,
                      @QueryParam("contrasenia")@DefaultValue("") String contrasenia){


    ControllerLogin cl = new ControllerLogin();
    JSONSerializer jss = new JSONSerializer();
    String out = null;
    Object o = null;
    Empleado e = null;
    Cliente c = null;
    try{
        o = cl.login(usuario, contrasenia);
        if (o != null){
             if(o instanceof Empleado){
                e = (Empleado) o;
                out = jss.serialize(e);
            }else{
                c = (Cliente) o;
                out = jss.serialize(c);
            }                                
        }else{
            out = "{\"error\":\"Datos de Credencial Incorrectos.\"}";
        }
    }catch(Exception ex){
        ex.printStackTrace();
    }
return Response.status(Response.Status.OK).entity(out).build();  
}
    
}
