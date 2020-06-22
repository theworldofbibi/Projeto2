package org.DuaLipa.resource;
import org.DuaLipa.api.*;
import org.DuaLipa.db.*;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("charts")
@Produces(MediaType.APPLICATION_JSON)
public class ChartsResource {
    ChartsDAO dao;

    public ChartsResource(ChartsDAO dao){
        this.dao=dao;
    }

    @GET
    public List<Results>getResults(){
        return this.dao.read();
    }

   @POST
    public Results createResults(Results r){
        this.dao.create(r);
        return r;
    } //TRATAMENTO DO ERRO

    @PUT
    public Results updateResults(Results r){
        if(dao.update(r)){
            return r;
        }
        throw new NotFoundException();
    }
    @DELETE
    @Path("/{date}")
    public Response deleteResults(@PathParam("date") String date) {
        if (dao.delete(date)) {
            return Response.ok().build();
        }
        throw new NotFoundException();
    }
}