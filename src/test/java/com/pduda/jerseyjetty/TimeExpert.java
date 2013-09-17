package com.pduda.jerseyjetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class TimeExpert {
    private Server server;
    private final FixedClock clock;

    public TimeExpert(FixedClock clock) {
        this.clock = clock;
    }

    void start() {
        server = new Server(6666);
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");
        handler.addServlet(new ServletHolder(new ServletContainer(resourceConfig())), "/*");
        server.setHandler(handler);
        try {
            server.start();
            //                server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ResourceConfig resourceConfig() {
        return new ResourceConfig().register(new GmtTimeResource(clock));
    }
    
}
