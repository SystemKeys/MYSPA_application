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
import org.solsistemas.myspa.controller.ControllerReservacion;
import org.solsistemas.myspa.controller.ControllerSalaHorario;
import org.solsistemas.myspa.model.Cliente;
import org.solsistemas.myspa.model.Horario;
import org.solsistemas.myspa.model.Reservacion;
import org.solsistemas.myspa.model.Sala;
import org.solsistemas.myspa.model.SalaHorario;


/**
 *
 * @author diegg
 */
@Path("/")
public class RESTReservacion extends Application{
    @GET
    @Path("getAllReservacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        ControllerReservacion cr = new ControllerReservacion();
        
        List<Reservacion> reservaciones = null;
        JSONSerializer jss = new JSONSerializer();
        String out = null;
        
        try {
            reservaciones = cr.getAll("",1);
            out = jss.serialize(reservaciones);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }     
    @POST
    @Path("getAllReservacionCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCliente(@FormParam("idCliente")@DefaultValue("0")int idCliente){
        ControllerReservacion cr = new ControllerReservacion();        
        List<Reservacion> reservacionesCliente = null;
        JSONSerializer jss = new JSONSerializer();
        String out = null;
        
        try {
            reservacionesCliente = cr.getAllCliente("", idCliente);
            out = jss.serialize(reservacionesCliente);
        } catch (Exception e) {
            e.printStackTrace();
            out="{\"error:\":\""+e.toString()+"\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    } 
    @POST
    @Path("insertReservacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(@FormParam("fechaHoraInicio")@DefaultValue("") String fechaHoraInicio,
                           @FormParam("fechaHoraFin")@DefaultValue("") String fechaHoraFin,                           
                           @FormParam("idCliente")@DefaultValue("0") int idCliente,
                           @FormParam("idSala")@DefaultValue("0")int idSala,
                           @FormParam("idHorario")@DefaultValue("0") int idHorario)
                           {
        
        ControllerReservacion cr = new ControllerReservacion();        
        ControllerSalaHorario csh = new ControllerSalaHorario();
        SalaHorario sh = new SalaHorario();
        String out = null;
        Reservacion r = new Reservacion();
        Sala s = new Sala();
        Cliente c = new Cliente();
        Horario h = new Horario();
     // precioUso = 0.5f;
        try {            
            r.setEstatus(1);
            r.setFechaHoraFin(fechaHoraFin);
            r.setFechaHoraInicio(fechaHoraInicio);
            s.setId(idSala);
            c.setId(idCliente);
            h.setId(idHorario); 
            sh.setHorario(h);
            sh.setSala(s);
            r.setSala(s);
            r.setCliente(c);
            cr.insert(r);
            csh.insert(sh);
            if(r.getId() > 0)
                out = "{\"result\":" + r.getId() + "}";
            else
                out = "{\"error\":\"Movimiento no realizado.\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
         return Response.status(Response.Status.OK).entity(out).build();
    }    
        
    @POST
    @Path("deleteReservacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("idReservacion")@DefaultValue("0") int idReservacion){
        ControllerReservacion cr = new ControllerReservacion();        
        String out = null;

        try{
          cr.delete(idReservacion);
          out = "{\"response:\":\"Eliminado Correctamente.\"}";
        }catch(Exception e){
            e.printStackTrace();
             out = "{\"exception:\":\"" + e.toString() + "\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }                          
}