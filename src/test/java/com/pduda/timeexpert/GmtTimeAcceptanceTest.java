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

public class GmtTimeAcceptanceTest {

    private String actualApplicationGmtTime;
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
    public void saysGoodEveningWithTheCurrentTime() throws Exception {
        givenTheCurrentGmtTimeIs("20:15");
        whenAUserChecksTheGmtTime();
        thenTheUserSees("It's currently 20:15 GMT");
    }

    private void givenTheCurrentGmtTimeIs(String currentGmtTime) throws ParseException {
        Date currentTimeAsDate = new SimpleDateFormat("HH:mm").parse(currentGmtTime);
        clock.setNow(currentTimeAsDate);
    }

    private void whenAUserChecksTheGmtTime() {
        actualApplicationGmtTime = timeExpertUser.checkCurrentGmtTime();
    }

    private void thenTheUserSees(String expectedApplicationGmtTime) {
        assertEquals(expectedApplicationGmtTime, actualApplicationGmtTime);
    }
    
    private static class TimeExpertUser {

        public String checkCurrentGmtTime() {
            WebTarget target = newClient().target("http://localhost:6666").path("gmt");
            Response response = target.request(MediaType.TEXT_PLAIN).get();
            return response.readEntity(String.class);
        }
        
    }
}
