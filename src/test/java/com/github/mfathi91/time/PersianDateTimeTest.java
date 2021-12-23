package com.github.mfathi91.time;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Provides unit tests for {@link PersianDateTime}.
 *
 * @author Mahmoud Fathi
 */
public class PersianDateTimeTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testOf() {
        final PersianDate date = PersianDate.of(1400, PersianMonth.AZAR, 25);
        final LocalTime time = LocalTime.of(5, 12);
        final PersianDateTime dateTime = PersianDateTime.of(date, time);
        assertEquals(date, dateTime.toLocalDate());
        assertEquals(time, dateTime.toLocalTime());
    }

    @Test
    public void testOf2() {
        final PersianDate date = PersianDate.of(1400, PersianMonth.AZAR, 25);
        final LocalTime time = LocalTime.of(5, 12);
        final PersianDateTime dateTime = PersianDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                time.getHour(), time.getMinute());
        assertEquals(date, dateTime.toLocalDate());
        assertEquals(time, dateTime.toLocalTime());
    }

    @Test
    public void testOf3() {
        final PersianDate date = PersianDate.of(1400, PersianMonth.AZAR, 25);
        final LocalTime time = LocalTime.of(5, 12, 35);
        final PersianDateTime dateTime = PersianDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                time.getHour(), time.getMinute(), time.getSecond());
        assertEquals(date, dateTime.toLocalDate());
        assertEquals(time, dateTime.toLocalTime());
    }

    @Test
    public void testOf4() {
        final PersianDate date = PersianDate.of(1400, PersianMonth.AZAR, 25);
        final LocalTime time = LocalTime.of(5, 12, 35, 888888888);
        final PersianDateTime dateTime = PersianDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                time.getHour(), time.getMinute(), time.getSecond(), time.getNano());
        assertEquals(date, dateTime.toLocalDate());
        assertEquals(time, dateTime.toLocalTime());
    }

    @Test
    public void testOf5() {
        final PersianDate date = PersianDate.of(1400, 9, 25);
        final LocalTime time = LocalTime.of(5, 12);
        final PersianDateTime dateTime = PersianDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                time.getHour(), time.getMinute());
        assertEquals(date, dateTime.toLocalDate());
        assertEquals(time, dateTime.toLocalTime());
    }

    @Test
    public void testOf6() {
        final PersianDate date = PersianDate.of(1400, 9, 25);
        final LocalTime time = LocalTime.of(5, 12, 35);
        final PersianDateTime dateTime = PersianDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                time.getHour(), time.getMinute(), time.getSecond());
        assertEquals(date, dateTime.toLocalDate());
        assertEquals(time, dateTime.toLocalTime());
    }

    @Test
    public void testOf7() {
        final PersianDate date = PersianDate.of(1400, 9, 25);
        final LocalTime time = LocalTime.of(5, 12, 35, 888888888);
        final PersianDateTime dateTime = PersianDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(),
                time.getHour(), time.getMinute(), time.getSecond(), time.getNano());
        assertEquals(date, dateTime.toLocalDate());
        assertEquals(time, dateTime.toLocalTime());
    }

    //-----------------------------------------------------------------------

    @Test
    public void testToGregorian() {
        final PersianDateTime persianDateTime = PersianDateTime.of(1400, PersianMonth.ESFAND, 21, 17, 29, 33, 123456);
        final LocalDateTime expectedGregorianDateTime = LocalDateTime.of(2022, 3, 12, 17, 29, 33, 123456);
        final LocalDateTime actualGregorianDateTime = persianDateTime.toGregorian();
        assertEquals(expectedGregorianDateTime, actualGregorianDateTime);
    }

    @Test
    public void testToPersianDateTime() {
        final LocalDateTime gregorianDateTime = LocalDateTime.of(2027, Month.NOVEMBER, 2, 13, 19, 46, 654321);
        final PersianDateTime expectedPersianDateTime = PersianDateTime.of(1406, 8, 11, 13, 19, 46, 654321);
        final PersianDateTime actualPersianDateTime = PersianDateTime.fromGregorian(gregorianDateTime);
        assertEquals(expectedPersianDateTime, actualPersianDateTime);
    }

    @Test
    public void testParse() {
        {
            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            final PersianDateTime expectedPersianDateTime = PersianDateTime.of(1404, 11, 7, 9, 51);
            final PersianDateTime actualPersianDateTime = PersianDateTime.parse("1404/11/07 09:51", dtf);
            assertEquals(expectedPersianDateTime, actualPersianDateTime);
        }
        {
            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd HH:mm a");
            final PersianDateTime expectedPersianDateTime = PersianDateTime.of(1404, 12, 29, 19, 27);
            final PersianDateTime actualPersianDateTime = PersianDateTime.parse("1404_12_29 19:27 PM", dtf);
            assertEquals(expectedPersianDateTime, actualPersianDateTime);
        }
        {
            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
            final PersianDateTime actualPersianDateTime = PersianDateTime.parse("1400-06-15_11-38-43", dtf);
            final PersianDateTime expectedPersianDateTime = PersianDateTime.of(1400, 6, 15, 11, 38, 43);
            assertEquals(expectedPersianDateTime, actualPersianDateTime);
        }
        {
            final PersianDateTime actualPersianDateTime = PersianDateTime.parse("1400-12-03T12:15:30");
            final PersianDateTime expectedPersianDateTime = PersianDateTime.of(1400, 12, 3, 12, 15, 30);
            assertEquals(expectedPersianDateTime, actualPersianDateTime);
        }
        {
            exception.expect(DateTimeException.class);
            PersianDateTime.parse("140A-12-03T12:15:30");
        }
    }

    @Test
    public void testGetLong() {

        final PersianDateTime dateTime = PersianDateTime.of(1400, PersianMonth.ESFAND, 25, 5, 19);
        assertEquals(1400, dateTime.getLong(ChronoField.YEAR));
        assertEquals(12, dateTime.getLong(ChronoField.MONTH_OF_YEAR));
        assertEquals(25, dateTime.getLong(ChronoField.DAY_OF_MONTH));
        assertEquals(361, dateTime.getLong(ChronoField.DAY_OF_YEAR));
        assertEquals(5, dateTime.getLong(ChronoField.HOUR_OF_DAY));
        assertEquals(19, dateTime.getLong(ChronoField.MINUTE_OF_HOUR));
    }

    //-----------------------------------------------------------------------

    @Test
    public void testCompareTo() {
        final PersianDateTime pdt1 = PersianDateTime.of(1404, PersianMonth.KHORDAD, 19, 8, 43, 59, 441234);
        final PersianDateTime pdt2 = PersianDateTime.of(1404, PersianMonth.KHORDAD, 19, 8, 43, 59, 441234);
        assertEquals(0, pdt1.compareTo(pdt2));
        assertEquals(0, pdt2.compareTo(pdt1));

        final PersianDateTime pdt3 = PersianDateTime.of(1387, PersianMonth.KHORDAD, 19, 8, 43, 59, 441234);
        final PersianDateTime pdt4 = PersianDateTime.of(1387, PersianMonth.MEHR, 1, 12, 0, 0, 0);
        assertEquals(-1, pdt3.compareTo(pdt4));
        assertEquals(1, pdt4.compareTo(pdt3));

        final PersianDateTime pdt5 = PersianDateTime.of(1420, PersianMonth.ORDIBEHESHT, 19, 8, 43, 59, 441234);
        final PersianDateTime pdt6 = PersianDateTime.of(1407, PersianMonth.SHAHRIVAR, 22, 0, 0, 0, 0);
        assertEquals(1, pdt5.compareTo(pdt6));
        assertEquals(-1, pdt6.compareTo(pdt5));
    }

    @Test
    public void testEquals() {
        final PersianDateTime pdt1 = PersianDateTime.of(1404, PersianMonth.KHORDAD, 19, 8, 43, 59, 441234);
        final PersianDateTime pdt2 = PersianDateTime.of(1404, PersianMonth.KHORDAD, 19, 8, 43, 59, 441234);
        assertEquals(pdt1, pdt2);
        assertEquals(pdt2, pdt1);

        final PersianDateTime pdt3 = PersianDateTime.of(1387, PersianMonth.KHORDAD, 19, 8, 43, 59, 441234);
        final PersianDateTime pdt4 = PersianDateTime.of(1387, PersianMonth.MEHR, 1, 12, 0, 0, 0);
        assertNotEquals(pdt3, pdt4);
        assertNotEquals(pdt4, pdt3);

        assertNotEquals(null, pdt1);
        assertNotEquals(pdt1, null);
    }

    @Test
    public void testHashcode() {
        final PersianDateTime pdt1 = PersianDateTime.of(1404, PersianMonth.KHORDAD, 19, 8, 43, 59, 441234);
        final PersianDateTime pdt2 = PersianDateTime.of(1404, PersianMonth.KHORDAD, 19, 8, 43, 59, 441234);
        assertEquals(pdt1.hashCode(), pdt2.hashCode());

        final PersianDateTime pdt3 = PersianDateTime.of(1387, PersianMonth.KHORDAD, 19, 8, 43, 59, 441234);
        final PersianDateTime pdt4 = PersianDateTime.of(1387, PersianMonth.MEHR, 1, 12, 0, 0, 0);
        assertNotEquals(pdt3.hashCode(), pdt4.hashCode());

        final PersianDateTime pdt5 = PersianDateTime.of(1404, PersianMonth.KHORDAD, 19, 8, 43, 59, 441234);
        final PersianDateTime pdt6 = PersianDateTime.of(1404, PersianMonth.KHORDAD, 19, 8, 43, 59, 441235);
        assertNotEquals(pdt5.hashCode(), pdt6.hashCode());
    }
}