package com.pduda.timeexpert;

import java.util.Date;

public class FixedClock implements Clock {
    private Date fixedNow;

    @Override
    public Date now() {
        return fixedNow;
    }

    public void setNow(Date fixedNow) {
        this.fixedNow = fixedNow;
    }
}
