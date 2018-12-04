/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systemkeys.myspa.rest;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.solsistemas.myspa.controller.ControllerServicio;
import org.solsistemas.myspa.model.Servicio;

/**
 *
 * @author diegg
 */
@Path("/")
public class RESTServicio extends Application{
    @POST
    @Path("insertServicio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("JSONServicio")@DefaultValue("") String JSONServicio){
        Servicio s;
        JSONDeserializer<Servicio> jss = new JSONDeserializer();
      
        JSONSerializer jssSer = new JSONSerializer();
        String out = null;
        ControllerServicio cs = new ControllerServicio();
        try{
            s = jss.deserialize(JSONServicio);
            cs.insert(s);
        if(s.getId() > 0)
                out = jssSer.serialize(JSONServicio);
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
}
