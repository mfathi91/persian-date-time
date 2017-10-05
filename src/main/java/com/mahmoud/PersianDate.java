package com.mahmoud;

import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import net.jcip.annotations.Immutable;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * A persian date without time class, based on ICU4J library.
 *
 * <p>
 * {@code PersianDate} is an immutable date-time object that represents a date,
 * often viewed as year-month-day.
 *
 * <p>
 * This class is immutable and can be used in multi-threaded programs.
 *
 * @author Mahmoud Fathi
 */
@Immutable
public final class PersianDate implements Comparable<PersianDate> {

    /**
     * Persian date.
     */
    private final Calendar persianDate;

    /**
     * Corresponding gregorian date. Since gregorian date is used frequently in the internal
     * implementation of this class, it is cached.
     */
    private final LocalDate gregDate;

    /**
     * Maximim tested year.
     */
    private static int MAX_PERSIAN_YEAR = 1500;

    /**
     * @return the year
     */
    public int getYear() {
        return persianDate.get(Calendar.YEAR);
    }

    /**
     * @return the month-of-year field using the {@code Month} enum.
     * @see #getMonthValue()
     */
    public PersianMonth getMonth() {
        return PersianMonth.of(persianDate.get(Calendar.MONTH) + 1);
    }

    /**
     * @return the month-of-year, from 1 to 12
     * @see #getMonth()
     */
    public int getMonthValue() {
        return persianDate.get(Calendar.MONTH) + 1;
    }

    /**
     * @return day-of-month, from 1 to 31
     */
    public int getDayOfMonth() {
        return persianDate.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @return day-of-year, from 1 to 365 or from 1 to 366 in a leap year
     */
    public int getDayOfYear(){
        return persianDate.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Returns day-of-week as an enum {@link DayOfWeek}. This avoids confusion as to what
     * {@code int} means. If you need access to the primitive {@code int} value then the
     * enum provides the {@link DayOfWeek#getValue() int value}.
     *
     * @return day-of-week, which is an enum {@link DayOfWeek}
     */
    public DayOfWeek getDayOfWeek(){
        int dayOfWeek = persianDate.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek != 1) ? DayOfWeek.of(dayOfWeek - 1) : DayOfWeek.SUNDAY;
    }

    /**
     * Obtains current Persian date from the system clock in the default time zone.
     *
     * @return current Persian date from the system clock in the default time zone
     */
    public static PersianDate now() {
        return gregorianToPersian(LocalDate.now());
    }

    /**
     * Obtains an instance of {@code PersianDate} with year, month, day of month, hour,
     * minute and second. The value of month must be between {@code 1} and {@code 12}.
     * Value {@code 1} would be {@link PersianMonth#FARVARDIN} and value {@code 12} represents
     * {@link PersianMonth#ESFAND}.
     *
     * @param year       the year to represent, from 1 to MAX_YEAR
     * @param month      the value of month, from 1 to 12
     * @param dayOfMonth the dayOfMonth to represent, from 1 to 31
     * @return an instance of {@code PersianDate}
     * @throws DateTimeException if the passed parameters do not form a valid date or time.
     */
    public static PersianDate of(int year, int month, int dayOfMonth) {
        return new PersianDate(year, PersianMonth.of(month), dayOfMonth);
    }

    /**
     * Obtains an instance of {@code PersianDate} with year, month, day of month, hour,
     * minute and second.
     *
     * @param year       the year to represent, from 1 to MAX_YEAR
     * @param month      the month-of-year to represent, an instance of {@link PersianMonth}
     * @param dayOfMonth the dayOfMonth to represent, from 1 to 31
     * @return an instance of {@code PersianDate}
     * @throws DateTimeException if the passed parameters do not form a valid date or time.
     */
    public static PersianDate of(int year, PersianMonth month, int dayOfMonth) {
        return new PersianDate(year, month, dayOfMonth);
    }

    /**
     * Constructor.
     *
     * @param year       the year to represent, from 1 to MAX_YEAR
     * @param month      the month-of-year to represent, not null, from {@link PersianMonth} enum
     * @param dayOfMonth the dayOfMonth-of-month to represent, from 1 to 31
     * @throws DateTimeException if the passed parameters do not form a valid date or time.
     */
    private PersianDate(int year, PersianMonth month, int dayOfMonth) {
        MyUtils.integerRequiresRange(year, 1, MAX_PERSIAN_YEAR, "year");
        Objects.requireNonNull(month, "month must not be null");
        MyUtils.integerRequiresRange(dayOfMonth, 1, 31, "dayOfMonth");

        if (dayOfMonth > 29) {
            int maxValidDays = month.length(isLeapYear(year));
            if (!MyUtils.isBetween(dayOfMonth, 1, maxValidDays)) {
                if (!isLeapYear(year) && month == PersianMonth.ESFAND && dayOfMonth == 30) {
                    throw new DateTimeException("Invalid date 'ESFAND 30' as '" + year + "' is not a leap year");
                } else {
                    throw new DateTimeException("Invalid date '" + month.toString() + " " + dayOfMonth + "'");
                }
            }
        }

        // Create Persian date
        persianDate = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));
        persianDate.clear();
        persianDate.set(year, month.getValue() - 1, dayOfMonth);

        // Convert Persian to Gregorian
        java.util.Calendar gregorianDate = GregorianCalendar.getInstance();
        gregorianDate.setTime(persianDate.getTime());

        int gregYear = gregorianDate.get(Calendar.YEAR);
        int gregMonth = gregorianDate.get(Calendar.MONTH) + 1;
        int gregDayOfMonth = gregorianDate.get(Calendar.DAY_OF_MONTH);
        this.gregDate = LocalDate.of(gregYear, gregMonth, gregDayOfMonth);
    }

    /**
     * Returns an equivalent Gregorian date and time as an instance of {@link LocalDate}.
     * Calling this method has no effect on the object that calls this.
     *
     * @return the equivalent Gregorian date as an instance of {@link LocalDate}
     */
    public LocalDate toGregorian() {
        return gregDate;
    }

    /**
     * Returns an equivalent Persian date and time as an instance of {@link PersianDate}.
     * Calling this method has no effect on the class and other instances of this class.
     *
     * @param ldt Gregorian date and time
     * @return an equivalent Persian date and time as an instance of {@link PersianDate}
     */
    public static PersianDate gregorianToPersian(LocalDate ldt) {
        // Create a Gregorian calendar with the argument ldt
        java.util.Calendar gregorianCalendar = java.util.Calendar.getInstance();
        gregorianCalendar.set(ldt.getYear(), ldt.getMonthValue() - 1, ldt.getDayOfMonth());

        // Convert Gregorian calendar to Persian calendar
        ULocale locale = new ULocale("fa_IR@calendar=persian");
        Calendar persianCalendar = Calendar.getInstance(locale);
        persianCalendar.setTime(gregorianCalendar.getTime());

        int persianYear = persianCalendar.get(Calendar.YEAR);
        int persianMonth = persianCalendar.get(Calendar.MONTH) + 1;
        int persianDayOfMonth = persianCalendar.get(Calendar.DAY_OF_MONTH);
        return PersianDate.of(persianYear, persianMonth, persianDayOfMonth);
    }

    /**
     * Returns true if {@code year} is a leap year in Persian calendar.
     *
     * @return true if {@code year} is a leap year in Persian calendar
     */
    public boolean isLeapYear() {
        return PersianDate.isLeapYear(getYear());
    }

    /**
     * Returns true if {@code year} is a leap year in Persian calendar.
     *
     * @return true if {@code year} is a leap year in Persian calendar
     * @throws IllegalArgumentException if argument is a negative value
     */
    public static boolean isLeapYear(int year) {
        MyUtils.integerRequiresRange(year, 1, MAX_PERSIAN_YEAR, "year");
        return (((25 * year) + 11) % 33) < 8;
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this persian date to another persian date.
     * <p>
     * Newer persian date is considered greater.
     *
     * @param other the other persian date to compare to, not null
     * @return the comparator value, negative if less, positive if greater and zero if equals
     */
    @Override
    public int compareTo(PersianDate other) {
        Objects.requireNonNull(other, "object to compare must not be null");
        return this.gregDate.compareTo(other.gregDate);
    }

    /**
     * Checks whether this persian date has the same date with the specified persian date.
     *
     * @param other instance of persian date to compare to
     * @return true, if this persian date and specified persian dates have the same date value.
     */
    public boolean isEqual(PersianDate other){
        return compareTo(other) == 0;
    }

    /**
     * Checks whether this persian date is before the specified persian date.
     *
     * <pre>
     *     PersianDate a = PersianDate.of(1396, 3, 15);
     *     PersianDate b = PersianDate.of(1396, 6, 10);
     *     a.isBefore(b) -> true
     *     a.isBefore(a) -> false
     *     b.isBefore(a) -> false
     * </pre>
     * @param other instance of PersianDate to compare to
     * @return true, if persian date is before the specified persian date
     */
    public boolean isBefore(PersianDate other){
        return compareTo(other) < 0;
    }

    /**
     * Checks whether this persian date is after the specified persian date.
     *
     * <pre>
     *     PersianDate a = PersianDate.of(1396, 3, 15);
     *     PersianDate b = PersianDate.of(1396, 6, 10);
     *     a.isAfter(b) -> false
     *     a.isAfter(a) -> false
     *     b.isAfter(a) -> true
     * </pre>
     * @param other instance of PersianDate to compare to
     * @return true, if persian date is after the specified persian date
     */
    public boolean isAfter(PersianDate other){
        return compareTo(other) > 0;
    }

    //-----------------------------------------------------------------------
    /**
     * Checks if this date is equal to another date.
     * <p>
     * Compares this {@code LocalDate} with another ensuring that the date is the same.
     *
     * @param obj  the object to check, null returns false
     * @return true if this is equal to the other date
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj instanceof PersianDate){
            return compareTo((PersianDate) obj) == 0;
        }
        return false;
    }

    /**
     * A hash code for this persian date.
     *
     * @return a suitable hash code
     */
    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + getYear();
        result = 31 * result + getMonthValue();
        result = 31 * result + getDayOfMonth();
        return result;
    }

    //-----------------------------------------------------------------------
    /**
     * Returns the string representation of this persian date. The string contains of ten
     * characters whose format is "XXXX-YY-ZZ", where XXXX is the year, YY is the
     * month-of-year and ZZ is day-of-month. (Each of the capital characters represents a
     * single decimal digit.)
     * <p>
     * If any of the three parts of this persian date is too small to fill up its field,
     * the field is padded with leading zeros.
     *
     * @return a suitable representation of this persian date
     */
    public String toString(){
        return String.format("%04d-%02d-%02d", getYear(), getMonthValue(), getDayOfMonth());
    }
}
