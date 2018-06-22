package com.github.mfathi91.time;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;

import static java.time.temporal.ChronoField.ERA;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static org.junit.Assert.assertEquals;

public class PersianEraTest {

    @Test(expected = DateTimeException.class)
    public void of_persianEra_zero() {
        PersianEra.of(0);
    }

    @Test(expected = DateTimeException.class)
    public void of_persianEra_two() {
        PersianEra.of(2);
    }

    @Test
    public void of_persianEra_one() {
        assertEquals(PersianEra.AHS, PersianEra.of(1));
    }

    @Test
    public void getValue() {
        assertEquals(1, PersianEra.AHS.getValue());
    }

    @Test
    public void range_instanceOfEra() {
        assertEquals(ValueRange.of(1, 1), PersianEra.AHS.range(ERA));
    }

    @Test(expected = UnsupportedTemporalTypeException.class)
    public void range_notInstanceOfEra() {
        PersianEra.AHS.range(MINUTE_OF_HOUR);
    }

}