
package com.systemkeys.myspa.rest;

import flexjson.JSONSerializer;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.solsistemas.myspa.controller.ControllerTratamiento;
import org.solsistemas.myspa.model.Tratamiento;

/**
 *
 * @author ramir
 */
@Path("/")
public class RESTTratamiento extends Application{
    @GET
    @Path("getAllTratamiento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        ControllerTratamiento cp = new ControllerTratamiento();
        List<Tratamiento> tratamientos = null;
        JSONSerializer jss = new JSONSerializer();
        String out = null;
        try{
            tratamientos = cp.getAll("");
            out = jss.serialize(tratamientos);
        }
        catch (Exception e){
            e.printStackTrace();
            out = "{\"error:\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @POST
    @Path("insertTratamiento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("nombre") @DefaultValue("") String nombre,
                           @FormParam("descripcion") @DefaultValue("") String descripcion){
        ControllerTratamiento ct = new ControllerTratamiento();
        String out = null;
        Tratamiento t = new Tratamiento();
        try{
            t.setNombre(nombre);
            t.setDescripcion(descripcion);
            t.setEstatus(1);
            
            ct.insert(t);
            if(t.getId()>0)
                out = "{\"result:\":" + t.getId() + "}";
            else
                out = "{\"error:\":\"Movimiento realizado.\"}";
        }
        catch (Exception e){
            e.printStackTrace();
            out = "{\"error:\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
//    
    @POST
    @Path("updateTratamiento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("nombre") @DefaultValue("") String nombre,
                           @FormParam("descripcion") @DefaultValue("") String descripcion,
                           @FormParam("idTratamiento") @DefaultValue("0") int idTratamiento){
        ControllerTratamiento ct = new ControllerTratamiento();
        String out = null;
        Tratamiento t = new Tratamiento();
        try{
            t.setId(idTratamiento);
            t.setNombre(nombre);
            t.setDescripcion(descripcion);
            t.setEstatus(1);
            
            ct.update(t);
            if(t.getId()>0)
                out = "{\"result:\":" + t.getId() + "}";
            else
                out = "{\"error:\":\"Movimiento realizado.\"}";
        }
        catch (Exception e){
            e.printStackTrace();
            out = "{\"error:\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @POST
    @Path("deleteTratamiento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("idTratamiento") @DefaultValue("0") int idTratamiento) {
        ControllerTratamiento cp = new ControllerTratamiento();
        String out = null;
        Tratamiento t = new Tratamiento();
        try{
            t.setEstatus(0);
            t.getNombre();
            t.getDescripcion();
            t.setId(idTratamiento);
            
            cp.delete(idTratamiento);
            if(t.getId()>0)
                out = "{\"result:\":" + t.getId() + "}";
            else
                out = "{\"error:\":\"Movimiento realizado.\"}";
        }
        catch (Exception e){
            e.printStackTrace();
            out = "{\"error:\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
