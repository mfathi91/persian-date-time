package com.github.mfathi91.time;

import net.jcip.annotations.Immutable;

import java.time.DateTimeException;
import java.time.chrono.Era;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.time.temporal.ValueRange;

import static java.time.temporal.ChronoField.ERA;

/**
 * An era in the Persian calendar system.
 * <p>
 * The Persian calendar system has only one era covering the
 * proleptic years greater than zero.
 * <p>
 * <b>Do not use {@code ordinal()} to obtain the numeric representation of {@code PersianEra}.
 * Use {@code getValue()} instead.</b>
 *
 * <p>
 * This is an immutable and thread-safe enum.
 *
 * @author Mahmoud Fathi
 */
@Immutable
public enum PersianEra implements Era {

    /**
     * The singleton instance for the current era, 'Anno Hegirae Solar',
     * which has the numeric value 1.
     */
    AHS;

    //-----------------------------------------------------------------------
    /**
     * Obtains an instance of {@code PersianEra} from an {@code int} value.
     * <p>
     * The current era, which is the only accepted value, has the value 1
     *
     * @param persianEra  the era to represent, only 1 supported
     * @return the PersianEra. AHS singleton, not null
     * @throws DateTimeException if the value is invalid
     */
    public static PersianEra of(int persianEra) {
        if (persianEra == 1 ) {
            return AHS;
        } else {
            throw new DateTimeException("Invalid era: " + persianEra);
        }
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the numeric era {@code int} value.
     * <p>
     * The era AH has the value 1.
     *
     * @return the era value, 1 (AH)
     */
    @Override
    public int getValue() {
        return 1;
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the range of valid values for the specified field.
     * <p>
     * The range object expresses the minimum and maximum valid values for a field.
     * This era is used to enhance the accuracy of the returned range.
     * If it is not possible to return the range, because the field is not supported
     * or for some other reason, an exception is thrown.
     * <p>
     * If the field is a {@link ChronoField} then the query is implemented here.
     * The {@code ERA} field returns the range.
     * All other {@code ChronoField} instances will throw an {@code UnsupportedTemporalTypeException}.
     * <p>
     * If the field is not a {@code ChronoField}, then the result of this method
     * is obtained by invoking {@code TemporalField.rangeRefinedBy(TemporalAccessor)}
     * passing {@code this} as the argument.
     * Whether the range can be obtained is determined by the field.
     * <p>
     * The {@code ERA} field returns a range for the one valid Persian era.
     *
     * @param field  the field to query the range for, not null
     * @return the range of valid values for the field, not null
     * @throws DateTimeException if the range for the field cannot be obtained
     * @throws UnsupportedTemporalTypeException if the unit is not supported
     */
    @Override  // override as super would return range from 0 to 1
    public ValueRange range(TemporalField field) {
        if (field == ERA) {
            return ValueRange.of(1, 1);
        }
        return Era.super.range(field);
    }

}
