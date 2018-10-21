/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systemkeys.myspa.rest;


import flexjson.JSON;
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
import org.solsistemas.myspa.controller.ControllerProducto;
import org.solsistemas.myspa.model.Producto;


/**
 *
 * @author Usuario
 */

 @Path("/")
public class RESTProducto extends Application{   
    @GET
    @Path("getAllProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        ControllerProducto cp= new ControllerProducto();
        
        List<Producto> productos=null;
        JSONSerializer jss= new JSONSerializer();
        String out= null;
        
        try {
            productos=cp.getAll("",1);
            out=jss.serialize(productos);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @POST
    @Path("insertProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("marca")@DefaultValue("") String marca,
                           @FormParam("precioUso")@DefaultValue("0") float precioUso){
        
        ControllerProducto cp= new ControllerProducto();        
        String out = null;
        Producto p = new Producto();
     // precioUso = 0.5f;
        try {
            
            p.setEstatus(1);
            p.setMarca(marca);
            p.setNombre(nombre);
            p.setPrecioUso(precioUso);
            cp.insert(p);
            if(p.getId() > 0)
                out = "{\"result\":" + p.getId() + "}";
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }    

    @POST
    @Path("updateProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("marca")@DefaultValue("") String marca,
                           @FormParam("precioUso")@DefaultValue("0") float precioUso,
                           @FormParam("estatus")@DefaultValue("0") int estatus,
                           @FormParam("idProducto")@DefaultValue("0") int idProducto){
        
        ControllerProducto cp = new ControllerProducto();        
        String out = null;
        Producto p = new Producto();
     // precioUso = 0.5f;
        try {
            
                p.setEstatus(estatus);
                p.setMarca(marca);
                p.setNombre(nombre);
                p.setPrecioUso(precioUso);
                p.setId(idProducto);
                cp.update(p);    
            
            
            if(p.getId() > 0)
                out = "{\"result\":" + p.getId() + "}";
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
        
    @POST
    @Path("deleteProducto")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("idProducto")@DefaultValue("0") int idProducto){
        ControllerProducto cp = new ControllerProducto();        
        String out = null;

        try{
          cp.delete(idProducto);
          out = "{\"response:\":\"Eliminado Correctamente.\"}";
        }catch(Exception e){
            e.printStackTrace();
             out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
                           
}
