package com.pduda.timeexpert;

import java.text.SimpleDateFormat;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("gmt")
public class GmtTimeResource {

    private final Clock clock;

    public GmtTimeResource(Clock clock) {
        this.clock = clock;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        return String.format("It's currently %s GMT", now());
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getStatusWildcard() {
        return String.format("It'z currently %s GMT", now());
    }

    private String now() {
        return new SimpleDateFormat("HH:mm").format(clock.now());
    }
}
