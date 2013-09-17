package com.pduda.jerseyjetty;

import java.util.Date;

public class FixedClock implements Clock {
    private Date fixedNow;

    @Override
    public Date now() {
        return fixedNow;
    }

    public void setNow(Date now) {
        fixedNow = now;
    }
    
}
