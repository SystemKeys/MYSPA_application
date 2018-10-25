package com.systemkeys.myspa.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author diegg
 */
//Nos da el control de todo
@Path("/")
public class RESTPrueba extends Application{
    
    @GET
    @Path("saludar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saludar(){
        
        String out = "{\"result\":\"Hola desde un REST!\"}";
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    
   
    
    //La variable esta anotada para saver que la variable viene de un documento html
    // @DefaultValue : es un valor default por si el usuario no ingresa un valor
    public Response saludarPersonalizado(@QueryParam("txtNombre")@DefaultValue("") String nombre){
        
        String out =null;
        
        if (nombre.isEmpty()) 
             
        out="{\"result\":\"Hola usuario Anonimo!\"}";
        else
        out="{\"result\":\"Hola!"+nombre+"\"}";
        return Response.status(Response.Status.OK).entity(out).build();
        
      
    }
    
      @GET
    @Path("sumar")
    @Produces(MediaType.APPLICATION_JSON)
    
     public Response sumar(@QueryParam("num1")@DefaultValue("0") double n1,
                           @QueryParam("num2")@DefaultValue("0")double n2){
        double r=0;
        String out =null;
            
            r=n1+n2;
        out="{\"result\":\"El resultado de la suma es: "+r+"\"}";
            return Response.status(Response.Status.OK).entity(out).build();
        
      
    }


    
}
