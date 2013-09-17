package com.pduda.jerseyjetty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.client.Client;
import static javax.ws.rs.client.ClientBuilder.newClient;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class GmtTimeAcceptanceTest {

    private String actualApplicationStatus;
    private TimeExpert timeExpert;
    private FixedClock clock;

    @Before
    public void startApplication() {
        clock = new FixedClock();
        timeExpert = new TimeExpert(clock);
        timeExpert.start();
    }

    @After
    public void stopApplication() {
        timeExpert.stop();
    }

    @Test
    public void saysGoodEveningWithTheCurrentTime() throws Exception {
        givenTheCurrentGmtTimeIs("20:15");
        whenUserChecksTheGmtTime();
        thenTheUserSees("It's currently 20:15 GMT");
    }

    private void givenTheCurrentGmtTimeIs(String currentGmtTime) throws ParseException {
        Date currentTimeAsDate = new SimpleDateFormat("HH:mm").parse(currentGmtTime);
        clock.setNow(currentTimeAsDate);
    }

    private void whenUserChecksTheGmtTime() {
        actualApplicationStatus = checkCurrentApplicationStatus();
    }

    private void thenTheUserSees(String expectedApplicationStatus) {
        assertEquals(expectedApplicationStatus, actualApplicationStatus);
    }

    private String checkCurrentApplicationStatus() {
        Client client = newClient();
        WebTarget target = client.target("http://localhost:6666").path("gmt");
        Response response = target.request(MediaType.TEXT_PLAIN).get();
        return response.readEntity(String.class);
    }
}
