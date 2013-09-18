package com.pduda.timeexpert;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class TimeExpertServer {

    private Server server;
    private final Clock clock;

    public TimeExpertServer(Clock clock) {
        this.clock = clock;
    }

    public void start() {
        server = new Server(6666);
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("");
        // adds Jersey Servlet with a customized ResourceConfig
        handler.addServlet(new ServletHolder(new ServletContainer(resourceConfig())), "/*");
        server.setHandler(handler);
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException("Could not start the server", e);
        }
    }

    private ResourceConfig resourceConfig() {
        // manually injecting dependencies (clock) to Jersey resource classes
        return new ResourceConfig().register(new GmtTimeResource(clock));
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException("Could not stop the server", e);
        }
    }

    public void join() {
        try {
            server.join();
        } catch (InterruptedException e) {
            throw new RuntimeException("Could not join the thread", e);
        }
    }
}
