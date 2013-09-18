package com.pduda.timeexpert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javax.ws.rs.client.ClientBuilder.newClient;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TimeAcceptanceTest {

    private String actualApplicationTime;
    private TimeExpertServer timeExpert;
    private FixedClock clock;
    private TimeExpertUser timeExpertUser;

    @Before
    public void startApplication() {
        clock = new FixedClock();
        timeExpert = new TimeExpertServer(clock);
        timeExpert.start();

        timeExpertUser = new TimeExpertUser();
    }

    @After
    public void stopApplication() {
        timeExpert.stop();
    }

    @Test
    public void saysTheCurrentTime() throws Exception {
        givenTheCurrentTimeIs("20:15");
        whenAUserChecksTheTime();
        thenTheUserSees("It's currently 20:15!");
    }

    private void givenTheCurrentTimeIs(String currentTime) throws ParseException {
        Date currentTimeAsDate = new SimpleDateFormat("HH:mm").parse(currentTime);
        clock.setNow(currentTimeAsDate);
    }

    private void whenAUserChecksTheTime() {
        actualApplicationTime = timeExpertUser.checkCurrentTime();
    }

    private void thenTheUserSees(String expectedApplicationTime) {
        assertEquals(expectedApplicationTime, actualApplicationTime);
    }

    private static class TimeExpertUser {

        public String checkCurrentTime() {
            WebTarget target = newClient().target("http://localhost:6666").path("time");
            Response response = target.request(MediaType.TEXT_PLAIN).get();
            return response.readEntity(String.class);
        }
    }
}
