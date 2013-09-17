package com.pduda.jerseyjetty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("gmt")
public class GmtTimeResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        return "It's currently 20:15 GMT";
    }
}
