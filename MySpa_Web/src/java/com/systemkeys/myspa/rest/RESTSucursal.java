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
import org.solsistemas.myspa.controller.ControllerProducto;
import org.solsistemas.myspa.controller.ControllerSucursal;
import org.solsistemas.myspa.model.Producto;
import org.solsistemas.myspa.model.Sucursal;

/**
 *
 * @author diegg
 */
@Path("/")
public class RESTSucursal extends Application{
    @GET
    @Path("getAllSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        ControllerSucursal cs = new ControllerSucursal();
        
        List<Sucursal> sucursales = null;
        JSONSerializer jss = new JSONSerializer();
        String out = null;
        
        try {
            sucursales = cs.getAll("",1);
            out=jss.serialize(sucursales);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    } 
    @POST
    @Path("insertSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("domicilio")@DefaultValue("") String domicilio,
                           @FormParam("latitud")@DefaultValue("0.0") double latitud,
                            @FormParam("longitud")@DefaultValue("0.0") double longitud){
        
        ControllerSucursal cs= new ControllerSucursal();        
        String out = null;
        Sucursal s = new Sucursal();
     // precioUso = 0.5f;
        try {
            
            s.setEstatus(1);
            s.setNombre(nombre);
            s.setDomicilio(domicilio);
            s.setLatitud(latitud);
            s.setLongitud(longitud);
            cs.insert(s);
            if(s.getId() > 0)
                out = "{\"result\":" + s.getId() + "}";
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }    

    @POST
    @Path("updateSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("domicilio")@DefaultValue("") String domicilio,
                           @FormParam("latitud")@DefaultValue("0.0") double latitud,
                           @FormParam("longitud")@DefaultValue("0.0") double longitud,
                           @FormParam("estatus")@DefaultValue("0") int estatus,
                           @FormParam("idSucursal")@DefaultValue("0") int idSucursal){
        
        ControllerSucursal cs= new ControllerSucursal();        
        String out = null;
        Sucursal s = new Sucursal();
     // precioUso = 0.5f;
        try {            
            s.setEstatus(estatus);
            s.setNombre(nombre);
            s.setDomicilio(domicilio);
            s.setLatitud(latitud);
            s.setLongitud(longitud);
            s.setId(idSucursal);
            cs.update(s);
            if(s.getId() > 0)
                out = "{\"result\":" + s.getId() + "}";
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
        
    @POST
    @Path("deleteSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("idSucursal")@DefaultValue("0") int idSucursal){
        ControllerSucursal cs = new ControllerSucursal();        
        String out = null;

        try{
          cs.delete(idSucursal);
          out = "{\"response:\":\"Eliminado Correctamente.\"}";
        }catch(Exception e){
            e.printStackTrace();
             out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
