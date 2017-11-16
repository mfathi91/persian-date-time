package com.github.mfathi91.time;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.chrono.JapaneseEra;
import java.time.temporal.ValueRange;
import java.util.Arrays;

import static java.time.temporal.ChronoField.*;
import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

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
        PersianDate expected = PersianDate.of(1000, 4, 25);
        PersianDate actual = PersianChronology.INSTANCE.date(1000, 4, 25);
        assertReflectionEquals(expected, actual);
    }
    //-----------------------------------------------------

    @Test
    public void testOnDateYearDay1() {
        PersianDate expected = PersianDate.of(1387, 12, 30);
        PersianDate actual = PersianChronology.INSTANCE.dateYearDay(1387, 366);
        assertReflectionEquals(expected, actual);
    }

    @Test
    public void testOnDateYearDay2() {
        PersianDate expected = PersianDate.of(1000, 1, 1);
        PersianDate actual = PersianChronology.INSTANCE.dateYearDay(1000, 1);
        assertReflectionEquals(expected, actual);
    }

    @Test(expected = DateTimeException.class)
    public void testOnDateYearDay3() {
        PersianChronology.INSTANCE.dateYearDay(1388, 366);
    }
    //-----------------------------------------------------

    @Test
    public void testOnDateEpochDay1() {
        PersianDate expected = PersianDate.of(1396, 8, 25);
        PersianDate actual = PersianChronology.INSTANCE.dateEpochDay(17486);
        assertReflectionEquals(expected, actual);
    }

    @Test
    public void testOnDateEpochDay2() {
        PersianDate expected = PersianDate.of(378, 12, 8);
        PersianDate actual = PersianChronology.INSTANCE.dateEpochDay(-354228);
        assertReflectionEquals(expected, actual);
    }
    //-----------------------------------------------------

    @Test
    public void testOnDateTemporalAccessor1() {
        PersianDate expected = PersianDate.of(1396, 8, 25);
        PersianDate actual = PersianChronology.INSTANCE.date(PersianDate.of(1396, 8, 25));
        assertReflectionEquals(expected, actual);
    }

    @Test
    public void testOnDateTemporalAccessor2() {
        PersianDate expected = PersianDate.of(1396, 8, 25);
        PersianDate actual = PersianChronology.INSTANCE.date(LocalDate.of(2017, 11, 16));
        assertReflectionEquals(expected, actual);
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
        assertReflectionEquals(Arrays.asList(PersianEra.AHS), PersianChronology.INSTANCE.eras());
    }
    //-----------------------------------------------------

    @Test
    public void testOnRangeDayOfMonth() {
        ValueRange expected = ValueRange.of(1, 29, 31);
        ValueRange actual = PersianChronology.INSTANCE.range(DAY_OF_MONTH);
        assertReflectionEquals(expected, actual);
    }

    @Test
    public void testOnRangeDayOfYear() {
        ValueRange expected = ValueRange.of(1, 365, 366);
        ValueRange actual = PersianChronology.INSTANCE.range(DAY_OF_YEAR);
        assertReflectionEquals(expected, actual);
    }

    @Test
    public void testOnRangeAlignedWeekOfMonth() {
        ValueRange expected = ValueRange.of(1, 5);
        ValueRange actual = PersianChronology.INSTANCE.range(ALIGNED_WEEK_OF_MONTH);
        assertReflectionEquals(expected, actual);
    }

    @Test
    public void testOnRangeYear() {
        ValueRange expected = ValueRange.of(1, 1999);
        ValueRange actual = PersianChronology.INSTANCE.range(YEAR);
        assertReflectionEquals(expected, actual);
    }

    @Test
    public void testOnRangeEra(){
        ValueRange expected = ValueRange.of(1, 1);
        ValueRange actual = PersianChronology.INSTANCE.range(ERA);
        assertReflectionEquals(expected, actual);
    }
}