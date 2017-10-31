package com.github.mfathi91.time;

import net.jcip.annotations.Immutable;

/**
 * A month-of-year, such as Mehr.
 * <p>
 * {@code PersianMonth} is an enum representing the 12 months of the year
 * Farvardin, Ordibehesht, Khordad, Tir, Mordad, Shahrivar, Mehr, Aban, Azar, Dey,
 * Bahman and Esfand.
 * <p>
 * In addition to the textual enum name, each month-of-year has an {@code int} value.
 * The {@code int} value follows normal usage and the ISO-8601 standard,
 * from 1 (January) to 12 (December). It is recommended that applications use the enum
 * rather than the {@code int} value to ensure code clarity.
 * <p>
 * <b>Do not use {@code ordinal()} to obtain the numeric representation of {@code Month}.
 * Use {@code getValue()} instead.</b>
 * <p>
 * This is an immutable enum and can be used in concurrent programs.
 *
 * @author Mahmoud Fathi
 */
@Immutable
public enum PersianMonth {

    /**
     * The singleton instance for the month of Farvardin with 31 days.
     * This has the numeric value of {@code 1}.
     */
    FARVARDIN("فروردین"),

    /**
     * The singleton instance for the month of Ordibehesht with 31 days.
     * This has the numeric value of {@code 2}.
     */
    ORDIBEHESHT("اردیبهشت"),

    /**
     * The singleton instance for the month of Khordad with 31 days.
     * This has the numeric value of {@code 3}.
     */
    KHORDAD("خرداد"),

    /**
     * The singleton instance for the month of Tir with 31 days.
     * This has the numeric value of {@code 4}.
     */
    TIR("تیر"),

    /**
     * The singleton instance for the month of Mordad with 31 days.
     * This has the numeric value of {@code 5}.
     */
    MORDAD("مرداد"),

    /**
     * The singleton instance for the month of Shahrivar with 31 days.
     * This has the numeric value of {@code 6}.
     */
    SHAHRIVAR("شهریور"),

    /**
     * The singleton instance for the month of Mehr with 30 days.
     * This has the numeric value of {@code 7}.
     */
    MEHR("مهر"),

    /**
     * The singleton instance for the month of Aban with 30 days.
     * This has the numeric value of {@code 8}.
     */
    ABAN("آبان"),

    /**
     * The singleton instance for the month of Azar with 30 days.
     * This has the numeric value of {@code 9}.
     */
    AZAR("آذر"),

    /**
     * The singleton instance for the month of Dey with 30 days.
     * This has the numeric value of {@code 10}.
     */
    DEY("دی"),

    /**
     * The singleton instance for the month of Bahman with 30 days.
     * This has the numeric value of {@code 11}.
     */
    BAHMAN("بهمن"),

    /**
     * The singleton instance for the month of Farvardin with 29 days in non-leap year
     * and 30 days in leap year. This has the numeric value of {@code 12}.
     */
    ESFAND("اسفند");

    private final String persianName;

    PersianMonth(String persianName) {
        this.persianName = persianName;
    }

    /**
     * Returns name of month in persian alphabe.
     *
     * @return persian name of month.
     */
    public String getPersianName() {
        return persianName;
    }

    /**
     * Returns the equivalent instance of {@code Month}, based on the passed argument.
     * Argument should be from 1 to 12.
     *
     * @param month the number of month
     * @return instance of {@code Month} enum.
     */
    static PersianMonth of(int month) {
        MyUtils.intRequireRange(month, 1, 12, "month");
        return PersianMonth.values()[month - 1];
    }

    /**
     * @return number of month, from 1 (Farvardin) to 12 (Esfand)
     */
    public int getValue() {
        return ordinal() + 1;
    }

    /**
     * Returns length of month. For the first six months of year, {@code 31} is returned.
     * For the second six months of year except Esfand, {@code 30} is returned.
     * Finlally For Esfand, if {@code leapYear} is true, it returns {@code 30}, otherwise
     * it returns {@code 29}.
     *
     * @param leapYear true, if length of months in leap year is required
     * @return length of months of Persian calendar
     */
    public int length(boolean leapYear) {
        int value = getValue();
        return value < 7 ? 31 : (value != 12 ? 30 : (leapYear ? 30 : 29));
    }

    /**
     * Returns length of month in a leap year. For the first six months of year,
     * {@code 31} is returned. For the second six months of year {@code 30} is returned.
     *
     * @return length of months of Persian calendar
     */
    public int maxLength() {
        return length(true);
    }

    /**
     * Returns length of month in a non-leap year. For the first six months of year,
     * {@code 31} is returned. For the second six months of year except Esfand, {@code 30}
     * is returned. For Esfand {@code 29} is returned.
     *
     * @return length of months of Persian calendar
     */
    public int minLength() {
        return length(false);
    }

    /**
     * Returns a month-of-year that is {@code months} after current month. The calculation
     * rolls around end of year from Esfand to Farvardin.
     * <p>
     * This enum is immutable and unaffected by calling this method.
     *
     * @param months the months to add, positive or negative
     * @return the resulting month, not null
     */
    public PersianMonth plus(long months) {
        int amount = (int) (months % 12);
        // For negative argument
        amount = (amount + 12) % 12;
        return PersianMonth.values()[(ordinal() + amount) % 12];
    }

    /**
     * Returns a month-of-year that is {@code months} before current month. The calculation
     * rolls around the start of year from Farvardin to Esfand.
     * <p>
     * This enum is immutable and unaffected by calling this method.
     *
     * @param months the months to subtract, positive or negative
     * @return the resulting month, not null
     */
    public PersianMonth minus(long months) {
        return plus(-months);
    }

    /**
     * Returns elapsed days from first of the year to first of this month.
     *
     * @return elapsed days from first of the year to first of this month.
     */
    public int daysToFirstOfMonth() {
        int val = getValue();
        return (val <= 6) ? (31 * (val - 1)) : ((30 * (val - 1 - 6)) + 186);
    }
}
