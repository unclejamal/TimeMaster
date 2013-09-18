package com.pduda.timeexpert;

import java.util.Date;

public class RealClock implements Clock {

    @Override
    public Date now() {
        return new Date();
    }
}
