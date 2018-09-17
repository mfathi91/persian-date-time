package com.github.mfathi91.time;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.UnsupportedTemporalTypeException;

import static java.time.temporal.ChronoField.*;
import static java.time.temporal.ChronoUnit.*;
import static org.junit.Assert.*;

public class PersianDateTest {

    @Test
    public void testOnStaticFactoryMethodNow() {
        assertEquals(PersianDate.fromGregorian(LocalDate.now()), PersianDate.now());
    }

    @Test
    public void testOnStaticFactoryMethod1() {
        PersianDate pd = PersianDate.of(1400, 2, 17);
        assertEquals(1400, pd.getYear());
        assertEquals(2, pd.getMonthValue());
        assertEquals(PersianMonth.ORDIBEHESHT, pd.getMonth());
        assertEquals(17, pd.getDayOfMonth());
    }

    @Test
    public void testOnStaticFactoryMethod2() {
        PersianDate pd = PersianDate.of(1623, PersianMonth.AZAR, 10);
        assertEquals(1623, pd.getYear());
        assertEquals(9, pd.getMonthValue());
        assertEquals(PersianMonth.AZAR, pd.getMonth());
        assertEquals(10, pd.getDayOfMonth());
    }

    @Test(expected = DateTimeException.class)
    public void testOnPersianDateOfInvalidDateNotLeapYear() {
        PersianDate.of(1388, 12, 30);
    }

    @Test(expected = DateTimeException.class)
    public void testOnPersianDateOfInvalidDate1() {
        PersianDate.of(2000, 12, 2);
    }

    @Test(expected = DateTimeException.class)
    public void testOnPersianDatePlus() {
        PersianDate.of(1890, 6, 31).plusYears(120);
    }

    @Test(expected = DateTimeException.class)
    public void testOnPersianDateInvalidDate2() {
        PersianDate.of(1000, 4, 32);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnGetDayOfYear() {
        PersianDate pd = PersianDate.of(1400, 8, 15);
        assertEquals(231, pd.getDayOfYear());
    }

    @Test
    public void testOnGetDayOfYearInLeapYear() {
        PersianDate pd = PersianDate.of(1387, 12, 30);
        assertEquals(366, pd.getDayOfYear());
    }

    @Test
    public void testOnGetDayOfWeek() {
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
    public void testOnToJulianDay() {
        assertEquals(2458054, PersianDate.of(1396, 8, 6).toJulianDay());
        assertEquals(2462580, PersianDate.of(1408, 12, 30).toJulianDay());
        assertEquals(1984844, PersianDate.of(101, 1, 1).toJulianDay());
    }

    @Test
    public void testOnOfJulianDay() {
        assertEquals(PersianDate.of(1393, 11, 13), PersianDate.ofJulianDays(2457055));
        assertEquals(PersianDate.of(1791, 6, 19), PersianDate.ofJulianDays(2602276));
        assertEquals(PersianDate.of(320, 5, 5), PersianDate.ofJulianDays(2064960));
        assertEquals(PersianDate.of(321, 12, 29), PersianDate.ofJulianDays(2065561));
        assertEquals(PersianDate.of(473, 1, 1), PersianDate.ofJulianDays(2120714));
        assertEquals(PersianDate.of(474, 1, 1), PersianDate.ofJulianDays(2121079));
        assertEquals(PersianDate.of(474, 12, 30), PersianDate.ofJulianDays(2121444));
        assertEquals(PersianDate.of(475, 1, 1), PersianDate.ofJulianDays(2121445));
        assertEquals(PersianDate.MIN, PersianDate.ofJulianDays(1948320));
        assertEquals(PersianDate.MAX, PersianDate.ofJulianDays(2678438));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnPlusYears() {
        PersianDate actual = PersianDate.of(1400, 1, 1).plusYears(1);
        PersianDate expected = PersianDate.of(1401, 1, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusYearsLeapYear() {
        PersianDate actual = PersianDate.of(1387, 12, 30).plusYears(1);
        PersianDate expected = PersianDate.of(1388, 12, 29);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusYearsLeapYear100Years() {
        PersianDate actual = PersianDate.of(1503, 12, 30).plusYears(100);
        PersianDate expected = PersianDate.of(1603, 12, 29);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusYearsNegativeLeapYear104Years() {
        PersianDate actual = PersianDate.of(1503, 12, 30).plusYears(-104);
        PersianDate expected = PersianDate.of(1399, 12, 30);
        assertEquals(expected, actual);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnPlusMonthsZero() {
        assertEquals(PersianDate.MIN, PersianDate.MIN.plusMonths(0));
    }

    @Test
    public void testOnPlusMonths() {
        PersianDate actual = PersianDate.of(1388, 1, 1).plusMonths(1);
        PersianDate expected = PersianDate.of(1388, 2, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthsMoreThan12Months() {
        PersianDate actual = PersianDate.of(1353, 10, 25).plusMonths(16);
        PersianDate expected = PersianDate.of(1355, 2, 25);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthShahrivarToMehr() {
        PersianDate actual = PersianDate.of(1400, 6, 31).plusMonths(1);
        PersianDate expected = PersianDate.of(1400, 7, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthEndOfMonthLeapYear() {
        PersianDate actual = PersianDate.of(1387, 3, 30).plusMonths(9);
        PersianDate expected = PersianDate.of(1387, 12, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthEndOfMonthNotLeapYear() {
        PersianDate actual = PersianDate.of(1396, 4, 30).plusMonths(8);
        PersianDate expected = PersianDate.of(1396, 12, 29);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthNegative() {
        PersianDate actual = PersianDate.of(1396, 6, 31).plusMonths(-8);
        PersianDate expected = PersianDate.of(1395, 10, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthNegativeMoreThan12Months() {
        PersianDate actual = PersianDate.of(1396, 7, 30).plusMonths(-30);
        PersianDate expected = PersianDate.of(1394, 1, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthNegativeEndOfYearLeapYear() {
        PersianDate actual = PersianDate.of(1388, 1, 31).plusMonths(-1);
        PersianDate expected = PersianDate.of(1387, 12, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusMonthNegativeEndOfYearNotLeapYear() {
        PersianDate actual = PersianDate.of(1389, 1, 31).plusMonths(-1);
        PersianDate expected = PersianDate.of(1388, 12, 29);
        assertEquals(expected, actual);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnPlusDaysZero() {
        assertEquals(PersianDate.MIN, PersianDate.MIN.plusDays(0));
    }

    @Test
    public void testOnPlusDays() {
        PersianDate actual = PersianDate.of(1450, 6, 31).plusDays(1);
        PersianDate expected = PersianDate.of(1450, 7, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysMoreThan365Days() {
        PersianDate actual = PersianDate.of(1396, 8, 6).plusDays(24435);
        PersianDate expected = PersianDate.of(1463, 6, 31);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysEndOfYearLeapYear() {
        PersianDate actual = PersianDate.of(1387, 12, 29).plusDays(1);
        PersianDate expected = PersianDate.of(1387, 12, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysEndOfNotYearLeapYear() {
        PersianDate actual = PersianDate.of(1388, 12, 29).plusDays(1);
        PersianDate expected = PersianDate.of(1389, 1, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysNegativeFirstOfYearLeapYear() {
        PersianDate actual = PersianDate.of(1392, 1, 1).plusDays(-1);
        PersianDate expected = PersianDate.of(1391, 12, 30);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysNegativeFirstOfYearNotLeapYear() {
        PersianDate actual = PersianDate.of(1393, 1, 1).plusDays(-1);
        PersianDate expected = PersianDate.of(1392, 12, 29);
        assertEquals(expected, actual);
    }

    @Test
    public void testOnPlusDaysNegativeMoreThan365Days() {
        PersianDate actual = PersianDate.of(1396, 8, 6).plusDays(-35688);
        PersianDate expected = PersianDate.of(1298, 11, 22);
        assertEquals(expected, actual);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnIsLeapYear() {
        PersianDate pd = PersianDate.of(1375, 6, 15);
        // 7 successive leap years
        for (int i = 0; i < 7; i++) {
            assertTrue(pd.isLeapYear());
            pd = pd.plusYears(4);
        }
        // each 33 years, leap year happens after 5 years
        assertTrue(pd.plusYears(1).isLeapYear());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnToEpochDay() {
        assertEquals(17468, PersianDate.of(1396, 8, 7).toEpochDay());
        assertEquals(-66869, PersianDate.of(1165, 9, 11).toEpochDay());
        assertEquals(-492267, PersianDate.of(1, 1, 1).toEpochDay());
        assertEquals(237851, PersianDate.of(1999, 12, 29).toEpochDay());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnGetLengthOfMonth() {
        // 1387 is a leap year
        assertEquals(31, PersianDate.of(1387, 1, 1).lengthOfMonth());
        assertEquals(30, PersianDate.of(1387, 12, 1).lengthOfMonth());

        // 1388 is a normal year
        assertEquals(31, PersianDate.of(1388, 1, 1).lengthOfMonth());
        assertEquals(29, PersianDate.of(1388, 12, 1).lengthOfMonth());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnGetLongDayOfWeek() {
        PersianDate pd = PersianDate.of(1387, 12, 30);
        DayOfWeek dow = DayOfWeek.FRIDAY;
        // Check about three years
        for (int i = 0; i < 1000; i++) {
            assertEquals(dow.getValue(), pd.getLong(DAY_OF_WEEK));
            pd = pd.plusDays(1);
            dow = dow.plus(1);
        }
    }

    @Test
    public void testOnGetLongAlignedDayOfWeekInMonth() {
        PersianDate pd = PersianDate.of(1396, 8, 8);
        assertEquals(1, pd.getLong(ALIGNED_DAY_OF_WEEK_IN_MONTH));
        pd = PersianDate.of(1292, 12, 30);
        assertEquals(2, pd.getLong(ALIGNED_DAY_OF_WEEK_IN_MONTH));
    }

    @Test
    public void testOnGetAlignedDayOfWeekInYear() {
        assertEquals(1, PersianDate.MIN.getLong(ALIGNED_DAY_OF_WEEK_IN_YEAR));
        assertEquals(3, PersianDate.of(1000, 12, 24).getLong(ALIGNED_DAY_OF_WEEK_IN_YEAR));
        assertEquals(1, PersianDate.MAX.getLong(ALIGNED_DAY_OF_WEEK_IN_YEAR));
    }

    @Test
    public void testOnGetLongAlignedWeekOfMonth() {
        PersianDate pd = PersianDate.of(1345, 7, 16);
        assertEquals(3, pd.getLong(ALIGNED_WEEK_OF_MONTH));
        pd = PersianDate.of(1500, 11, 1);
        assertEquals(1, pd.getLong(ALIGNED_WEEK_OF_MONTH));
    }

    @Test
    public void testOnGetLongALignedWeekOfYear() {
        PersianDate pd = PersianDate.of(1612, 2, 31);
        assertEquals(9, pd.getLong(ALIGNED_WEEK_OF_YEAR));
        pd = PersianDate.of(1999, 11, 30);
        assertEquals(48, pd.getLong(ALIGNED_WEEK_OF_YEAR));
    }

    @Test
    public void testOnGetLongDayOfMonth() {
        assertEquals(31, PersianDate.of(200, 4, 31).getLong(DAY_OF_MONTH));
    }

    @Test
    public void testOnGetLongDayOfYear() {
        assertEquals(124, PersianDate.of(14, 4, 31).getLong(DAY_OF_YEAR));
    }

    @Test
    public void testOnGetLongYearOfEra() {
        assertEquals(1781, PersianDate.of(1781, 11, 27).getLong(YEAR_OF_ERA));
    }

    @Test
    public void testOnGetLongYear() {
        assertEquals(451, PersianDate.of(451, 8, 21).getLong(YEAR));
    }

    @Test
    public void testOnGetLongMonthOfYear() {
        assertEquals(1, PersianDate.MIN.getLong(MONTH_OF_YEAR));
        assertEquals(12, PersianDate.MAX.getLong(MONTH_OF_YEAR));
    }

    @Test(expected = UnsupportedTemporalTypeException.class)
    public void testOnGetLongUnsupportedTemporal() {
        PersianDate.of(151, 12, 11).getLong(NANO_OF_DAY);
    }

    @Test
    public void testOnProlepticMonth() {
        PersianDate pd = PersianDate.of(1612, 2, 31);
        assertEquals(19345, pd.getLong(PROLEPTIC_MONTH));
        pd = PersianDate.of(1999, 11, 30);
        assertEquals(23998, pd.getLong(PROLEPTIC_MONTH));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnToGregorian() {
        PersianDate pdt = PersianDate.of(1396, 6, 20);
        LocalDate expected = LocalDate.of(2017, 9, 11);
        assertEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianMinPersianDate() {
        LocalDate expected = LocalDate.of(622, 3, 22);
        assertEquals(expected, PersianDate.MIN.toGregorian());
    }

    @Test
    public void testOnToGregorianMaxPersianDate() {
        LocalDate expected = LocalDate.of(2621, 3, 20);
        assertEquals(expected, PersianDate.MAX.toGregorian());
    }

    @Test
    public void testOnToGregorianInPersianLeapYear() {
        PersianDate pdt = PersianDate.of(1399, 12, 30);
        LocalDate expected = LocalDate.of(2021, 3, 20);
        assertEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianInGregorianLeapYear() {
        PersianDate pdt = PersianDate.of(1394, 12, 10);
        LocalDate expected = LocalDate.of(2016, 2, 29);
        assertEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianOnFirstOfPersianYear() {
        PersianDate pdt = PersianDate.of(1407, 1, 1);
        LocalDate expected = LocalDate.of(2028, 3, 20);
        assertEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianOnEndOfPersianYear() {
        PersianDate pdt = PersianDate.of(1376, 12, 29);
        LocalDate expected = LocalDate.of(1998, 3, 20);
        assertEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianOnFirstOfGregorianYear() {
        PersianDate pdt = PersianDate.of(1385, 10, 11);
        LocalDate expected = LocalDate.of(2007, 1, 1);
        assertEquals(expected, pdt.toGregorian());
    }

    @Test
    public void testOnToGregorianOnEndOfGregorianYear() {
        PersianDate pdt = PersianDate.of(1429, 10, 10);
        LocalDate expected = LocalDate.of(2050, 12, 31);
        assertEquals(expected, pdt.toGregorian());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnToPersian() {
        LocalDate ld = LocalDate.of(2046, 5, 10);
        PersianDate expected = PersianDate.of(1425, 2, 20);
        assertEquals(expected, PersianDate.fromGregorian(ld));
    }

    @Test
    public void testOnToPersianInGregorianLeapYear() {
        LocalDate ldt = LocalDate.of(2012, 2, 29);
        PersianDate expected = PersianDate.of(1390, 12, 10);
        assertEquals(expected, PersianDate.fromGregorian(ldt));
    }

    @Test
    public void testOnToPersianInPersianLeapYear() {
        LocalDate ldt = LocalDate.of(2034, 3, 20);
        PersianDate expected = PersianDate.of(1412, 12, 30);
        assertEquals(expected, PersianDate.fromGregorian(ldt));
    }

    @Test
    public void testOnToPersianOnFirstOfGregorianYear() {
        LocalDate ldt = LocalDate.of(2008, 1, 1);
        PersianDate expected = PersianDate.of(1386, 10, 11);
        assertEquals(expected, PersianDate.fromGregorian(ldt));
    }

    @Test
    public void testOnToPersianOnEndOfGregorianYear() {
        LocalDate ldt = LocalDate.of(2003, 3, 1);
        PersianDate expected = PersianDate.of(1381, 12, 10);
        assertEquals(expected, PersianDate.fromGregorian(ldt));
    }

    @Test
    public void testOnToPersianOnFirstOfPersianYear() {
        LocalDate ldt = LocalDate.of(1986, 3, 21);
        PersianDate expected = PersianDate.of(1365, 1, 1);
        assertEquals(expected, PersianDate.fromGregorian(ldt));
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
    public void testOnEqualsNull() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        assertNotEquals(null, a);
    }

    @Test
    public void testOnEqualsReflexivity() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        assertEquals(a, a);
    }

    @Test
    public void testOnEqualsSymmetricity() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        PersianDate b = PersianDate.of(1396, 3, 15);
        assertEquals(a, b);
        assertEquals(b, a);
    }

    @Test
    public void testOnEqualsTransitivity() {
        PersianDate a = PersianDate.of(1396, 3, 15);
        PersianDate b = PersianDate.of(1396, 3, 15);
        PersianDate c = PersianDate.of(1396, 3, 15);
        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    public void testOnHashCodeForEqualObjects() {
        PersianDate a = PersianDate.of(1500, 12, 15);
        PersianDate b = PersianDate.of(1500, 12, 15);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnToString() {
        PersianDate PersianDate1 = PersianDate.of(1391, 11, 6);
        assertEquals("1391-11-06", PersianDate1.toString());
        PersianDate PersianDate2 = PersianDate.of(31, 1, 12);
        assertEquals("0031-01-12", PersianDate2.toString());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnUntilTemporalDays() {
        PersianDate pd1 = PersianDate.of(1400, 1, 1);
        PersianDate pd2 = PersianDate.of(1401, 1, 1);
        assertEquals(365, pd1.until(pd2, DAYS));
        assertEquals(-365, pd2.until(pd1, DAYS));
        assertEquals(10365, pd1.until(pd2.plusDays(10000), DAYS));
        assertEquals(-10365, pd2.until(pd1.plusDays(-10000), DAYS));
    }

    @Test
    public void testOnUntilTemporalWeeks() {
        PersianDate pd1 = PersianDate.of(1400, 1, 1);
        PersianDate pd2 = PersianDate.of(1401, 1, 1);
        assertEquals(52, pd1.until(pd2, WEEKS));
    }

    @Test
    public void testOnUntilTemporalYears() {
        PersianDate pd1 = PersianDate.of(1000, 1, 1);
        PersianDate pd2 = PersianDate.of(1999, 12, 29);
        assertEquals(999, pd1.until(pd2, YEARS));
        assertEquals(1998, PersianDate.MIN.until(PersianDate.MAX, YEARS));
    }

    @Test
    public void testOnUntilTemporalDecades() {
        assertEquals(199, PersianDate.MIN.until(PersianDate.MAX, DECADES));
    }

    @Test
    public void testOnUntilTemporalCenturies() {
        assertEquals(19, PersianDate.MIN.until(PersianDate.MAX, CENTURIES));
    }

    @Test
    public void testOnUntilTemporalMillennia() {
        assertEquals(1, PersianDate.MIN.until(PersianDate.MAX, MILLENNIA));
        assertEquals(0, PersianDate.MIN.plusYears(1000).until(PersianDate.MAX, MILLENNIA));
    }

    @Test
    public void testOnUntilTemporalEra() {
        assertEquals(0, PersianDate.MIN.until(PersianDate.MAX, ERAS));
    }

    @Test(expected = UnsupportedTemporalTypeException.class)
    public void testOnUntilTemproalUnsoppurted() {
        PersianDate.MIN.until(PersianDate.MAX, NANOS);
    }

    @Test
    public void testOnUntilTemporalMonths() {
        PersianDate pd1 = PersianDate.of(1396, 1, 1);
        PersianDate pd2 = PersianDate.of(1396, 2, 1);
        assertEquals(1, pd1.until(pd2, MONTHS));
        assertEquals(-1, pd2.until(pd1, MONTHS));
        assertEquals(0, pd1.until(pd2.plusDays(-1), MONTHS));
        assertEquals(2, pd1.until(pd2.plusDays(31), MONTHS));
        // End of year
        pd1 = PersianDate.of(1387, 12, 1);
        pd2 = PersianDate.of(1388, 1, 1);
        assertEquals(1, pd1.until(pd2, MONTHS));
        assertEquals(-1, pd2.until(pd1, MONTHS));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testOnUntilChronoLocalDate1() {
        PersianDate pd1 = PersianDate.of(1380, 7, 16);
        PersianDate pd2 = PersianDate.of(1400, 2, 21);
        ChronoPeriod pd1UntilPd2 = pd1.until(pd2);
        assertEquals(19, pd1UntilPd2.get(YEARS));
        assertEquals(7, pd1UntilPd2.get(MONTHS));
        assertEquals(5, pd1UntilPd2.get(DAYS));
        ChronoPeriod pd2UntilPd1 = pd2.until(pd1);
        assertEquals(-19, pd2UntilPd1.get(YEARS));
        assertEquals(-7, pd2UntilPd1.get(MONTHS));
        assertEquals(-5, pd2UntilPd1.get(DAYS));
    }

    @Test
    public void testOnUntilChronoLocalDate2() {
        PersianDate pd1 = PersianDate.of(1396, 5, 10);
        PersianDate pd2 = PersianDate.of(1400, 11, 3);
        ChronoPeriod pd1UntilPd2 = pd1.until(pd2);
        assertEquals(4, pd1UntilPd2.get(YEARS));
        assertEquals(5, pd1UntilPd2.get(MONTHS));
        assertEquals(23, pd1UntilPd2.get(DAYS));
        ChronoPeriod pd2UntilPd1 = pd2.until(pd1);
        assertEquals(-4, pd2UntilPd1.get(YEARS));
        assertEquals(-5, pd2UntilPd1.get(MONTHS));
        assertEquals(-24, pd2UntilPd1.get(DAYS));
    }
}