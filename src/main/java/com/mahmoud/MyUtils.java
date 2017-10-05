package com.mahmoud;

import net.jcip.annotations.ThreadSafe;

/**
 * This class provides static helper methods, such as {@cod isBetween(...)}.
 * It is not possible to get an instance of this class.
 * <p>
 * <p>
 * This class is stateless and thread-safe.
 */
@ThreadSafe
class MyUtils {

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
    static int integerRequiresRange(int val, int lowerLimit, int upperLimit, String valName) {
        if (!isBetween(val, lowerLimit, upperLimit)) {
            throw new IllegalArgumentException(valName + " is out of range [" + lowerLimit + ", " + upperLimit + "]");
        }
        return val;
    }
}
