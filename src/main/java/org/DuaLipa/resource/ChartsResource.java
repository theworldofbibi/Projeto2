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
    /*ChartsDAO dao;

    public ChartsResource(ChartsDAO dao){
        this.dao=dao;
    }
     List<Results> results;

    @GET
    public Charts getAllCharts(){
        return dao.getAllCharts();
    }*/
    List<Results> results;
    public ChartsResource (){
        this.results=new ArrayList<>();

        for(int i=1;i<31;i++){
            Results r = new Results();
            r.setValue(Math.random());
            r.setDate("2020-06-"+i);
            this.results.add(r);
        }
    }

    @GET
    public List<Results>getResults(){
        return this.results;
    }
    @POST
    public Results createResults(Results r){
        this.results.add(r);
        return r;
    }
    @PUT
    public Results updateResults(Results r){
        for (int i=0;i<this.results.size();i++){
            if(this.results.get(i).getDate().equals(r.getDate())){
                this.results.get(i).setValue(r.getValue());
                return r;
            }
        }
        throw new NotFoundException();
    }
    @DELETE
    public Results deleteResults(String date){
        for (int i=0;i<this.results.size();i++){
            if(this.results.get(i).getDate().equals(date)){
                Results aux = this.results.get(i);
                this.results.remove(i);
                return aux;
            }
        }
        throw new NotFoundException();
    }
}