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
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.solsistemas.myspa.controller.ControllerHorario;
import org.solsistemas.myspa.controller.ControllerReservacion;
import org.solsistemas.myspa.model.Horario;
import org.solsistemas.myspa.model.Reservacion;

/**
 *
 * @author diegg
 */
@Path("/")
public class RESTHorario extends Application{
    @POST
    @Path("getAllHorarioWithoutUsed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllWithoutUsed(@FormParam("idSala")@DefaultValue("0") int idSala,
                                      @FormParam("fecha")@DefaultValue("") String fecha){
        ControllerHorario ch = new ControllerHorario();
        
        List<Horario> horarios = null;
        JSONSerializer jss = new JSONSerializer();
        String out = null;
        
        try {
            fecha = fecha + "%";
            horarios = ch.getAllWithoutUsed(idSala,fecha);
            out = jss.serialize(horarios);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
}
