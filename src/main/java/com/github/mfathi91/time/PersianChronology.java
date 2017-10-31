package com.github.mfathi91.time;

import net.jcip.annotations.Immutable;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.AbstractChronology;
import java.time.chrono.Era;
import java.time.temporal.ChronoField;
import java.time.temporal.JulianFields;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoField.EPOCH_DAY;
import static java.time.temporal.ChronoField.YEAR;

/**
 * The Persian calendar (also known as Jalali calendar or Iranian calendar) is a solar calendar.
 * PersianChronology follows the rules of Persian Calendar.
 * <p>
 * Length of each month is between 29 to 31 days. Details of each month is implemented in
 * {@link PersianMonth} enum.
 * <p>
 * Normal yeras have 365 days and leap years have 366 days.
 * <p>
 * This is an immutable and thread-safe enum.
 *
 * @author Mahmoud Fathi
 */
@Immutable
public final class PersianChronology extends AbstractChronology {
    /**
     * Single instance of this class.
     */
    public static final PersianChronology INSTANCE = new PersianChronology();

    //-----------------------------------------------------------------------
    /**
     * Restricted constructor.
     */
    private PersianChronology() {
    }

    //-----------------------------------------------------------------------

    /**
     * Checks whther parameter {@code year} is valid or not. If {@code year} is out
     * of range, an IllegalArgumentException will be thrown, otherwise {@code year}
     * is returned.
     *
     * @param year year to be checked, valid range is from minimum year to maximum year
     * @return the same value as parameter {@code year}
     */
    long checkValidYear(long year) {
        return MyUtils.longRequireRange(year, INSTANCE.range(YEAR).getMinimum(),
                INSTANCE.range(YEAR).getMaximum(), "year");
    }

    /**
     * Checks whther parameter {@code month} is valid or not. If {@code month} is out
     * of range, an IllegalArgumentException will be thrown, otherwise {@code month}
     * is returned.
     *
     * @param month month to be checked, valid range is from 1 to 12
     * @return the same value as parameter {@code month}
     */
    int checkValidMonth(int month) {
        return Month.of(month).getValue();
    }

    /**
     * Checks whether parameter {@code dayOfYear} is valid in year {@code year}. If it is
     * not valid, an IllegalArgumentException will be thrown, otherwise {@code dayOfYear}
     * is returned.
     *
     * @param year      year that {@code dayOfYear} to be checked in, valid range is from
     *                  minimum year to maximum year
     * @param dayOfYear the day-of-year to be checked, from 1 to 365 or 366 in a leap year
     * @return {@code dayOfYear}
     */
    int checkDayOfYear(int year, int dayOfYear) {
        checkValidYear(year);
        int maxDayOfYear = isLeapYear(year) ? 366 : 365;
        return MyUtils.intRequireRange(dayOfYear, 1, maxDayOfYear, "dayOfYear");
    }

    //-----------------------------------------------------------------------
    @Override
    public String getId() {
        return "Persian";
    }

    @Override
    public String getCalendarType() {
        return "persian";
    }

    @Override
    public PersianDate date(int prolepticYear, int month, int dayOfMonth) {
        return PersianDate.of(prolepticYear, month, dayOfMonth);
    }

    @Override
    public PersianDate dateYearDay(int prolepticYear, int dayOfYear) {
        checkDayOfYear(prolepticYear, dayOfYear);
        return PersianDate.of(prolepticYear, 1, 1).plusDays(dayOfYear - 1);
    }

    @Override
    public PersianDate dateEpochDay(long epochDay) {
        return PersianDate.ofJulianDays(LocalDate.ofEpochDay(epochDay).getLong(JulianFields.JULIAN_DAY) - 1);
    }

    @Override
    public PersianDate date(TemporalAccessor temporal) {
        if (temporal instanceof PersianDate) {
            return (PersianDate) temporal;
        }
        return PersianDate.ofEpochDay(temporal.getLong(EPOCH_DAY));
    }

    /**
     * Returns true if {@code year} is a leap year in Persian calendar.
     *
     * @param year the year to be checked whether is a leap year or not. For valid
     *                      range, check {@link #range(ChronoField YEAR)}.
     * @return true if {@code year} is a leap year in Persian calendar
     */
    @Override
    public boolean isLeapYear(long year) {
        checkValidYear(year);
        return PersianDate.toJulianDay((int) (year+1), 1, 1) -
                PersianDate.toJulianDay((int) year, 1, 1) > 365;
    }

    @Override
    public int prolepticYear(Era era, int yearOfEra) {
        if (era instanceof PersianEra == false) {
            throw new ClassCastException("Era must be PersianEra");
        }
        return yearOfEra;
    }

    @Override
    public Era eraOf(int eraValue) {
        if(eraValue == 1){
            return PersianEra.AHS;
        }
        throw new DateTimeException("invalid Persian era");
    }

    @Override
    public List<Era> eras() {
        return Arrays.asList(PersianEra.values());
    }

    @Override
    public ValueRange range(ChronoField field) {
        switch (field) {
            case DAY_OF_MONTH:
                return ValueRange.of(1, 1, 29, 31);
            case DAY_OF_YEAR:
                return ValueRange.of(1, 1, 365, 366);
            case ALIGNED_WEEK_OF_MONTH:
                return ValueRange.of(1, 5);
            case YEAR:
            case YEAR_OF_ERA:
                return ValueRange.of(1, 1999);
            case ERA:
                return ValueRange.of(1, 1);
            default:
                return field.range();
        }
    }
}
