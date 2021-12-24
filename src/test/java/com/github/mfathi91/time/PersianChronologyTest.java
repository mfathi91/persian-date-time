package com.github.mfathi91.time;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.chrono.JapaneseEra;
import java.time.temporal.ValueRange;
import java.util.Arrays;

import static java.time.temporal.ChronoField.*;
import static org.junit.Assert.*;

public class PersianChronologyTest {

    @Test
    public void testOnCheckDayOfYear1() {
        PersianChronology.INSTANCE.checkDayOfYear(1387, 366);
        PersianChronology.INSTANCE.checkDayOfYear(1385, 365);
    }

    @Test(expected = DateTimeException.class)
    public void testOnCheckDayOfYear2() {
        PersianChronology.INSTANCE.checkDayOfYear(1385, 366);
    }
    //-----------------------------------------------------

    @Test
    public void testOnGetId() {
        assertEquals("Persian", PersianChronology.INSTANCE.getId());
    }
    //-----------------------------------------------------

    @Test
    public void testOnGetCalendarType() {
        assertEquals("persian", PersianChronology.INSTANCE.getCalendarType());
    }
    //-----------------------------------------------------

    @Test
    public void testOnDateYearMonthDay() {
        final PersianDate expected = PersianDate.of(1000, 4, 25);
        final PersianDate actual = PersianChronology.INSTANCE.date(1000, 4, 25);
        assertEquals(expected, actual);
    }
    //-----------------------------------------------------

    @Test
    public void testOnDateYearDay1() {
        final PersianDate expected = PersianDate.of(1387, 12, 30);
        final PersianDate actual = PersianChronology.INSTANCE.dateYearDay(1387, 366);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnDateYearDay2() {
        final PersianDate expected = PersianDate.of(1000, 1, 1);
        final PersianDate actual = PersianChronology.INSTANCE.dateYearDay(1000, 1);
        assertEquals(expected, actual);
    }

    @Test(expected = DateTimeException.class)
    public void testOnDateYearDay3() {
        PersianChronology.INSTANCE.dateYearDay(1388, 366);
    }
    //-----------------------------------------------------

    @Test
    public void testOnDateEpochDay1() {
        final PersianDate expected = PersianDate.of(1396, 8, 25);
        final PersianDate actual = PersianChronology.INSTANCE.dateEpochDay(17486);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnDateEpochDay2() {
        final PersianDate expected = PersianDate.of(378, 12, 8);
        final PersianDate actual = PersianChronology.INSTANCE.dateEpochDay(-354228);
        assertEquals(expected, actual);
    }
    //-----------------------------------------------------

    @Test
    public void testOnDateTemporalAccessor1() {
        final PersianDate expected = PersianDate.of(1396, 8, 25);
        final PersianDate actual = PersianChronology.INSTANCE.date(PersianDate.of(1396, 8, 25));
        assertEquals(expected, actual);
    }

    @Test
    public void testOnDateTemporalAccessor2() {
        final PersianDate expected = PersianDate.of(1396, 8, 25);
        final PersianDate actual = PersianChronology.INSTANCE.date(LocalDate.of(2017, 11, 16));
        assertEquals(expected, actual);
    }
    //-----------------------------------------------------

    @Test
    public void testOnIsLeapYear() {
        assertTrue(PersianChronology.INSTANCE.isLeapYear(999));
        assertTrue(PersianChronology.INSTANCE.isLeapYear(1003));
        assertTrue(PersianChronology.INSTANCE.isLeapYear(1387));
        assertFalse(PersianChronology.INSTANCE.isLeapYear(5));
        assertFalse(PersianChronology.INSTANCE.isLeapYear(1388));
        assertFalse(PersianChronology.INSTANCE.isLeapYear(671));
    }
    //-----------------------------------------------------

    @Test
    public void testOnProlepticYear1() {
        assertEquals(1000, PersianChronology.INSTANCE.prolepticYear(PersianEra.AHS, 1000));
    }

    @Test(expected = ClassCastException.class)
    public void testOnProlepticYear2() {
        PersianChronology.INSTANCE.prolepticYear(JapaneseEra.MEIJI, 500);
    }
    //-----------------------------------------------------

    @Test
    public void testOnEraOf1() {
        assertEquals(PersianEra.AHS, PersianChronology.INSTANCE.eraOf(1));
    }

    @Test(expected = DateTimeException.class)
    public void testOnEraOf2() {
        PersianChronology.INSTANCE.eraOf(2);
    }
    //-----------------------------------------------------

    @Test
    public void testOnEras() {
        assertEquals(Arrays.asList(PersianEra.AHS), PersianChronology.INSTANCE.eras());
    }
    //-----------------------------------------------------

    @Test
    public void testOnRangeDayOfMonth() {
        final ValueRange expected = ValueRange.of(1, 29, 31);
        final ValueRange actual = PersianChronology.INSTANCE.range(DAY_OF_MONTH);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnRangeDayOfYear() {
        final ValueRange expected = ValueRange.of(1, 365, 366);
        final ValueRange actual = PersianChronology.INSTANCE.range(DAY_OF_YEAR);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnRangeAlignedWeekOfMonth() {
        final ValueRange expected = ValueRange.of(1, 5);
        final ValueRange actual = PersianChronology.INSTANCE.range(ALIGNED_WEEK_OF_MONTH);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnRangeYear() {
        final ValueRange expected = ValueRange.of(1, 1999);
        final ValueRange actual = PersianChronology.INSTANCE.range(YEAR);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnRangeEra() {
        final ValueRange expected = ValueRange.of(1, 1);
        final ValueRange actual = PersianChronology.INSTANCE.range(ERA);
        assertEquals(expected, actual);
    }
}