/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systemkeys.myspa.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.solsistemas.myspa.controller.ControllerSalaHorario;
import org.solsistemas.myspa.model.Horario;
import org.solsistemas.myspa.model.Sala;
import org.solsistemas.myspa.model.SalaHorario;

/**
 *
 * @author diegg
 */
@Path("/")
public class RESTSalaHorario extends Application{
    //nos ayudará a recuperar los horarios ocupados.
    @POST
    @Path("insertSalaHorario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("idSala")@DefaultValue("0") int idSala,
                           @FormParam("idHorario")@DefaultValue("0") int idHorario)
                           {
        
        ControllerSalaHorario csh = new ControllerSalaHorario();        
        String out = null;
        Horario h = new Horario();
        Sala s = new Sala();
        SalaHorario sh= new SalaHorario();
        sh.setSala(s);
        sh.setHorario(h);
     // precioUso = 0.5f;
        try {         
            s.setId(idSala);
            h.setId(idHorario);
            csh.insert(sh);
            if(sh.getHorario().getId() > 0 && sh.getSala().getId() > 0)
                out = "{\"result\":" + sh.getSala().getId() + "}";
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }
    
    //metodo que en caso de que se edite el horario de la reservacion se libere el horario que se desocupo 
    // y se agregue el horario que se ocupo
    @POST
    @Path("updateSalaHorario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("idSala")@DefaultValue("0") int idSala,
                           @FormParam("idHorario")@DefaultValue("0") int idHorario,
                           @FormParam("fecha")@DefaultValue("") String fecha)
                           {
        
        ControllerSalaHorario csh = new ControllerSalaHorario();        
        String out = null;
        Horario h = new Horario();
        Sala s = new Sala();
        SalaHorario sh= new SalaHorario();
        sh.setSala(s);
        sh.setHorario(h);
     // precioUso = 0.5f;
        try {         
            s.setId(idSala);
            h.setId(idHorario);
            csh.update(sh, fecha);
            if(sh.getHorario().getId() > 0 && sh.getSala().getId() > 0)
                out = "{\"result\":" + sh.getSala().getId() + "}";
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }        
}
