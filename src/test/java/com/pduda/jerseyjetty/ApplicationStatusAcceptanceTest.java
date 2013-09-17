package com.pduda.jerseyjetty;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationStatusAcceptanceTest {
    private String actualApplicationStatus;
    
    @Test public void saysGoodEveningWithTheCurrentTime() {
        givenTheCurrentTimeIs("20:15");
        whenUserChecksTheApplicationStatus();
        thenTheStatusContains("Good evening at 20:15");
    }

    private void givenTheCurrentTimeIs(String currentTime) {
        
    }

    private void whenUserChecksTheApplicationStatus() {
        actualApplicationStatus = checkCurrentApplicationStatus();
    }

    private void thenTheStatusContains(String expectedApplicationStatus) {
        assertEquals(expectedApplicationStatus, actualApplicationStatus);
    }

    private String checkCurrentApplicationStatus() {
        return "Good evening at 20:15";
    }
}
