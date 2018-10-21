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
import org.solsistemas.myspa.controller.ControllerSala;
import org.solsistemas.myspa.model.Producto;
import org.solsistemas.myspa.model.Sala;
import org.solsistemas.myspa.model.Sucursal;

/**
 *
 * @author diegg
 */
@Path("/")
public class RESTSala extends Application {
    @GET
    @Path("getAllSala")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        ControllerSala cs = new ControllerSala();
        
        List<Sala> salas = null;
        JSONSerializer jss = new JSONSerializer();
        String out = null;
        
        try {
            salas = cs.getAll("",1);
            out = jss.serialize(salas);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    } 
@POST
    @Path("insertSala")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("descripcion")@DefaultValue("") String descripcion,
                           @FormParam("foto")@DefaultValue("") String foto,
                           @FormParam("rutaFoto")@DefaultValue("") String rutaFoto,
                           @FormParam("idSucursal")@DefaultValue("0")int idSucursal)
                            {
        
        ControllerSala cs= new ControllerSala();        
        String out = null;
        Sala s = new Sala();
        Sucursal su = new Sucursal();
     // precioUso = 0.5f;
        try {            
            s.setEstatus(1);
            s.setNombre(nombre);
            s.setDescripcion(descripcion);
            s.setFoto(foto);
            s.setRutaFoto(rutaFoto);
            su.setId(idSucursal);
            s.setSucursal(su);
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
    @Path("updateSala")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("nombre")@DefaultValue("") String nombre,
                           @FormParam("descripcion")@DefaultValue("") String descripcion,
                           @FormParam("foto")@DefaultValue("") String foto,
                           @FormParam("rutaFoto")@DefaultValue("") String rutaFoto,
                           @FormParam("idSucursal")@DefaultValue("0") int idSucursal,
                           @FormParam("estatus")@DefaultValue("0") int estatus,
                           @FormParam("idSala")@DefaultValue("0") int idSala)                           
                            {
        
        ControllerSala cs = new ControllerSala();        
        String out = null;
        Sala s = new Sala();
        Sucursal su = new Sucursal();
     // precioUso = 0.5f;
        try {
            
                s.setEstatus(estatus);
                s.setNombre(nombre);
                s.setDescripcion(descripcion);
                s.setFoto(foto);
                s.setRutaFoto(rutaFoto);
                su.setId(idSucursal);
                s.setId(idSala);
                s.setSucursal(su);
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
    @Path("deleteSala")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("idSala")@DefaultValue("0") int idSala){
        ControllerSala cs = new ControllerSala();        
        String out = null;

        try{
          cs.delete(idSala);
          out = "{\"response:\":\"Eliminado Correctamente.\"}";
        }catch(Exception e){
            e.printStackTrace();
             out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
                           
}