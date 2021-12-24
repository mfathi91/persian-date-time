package com.github.mfathi91.time;

import net.jcip.annotations.Immutable;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Objects;

/**
 * A wrapper class for {@link PersianDate} and {@link LocalTime} to encapsulate these classes in one class for the sake
 * of simplicity and convenience.
 * <p>
 * This class is immutable and can be used in multi-threaded environments.
 *
 * @author Mahmoud Fathi
 */
@Immutable
public final class PersianDateTime implements ChronoLocalDateTime<PersianDate> {

    /**
     * The minimum supported Persian date-time {@code 0001-01-01 00:00}.
     */
    private static final PersianDateTime MIN = new PersianDateTime(PersianDate.MIN, LocalTime.MIN);

    /**
     * The minimum supported Persian date-time {@code 1999-12-29 23:59:59.999999999}.
     */
    private static final PersianDateTime MAX = new PersianDateTime(PersianDate.MAX, LocalTime.MAX);

    /**
     * The Persian date.
     */
    private final PersianDate date;

    /**
     * The local time.
     */
    private final LocalTime time;

    /**
     * Returns an instance of this class with the actual current date and time.
     *
     * @return an instance of this class with the actual current date and time
     */
    public static PersianDateTime now() {
        return new PersianDateTime(PersianDate.now(), LocalTime.now());
    }

    /**
     * Obtains a new instance of this class with the given date and time.
     *
     * @param date the date, not {@code null}
     * @param time the time, not {@code  null}
     * @return a new instance of this clas with the given date and time
     */
    public static PersianDateTime of(final PersianDate date, final LocalTime time) {
        return new PersianDateTime(date, time);
    }

    /**
     * Obtains an instance of {@code PersianDateTime} with the given parameters, having second and nanosecond set to
     * zero.
     *
     * @param year the year to represent, from 1 to MAX_YEAR
     * @param month the Persian month, not {@code null}
     * @param dayOfMonth the dayOfMonth to represent, from 1 to 31
     * @param hour the hour of the day, from 0 to 23
     * @param minute the minute of the hour, from 0 to 59
     * @return an instance of {@code PersianDateTime}
     * @throws DateTimeException if the passed parameters do not form a valid date or time
     */
    public static PersianDateTime of(final int year, final PersianMonth month, final int dayOfMonth, final int hour,
            final int minute) {

        final PersianDate date = PersianDate.of(year, month, dayOfMonth);
        final LocalTime time = LocalTime.of(hour, minute);
        return new PersianDateTime(date, time);
    }

    /**
     * Obtains an instance of {@code PersianDateTime} with the given parameters, having nanosecond set to zero.
     *
     * @param year the year to represent, from 1 to MAX_YEAR
     * @param month the Persian month, not {@code null}
     * @param dayOfMonth the dayOfMonth to represent, from 1 to 31
     * @param hour the hour of the day, from 0 to 23
     * @param minute the minute of the hour, from 0 to 59
     * @param second the second of minute, from 0 to 59
     * @return an instance of {@code PersianDateTime}
     * @throws DateTimeException if the passed parameters do not form a valid date or time
     */
    public static PersianDateTime of(final int year, final PersianMonth month, final int dayOfMonth, final int hour,
            final int minute, final int second) {

        final PersianDate date = PersianDate.of(year, month, dayOfMonth);
        final LocalTime time = LocalTime.of(hour, minute, second);
        return new PersianDateTime(date, time);
    }

    /**
     * Obtains an instance of {@code PersianDateTime} with the given parameters.
     *
     * @param year the year to represent, from 1 to MAX_YEAR
     * @param month the Persian month, not {@code null}
     * @param dayOfMonth the dayOfMonth to represent, from 1 to 31
     * @param hour the hour of the day, from 0 to 23
     * @param minute the minute of the hour, from 0 to 59
     * @param second the second of minute, from 0 to 59
     * @param nanoOfSecond the nanosecond of second, from 0 to 999,999,999
     * @return an instance of {@code PersianDateTime}
     * @throws DateTimeException if the passed parameters do not form a valid date or time.
     */
    public static PersianDateTime of(final int year, final PersianMonth month, final int dayOfMonth, final int hour,
            final int minute, final int second, final int nanoOfSecond) {

        final PersianDate date = PersianDate.of(year, month, dayOfMonth);
        final LocalTime time = LocalTime.of(hour, minute, second, nanoOfSecond);
        return new PersianDateTime(date, time);
    }

    /**
     * Obtains an instance of {@code PersianDateTime} with the given parameters, having second and nanosecond set to
     * zero.
     *
     * @param year the year to represent, from 1 to MAX_YEAR
     * @param month the value of month, from 1 to 12
     * @param dayOfMonth the dayOfMonth to represent, from 1 to 31
     * @param hour the hour of the day, from 0 to 23
     * @param minute the minute of the hour, from 0 to 59
     * @return an instance of {@code PersianDateTime}
     * @throws DateTimeException if the passed parameters do not form a valid date or time.
     */
    public static PersianDateTime of(final int year, final int month, final int dayOfMonth, final int hour,
            final int minute) {

        final PersianDate date = PersianDate.of(year, month, dayOfMonth);
        final LocalTime time = LocalTime.of(hour, minute);
        return new PersianDateTime(date, time);
    }

    /**
     * Obtains an instance of {@code PersianDateTime} with the given parameters, having nanosecond set to zero.
     *
     * @param year the year to represent, from 1 to MAX_YEAR
     * @param month the value of month, from 1 to 12
     * @param dayOfMonth the dayOfMonth to represent, from 1 to 31
     * @param hour the hour of the day, from 0 to 23
     * @param minute the minute of the hour, from 0 to 59
     * @param second the second of the minute, from 0 to 59
     * @return an instance of {@code PersianDateTime}
     * @throws DateTimeException if the passed parameters do not form a valid date or time.
     */
    public static PersianDateTime of(final int year, final int month, final int dayOfMonth, final int hour,
            final int minute, final int second) {

        final PersianDate date = PersianDate.of(year, month, dayOfMonth);
        final LocalTime time = LocalTime.of(hour, minute, second);
        return new PersianDateTime(date, time);
    }

    /**
     * Obtains an instance of {@code PersianDateTime} with the given parameters.
     *
     * @param year the year to represent, from 1 to MAX_YEAR
     * @param month the value of month, from 1 to 12
     * @param dayOfMonth the dayOfMonth to represent, from 1 to 31
     * @param hour the hour of the day, from 0 to 23
     * @param minute the minute of the hour, from 0 to 59
     * @param second the second of the minute, from 0 to 59
     * @param nanoOfSecond the nanosecond of the second, from 0 to 999,999,999
     * @return an instance of {@code PersianDateTime}
     * @throws DateTimeException if the passed parameters do not form a valid date or time.
     */
    public static PersianDateTime of(final int year, final int month, final int dayOfMonth, final int hour,
            final int minute, final int second, final int nanoOfSecond) {

        final PersianDate date = PersianDate.of(year, month, dayOfMonth);
        final LocalTime time = LocalTime.of(hour, minute, second, nanoOfSecond);
        return new PersianDateTime(date, time);
    }

    /**
     * Returns a new instance of Persian date-time, where the date part of the given date-time gets converted to Persian
     * date, and the time part of the returned date-time will remain unchanged.
     *
     * @param localDateTime the Gregorian local date time, not {@code null}
     * @return the corresponding Persian date-time of the given Gregorian date-time
     */
    public static PersianDateTime fromGregorian(final LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        return new PersianDateTime(PersianDate.fromGregorian(localDateTime.toLocalDate()), localDateTime.toLocalTime());
    }

    /**
     * Parses the given string into its corresponding Persian date-time. The given string is expected to have {@link
     * DateTimeFormatter#ISO_LOCAL_DATE_TIME}. For example this is an accepted input string: {@code
     * 1401-06-10T08:35:11}.
     *
     * @param text the input string with {@link DateTimeFormatter#ISO_LOCAL_DATE_TIME}, not {@code null}
     * @return the corresponding Persian date-time of the given string
     * @throws DateTimeException if the format of the given string is not {@link DateTimeFormatter#ISO_LOCAL_DATE_TIME}
     * @see DateTimeFormatter#ISO_LOCAL_DATE_TIME
     */
    public static PersianDateTime parse(final CharSequence text) {
        return parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Parses the given string with the given formatter into its corresponding Persian date-time.
     *
     * @param text the input string with the given formatter pattern, not {@code null}
     * @param formatter the formatter to parse the string from, not {@code  null}
     * @return the corresponding Persian date-time of the given string
     * @throws DateTimeException if the format of the given string is unexpected
     * @see DateTimeFormatter
     */
    public static PersianDateTime parse(final CharSequence text, final DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter, "formatter");
        return formatter.withChronology(PersianChronology.INSTANCE).parse(text, PersianDateTime::from);
    }

    /**
     * Obtains an instance of this class from the given temporal object.
     *
     * @param temporal the temporal object to convert, not {@code null}
     * @return the local date, not {@code null}
     * @throws DateTimeException if unable to convert to a {@code PersianDate}
     */
    public static PersianDateTime from(final TemporalAccessor temporal) {
        Objects.requireNonNull(temporal, "temporal");
        if (temporal instanceof PersianDateTime) {
            return (PersianDateTime) temporal;
        } else {
            try {
                final PersianDate date = PersianDate.from(temporal);
                final LocalTime time = LocalTime.from(temporal);
                return new PersianDateTime(date, time);
            } catch (final DateTimeException ex) {
                throw new DateTimeException(
                        String.format("Unable to parse PersianDateTime from TemporalAccessor: %s", temporal), ex);
            }
        }
    }

    /**
     * Creates a new instance of this class.
     *
     * @param date the date, not {@code null}
     * @param time the time, not {@code null}
     */
    private PersianDateTime(final PersianDate date, final LocalTime time) {
        this.date = Objects.requireNonNull(date, "date");
        this.time = Objects.requireNonNull(time, "time");
    }

    /**
     * Returns the date part of this date-time.
     *
     * @return the date part of this date-time.
     */
    @Override
    public PersianDate toLocalDate() {
        return date;
    }

    /**
     * Returns the time part of this date-time.
     *
     * @return the time part of this date-time.
     */
    @Override
    public LocalTime toLocalTime() {
        return time;
    }

    /**
     * Returns the corresponding Gregorian date-time of this Persian date-time. The transformation happens by converting
     * the date part of the current Persian date-time to Gregorian date, and time part will remain unchanged.
     *
     * @return the corresponding Gregorian date-time of this Persian date-time
     */
    public LocalDateTime toGregorian() {
        return LocalDateTime.of(date.toGregorian(), time);
    }

    @Override
    public boolean isSupported(final TemporalField field) {
        return false;
    }

    /**
     * Returns the {@code long} value of this date-time for the given temporal field.
     *
     * @param field the temporal field, not {@code null}
     * @return the {@code long} value of this date-time for the given temporal field
     * @see PersianDate#getLong(TemporalField)
     * @see LocalTime#getLong(TemporalField)
     */
    @Override
    public long getLong(final TemporalField field) {
        Objects.requireNonNull(field, "field");
        if (field instanceof ChronoField) {
            final ChronoField chronoField = (ChronoField) field;
            return (chronoField.isTimeBased() ? time.getLong(field) : date.getLong(field));
        }
        return field.getFrom(this);
    }

    @Override
    public ChronoLocalDateTime<PersianDate> with(final TemporalField field, final long newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ChronoLocalDateTime<PersianDate> plus(final long amountToAdd, final TemporalUnit temporalUnit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long until(final Temporal temporal, final TemporalUnit temporalUnit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ChronoZonedDateTime<PersianDate> atZone(final ZoneId zoneId) {
        throw new UnsupportedOperationException();
    }

    //-----------------------------------------------------------------------

    @Override
    public int compareTo(final ChronoLocalDateTime<?> other) {

        int result = 0;
        if (other instanceof PersianDateTime) {
            final PersianDateTime otherPersianDateTime = (PersianDateTime) other;
            result = date.compareTo(otherPersianDateTime.date);
            if (result == 0) {
                result = time.compareTo(otherPersianDateTime.time);
            }
        }
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PersianDateTime) {
            return compareTo((PersianDateTime) obj) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(date, time);
    }

    /**
     * Returns the string representation of this date-time. Character 'T' will be placed between date and time, to
     * indicate their separation. For example:
     * <pre>
     *     PersianDateTime dt = PersianDateTime.of(1400, PersianMonth.AZAR, 28, 14, 20, 56);
     *     System.out.println(dt.toString());    // 1400-09-28T14:20:56
     * </pre>
     * <p>
     * Based on the available fields for the time, the format of output string will differ.
     *
     * @return a suitable representation of this Persian date-time
     * @see PersianDate#toString()
     * @see LocalTime#toString()
     */
    public String toString() {
        return String.format("%sT%s", date, time);
    }
}
