package com.mahmoud;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class PersianDateTest {

    @Test
    public void testOnStaticFactoryMethod1() {
        PersianDate pdt = PersianDate.of(1400, 2, 17);
        assertEquals(1400, pdt.getYear());
        assertEquals(2, pdt.getMonthValue());
        assertEquals(PersianMonth.ORDIBEHESHT, pdt.getMonth());
        assertEquals(17, pdt.getDayOfMonth());
    }

    @Test
    public void testOnStaticFactoryMethod2() {
        PersianDate pdt = PersianDate.of(1400, PersianMonth.ORDIBEHESHT, 17);
        assertEquals(1400, pdt.getYear());
        assertEquals(2, pdt.getMonthValue());
        assertEquals(PersianMonth.ORDIBEHESHT, pdt.getMonth());
        assertEquals(17, pdt.getDayOfMonth());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnGetDayOfYear(){
        PersianDate pd = PersianDate.of(1400, 8, 15);
        assertEquals(231, pd.getDayOfYear());
    }

    @Test
    public void testOnGetDayOfYearInLeapYear(){
        PersianDate pd = PersianDate.of(1387, 12, 30);
        assertEquals(366, pd.getDayOfYear());
    }

    @Test
    public void testOnGetDayOfWeek(){
        PersianDate saturday = PersianDate.of(1395, 11, 23);
        assertEquals(DayOfWeek.SATURDAY, saturday.getDayOfWeek());
        PersianDate sunday = PersianDate.of(1395, 11, 24);
        assertEquals(DayOfWeek.SUNDAY, sunday.getDayOfWeek());
        PersianDate monday = PersianDate.of(1395, 11, 25);
        assertEquals(DayOfWeek.MONDAY, monday.getDayOfWeek());
        PersianDate friday = PersianDate.of(1395, 11, 29);
        assertEquals(DayOfWeek.FRIDAY, friday.getDayOfWeek());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnToGregorian() {
        PersianDate pdt = PersianDate.of(1396, 6, 20);
        LocalDate expected = LocalDate.of(2017, 9, 11);
        assertReflectionEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianInPersianLeapYear() {
        PersianDate pdt = PersianDate.of(1399, 12, 30);
        LocalDate expected = LocalDate.of(2021, 3, 20);
        assertReflectionEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianInGregorianLeapYear() {
        PersianDate pdt = PersianDate.of(1394, 12, 10);
        LocalDate expected = LocalDate.of(2016, 2, 29);
        assertReflectionEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianOnFirstOfPersianYear() {
        PersianDate pdt = PersianDate.of(1407, 1, 1);
        LocalDate expected = LocalDate.of(2028, 3, 20);
        assertReflectionEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianOnEndOfPersianYear() {
        PersianDate pdt = PersianDate.of(1376, 12, 29);
        LocalDate expected = LocalDate.of(1998, 3, 20);
        assertReflectionEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianOnFirstOfGregorianYear() {
        PersianDate pdt = PersianDate.of(1385, 10, 11);
        LocalDate expected = LocalDate.of(2007, 1, 1);
        assertReflectionEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianOnEndOfGregorianYear() {
        PersianDate pdt = PersianDate.of(1429, 10, 10);
        LocalDate expected = LocalDate.of(2050, 12, 31);
        assertReflectionEquals(expected, pdt.toGregorian());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnToPersian() {
        LocalDate ld = LocalDate.of(2046, 5, 10);
        PersianDate expected = PersianDate.of(1425, 2, 20);
        assertReflectionEquals(expected, PersianDate.gregorianToPersian(ld));
    }

    @Test
    public void testOnToPersianInGregorianLeapYear() {
        LocalDate ldt = LocalDate.of(2012, 2, 29);
        PersianDate expected = PersianDate.of(1390, 12, 10);
        assertReflectionEquals(expected, PersianDate.gregorianToPersian(ldt));
    }

    @Test
    public void testOnToPersianInPersianLeapYear() {
        LocalDate ldt = LocalDate.of(2034, 3, 20);
        PersianDate expected = PersianDate.of(1412, 12, 30);
        assertReflectionEquals(expected, PersianDate.gregorianToPersian(ldt));
    }

    @Test
    public void testOnToPersianOnFirstOfGregorianYear() {
        LocalDate ldt = LocalDate.of(2008, 1, 1);
        PersianDate expected = PersianDate.of(1386, 10, 11);
        assertReflectionEquals(expected, PersianDate.gregorianToPersian(ldt));
    }

    @Test
    public void testOnToPersianOnEndOfGregorianYear() {
        LocalDate ldt = LocalDate.of(2003, 3, 1);
        PersianDate expected = PersianDate.of(1381, 12, 10);
        assertReflectionEquals(expected, PersianDate.gregorianToPersian(ldt));
    }

    @Test
    public void testOnToPersianOnFirstOfPersianYear() {
        LocalDate ldt = LocalDate.of(1986, 3, 21);
        PersianDate expected = PersianDate.of(1365, 1, 1);
        assertReflectionEquals(expected, PersianDate.gregorianToPersian(ldt));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnIsEqual() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        PersianDate b = PersianDate.of(1396, 6, 10);
        PersianDate c = PersianDate.of(1396, 3, 15);
        assertFalse(a.isEqual(b));
        assertTrue(a.isEqual(c));
        assertFalse(b.isEqual(c));
        assertTrue(b.isEqual(b));
    }

    @Test
    public void testOnBefore() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        PersianDate b = PersianDate.of(1396, 6, 10);
        assertTrue(a.isBefore(b));
        assertFalse(a.isBefore(a));
        assertFalse(b.isBefore(a));
    }

    @Test
    public void testOnAfter() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        PersianDate b = PersianDate.of(1396, 6, 10);
        assertFalse(a.isAfter(b));
        assertFalse(a.isAfter(a));
        assertTrue(b.isAfter(a));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnEqualsNull(){
        PersianDate a = PersianDate.of(1396, 3, 15);
        assertFalse(a.equals(null));
    }

    @Test
    public void testOnEqualsReflexivity() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        assertTrue(a.equals(a));
    }

    @Test
    public void testOnEqualsSymmetricity(){
        PersianDate a = PersianDate.of(1396, 3, 15);
        PersianDate b = PersianDate.of(1396, 3, 15);
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    public void testOnEqualsTransitivity(){
        PersianDate a = PersianDate.of(1396, 3, 15);
        PersianDate b = PersianDate.of(1396, 3, 15);
        PersianDate c = PersianDate.of(1396, 3, 15);
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void testOnHashCodeForEqualObjects(){
        PersianDate a = PersianDate.of(1500, 12, 15);
        PersianDate b = PersianDate.of(1500, 12, 15);
        assertTrue(a.equals(b));
        assertEquals(a.hashCode(), b.hashCode());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnToString(){
        PersianDate persianDate1 = PersianDate.of(1391, 11, 6);
        assertEquals("1391-11-06", persianDate1.toString());
        PersianDate persianDate2 = PersianDate.of(31, 1, 12);
        assertEquals("0031-01-12", persianDate2.toString());
    }
}