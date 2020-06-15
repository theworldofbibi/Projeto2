package org.DuaLipa.resource;
import org.DuaLipa.api.*;
import org.DuaLipa.db.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("charts")
@Produces(MediaType.APPLICATION_JSON)
public class ChartsResource {
    ChartsDAO dao;

    public ChartsResource(ChartsDAO dao){
        this.dao=dao;
    }
    @GET
    public Charts getAllCharts(){
        return dao.getAllCharts();
    }
}