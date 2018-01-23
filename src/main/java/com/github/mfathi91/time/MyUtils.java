package com.github.mfathi91.time;

import net.jcip.annotations.ThreadSafe;

/**
 * This class provides static helper methods, in order to remove boilerplate code.
 * It is not possible to get an instance of this class.
 * <p>
 * This class is stateless and thread-safe.
 *
 * @author Mahmoud Fathi
 */
@ThreadSafe
public class MyUtils {

    // Ensure non-instantiability
    private MyUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns true if and only if {@code val} is greater than or equal to {@code lowerLimit}
     * and is less than or equal to {@code upperLimit}.
     *
     * @param val        the value to be checked, an integer
     * @param lowerLimit lower boundary to be checked, an integer
     * @param upperLimit upper boundary to be checked, an integer
     * @return true if and only if {@code val} is between {@code lowerLimit} and
     * {@code upperLimit}
     */
    static boolean isBetween(int val, int lowerLimit, int upperLimit) {
        return val >= lowerLimit && val <= upperLimit;
    }

    /**
     * Returns true if and only if {@code val} is greater than or equal to {@code lowerLimit}
     * and is less than or equal to {@code upperLimit}.
     *
     * @param val        the value to be checked, a long
     * @param lowerLimit lower boundary to be checked, a long
     * @param upperLimit upper boundary to be checked, a long
     * @return true if and only if {@code val} is between {@code lowerLimit} and
     * {@code upperLimit}
     */
    static boolean isBetween(long val, long lowerLimit, long upperLimit) {
        return val >= lowerLimit && val <= upperLimit;
    }

    /**
     * Checks whether an integer is in a range or not. If {@code val} is less than
     * {@code lowerLimit} or greater than {@code upperLimit}, an IllegalArgumentException
     * will be thrown with a suitable message.
     *
     * @param val        value to check
     * @param lowerLimit lower limit of range
     * @param upperLimit upper limit of range
     * @param valName    the name of {@code val} that is printed in the exception message
     * @return {@code val}, if it is in the range
     */
    static int intRequireRange(int val, int lowerLimit, int upperLimit, String valName) {
        if (!isBetween(val, lowerLimit, upperLimit)) {
            throw new IllegalArgumentException(valName + " " + val +
                    " is out of valid range [" + lowerLimit + ", " + upperLimit + "]");
        }
        return val;
    }

    /**
     * Checks whether an integer is greater than zero or not. If {@code val} is less than
     * or equal to zero, an IllegalArgumentException will be thrown with a suitable message.
     *
     * @param val     integer value to check
     * @param valName name of {@code val} that is printed in the exception message
     * @return {@code val}, if it is positive
     */
    static int intRequirePositive(int val, String valName) {
        if (val <= 0) {
            throw new IllegalArgumentException(valName + " is not positive: " + val);
        }
        return val;
    }

    /**
     * Checks whether a long is greater than zero or not. If {@code val} is less than
     * or equal to zero, an IllegalArgumentException will be thrown with a suitable message.
     *
     * @param val     long value to check
     * @param valName name of {@code val} that is printed in the exception message
     * @return {@code val}, if it is positive
     */
    static long longRequirePositive(long val, String valName) {
        if (val <= 0) {
            throw new IllegalArgumentException(valName + " is not positive: " + val);
        }
        return val;
    }

    /**
     * Checks whether a long is in a range or not. If {@code val} is less than
     * {@code lowerLimit} or greater than {@code upperLimit}, an IllegalArgumentException
     * will be thrown with a suitable message.
     *
     * @param val        value to check
     * @param lowerLimit lower limit of range
     * @param upperLimit upper limit of range
     * @param valName    the name of {@code val} that is printed in the exception message
     * @return {@code val}, if it is in the range
     */
    static long longRequireRange(long val, long lowerLimit, long upperLimit, String valName) {
        if (val < lowerLimit || val > upperLimit){
                throw new IllegalArgumentException(valName + " " + val +
                        " is out of valid range [" + lowerLimit + ", " + upperLimit + "]");
            }
        return val;
    }
}
