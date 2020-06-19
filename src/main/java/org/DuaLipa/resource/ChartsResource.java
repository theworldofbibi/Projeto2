package org.DuaLipa.resource;
import org.DuaLipa.api.*;
import org.DuaLipa.db.*;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

   /* @POST
    public Results createResults(Results results){
        this.results.add(r);
        return r;
    }

    @PUT
    public Results updateResults(Results r){
        for (int i=0;i<this.results.size();i++){
            if(this.results.get(i).getWeek().equals(r.getWeek())){
                this.results.get(i).setPosition(r.getPosition());
                return r;
            }
        }
        throw new NotFoundException();
    }

    @DELETE
    public Results deleteResults(String date){
        for (int i=0;i<this.results.size();i++){
            if(this.results.get(i).getWeek().equals(date)){
                Results aux = this.results.get(i);
                this.results.remove(i);
                return aux;
            }
        }
        throw new NotFoundException();
    } */
}