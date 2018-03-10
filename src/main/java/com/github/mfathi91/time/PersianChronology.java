package com.github.mfathi91.time;

import net.jcip.annotations.Immutable;

import java.time.DateTimeException;
import java.time.chrono.AbstractChronology;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Era;
import java.time.temporal.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
     * Checks whther parameter {@code value} is valid or not. If {@code value} is out
     * of range, an DateTimeException will be thrown with a suitable message.
     *
     * @param value value to check
     */
    void checkValidValue(long value, TemporalField field) {
        Objects.requireNonNull(field, "field");
        if(!(field instanceof ChronoField)){
            throw new DateTimeException("Parameter 'field' is not supported");
        }
        ChronoField cf = (ChronoField) field;
        if(!MyUtils.isBetween(value, range(cf).getMinimum(), range(cf).getMaximum())){
            throw new DateTimeException("Invalid value for " + field + ", valid values: " + range(cf));
        }
    }

    /**
     * Checks whether parameter {@code dayOfYear} is valid in year {@code year}. If it is
     * not valid, an IllegalArgumentException will be thrown, otherwise {@code dayOfYear}
     * is returned.
     *
     * @param year      year that {@code dayOfYear} to be checked in, valid range is from
     *                  minimum year to maximum year
     * @param dayOfYear the day-of-year to be checked, from 1 to 365 or 366 in a leap year
     */
    void checkDayOfYear(int year, int dayOfYear) {
        checkValidValue(year, YEAR);
        int maxDayOfYear = isLeapYear(year) ? 366 : 365;
        if(!MyUtils.isBetween(dayOfYear, 1, maxDayOfYear)){
            throw new DateTimeException("Invalid value for dayOfYear: " + dayOfYear + " ");
        }
    }

    //-----------------------------------------------------------------------

    /**
     * Gets the ID of the chronology.
     * <p>
     * The ID uniquely identifies the {@code Chronology}. It can be used to
     * lookup the {@code Chronology} using {@link #of(String)}.
     *
     * @return the chronology ID, non-null
     * @see #getCalendarType()
     */
    @Override
    public String getId() {
        return "Persian";
    }

    /**
     * Gets the calendar type of the Islamic calendar.
     * <p>
     * The calendar type is an identifier defined by the
     * <em>Unicode Locale Data Markup Language (LDML)</em> specification.
     * It can be used to lookup the {@code Chronology} using {@link #of(String)}.
     *
     * @return the calendar system type; non-null if the calendar has
     * a standard type, otherwise null
     * @see #getId()
     */
    @Override
    public String getCalendarType() {
        return "persian";
    }

    /**
     * Obtains a local date in this chronology from the proleptic-year,
     * month-of-year and day-of-month fields.
     *
     * @param prolepticYear the chronology proleptic-year
     * @param month         the chronology month-of-year
     * @param dayOfMonth    the chronology day-of-month
     * @return the local date in this chronology, not null
     * @throws DateTimeException if unable to create the date
     */
    @Override
    public PersianDate date(int prolepticYear, int month, int dayOfMonth) {
        return PersianDate.of(prolepticYear, month, dayOfMonth);
    }

    /**
     * Obtains a local date in this chronology from the proleptic-year and
     * day-of-year fields.
     *
     * @param prolepticYear the chronology proleptic-year
     * @param dayOfYear     the chronology day-of-year
     * @return the local date in this chronology, not null
     * @throws DateTimeException if unable to create the date
     */
    @Override
    public PersianDate dateYearDay(int prolepticYear, int dayOfYear) {
        checkDayOfYear(prolepticYear, dayOfYear);
        return PersianDate.of(prolepticYear, 1, 1).plusDays(dayOfYear - 1);
    }

    /**
     * Obtains a local date in this chronology from the epoch-day.
     * <p>
     * The definition of {@link ChronoField#EPOCH_DAY EPOCH_DAY} is the same
     * for all calendar systems, thus it can be used for conversion.
     *
     * @param epochDay the epoch day
     * @return the local date in this chronology, not null
     * @throws DateTimeException if unable to create the date
     */
    @Override
    public PersianDate dateEpochDay(long epochDay) {
        return PersianDate.ofEpochDay(epochDay);
    }

    /**
     * Obtains a local date in this chronology from another temporal object.
     * <p>
     * This obtains a date in this chronology based on the specified temporal.
     * A {@code TemporalAccessor} represents an arbitrary set of date and time information,
     * which this factory converts to an instance of {@code ChronoLocalDate}.
     * <p>
     * The conversion typically uses the {@link ChronoField#EPOCH_DAY EPOCH_DAY}
     * field, which is standardized across calendar systems.
     * <p>
     * This method matches the signature of the functional interface {@link TemporalQuery}
     * allowing it to be used as a query via method reference, {@code aChronology::date}.
     *
     * @param temporal the temporal object to convert, not null
     * @return the local date in this chronology, not null
     * @throws DateTimeException if unable to create the date
     * @see ChronoLocalDate#from(TemporalAccessor)
     */
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
     *             range, check {@link #range(ChronoField YEAR)}.
     * @return true if {@code year} is a leap year in Persian calendar
     */
    @Override
    public boolean isLeapYear(long year) {
        checkValidValue(year, YEAR);
        return PersianDate.toJulianDay((int) (year + 1), 1, 1) -
                PersianDate.toJulianDay((int) year, 1, 1) > 365;
    }

    /**
     * Calculates the proleptic-year given the era and year-of-era.
     * <p>
     * This combines the era and year-of-era into the single proleptic-year field.
     *
     * @param era       the era of the correct type for the chronology, not null
     * @param yearOfEra the chronology year-of-era
     * @return the proleptic-year
     * @throws DateTimeException  if unable to convert to a proleptic-year,
     *                            such as if the year is invalid for the era
     * @throws ClassCastException if the {@code era} is not of the correct type for the chronology
     */
    @Override
    public int  prolepticYear(Era era, int yearOfEra) {
        if (!(era instanceof PersianEra)) {
            throw new ClassCastException("Era must be PersianEra");
        }
        return yearOfEra;
    }

    /**
     * Creates the chronology era object from the numeric value.
     * <p>
     * The era is, conceptually, the largest division of the time-line.
     * Most calendar systems have a single epoch dividing the time-line into two eras.
     * However, some have multiple eras, such as one for the reign of each leader.
     * The exact meaning is determined by the chronology according to the following constraints.
     * <p>
     * This method returns the singleton era of the correct type for the specified era value.
     *
     * @param eraValue the era value
     * @return the calendar system era, not null
     * @throws DateTimeException if unable to create the era
     */
    @Override
    public Era eraOf(int eraValue) {
        if (eraValue == 1) {
            return PersianEra.AHS;
        }
        throw new DateTimeException("invalid Persian era");
    }

    /**
     * Gets the list of eras for the chronology.
     *
     * @return the list of eras for the chronology, may be immutable, not null
     */
    @Override
    public List<Era> eras() {
        return Arrays.asList(PersianEra.values());
    }

    /**
     * Gets the range of valid values for the specified field.
     * <p>
     * All fields can be expressed as a {@code long} integer.
     * This method returns an object that describes the valid range for that value.
     * <p>
     * Note that the result only describes the minimum and maximum valid values
     * and it is important not to read too much into them. For example, there
     * could be values within the range that are invalid for the field.
     * <p>
     * This method will return a result whether or not the chronology supports the field.
     *
     * @param field the field to get the range for, not null
     * @return the range of valid values for the field, not null
     * @throws DateTimeException if the range for the field cannot be obtained
     */
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
