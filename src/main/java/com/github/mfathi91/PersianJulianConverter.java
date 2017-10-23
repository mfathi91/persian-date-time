package com.github.mfathi91;

import net.jcip.annotations.ThreadSafe;

import java.util.Objects;

/**
 * Static helper methods, for converting instances of {@link PersianDate} to julian days
 * and vice verca.
 *
 * <p>
 * Getting instance of this class is not possible.
 *
 * <p>
 * This class is stateless and thread-safe, so it can be used in concurrent programs.
 */
@ThreadSafe
class PersianJulianConverter {

    /**
     * Constant persian epoch.
     */
    private static final int PERSIAN_EPOCH = 1948320;

    /**
     * Constant persian epoch year is cached, becuase of frequnt usage.
     */
    private static final PersianDate PERSIAN_EPOCH_DATE = PersianDate.of(475, 1, 1);

    /**
     * Ensure non-instantiability.
     */
    private PersianJulianConverter(){
        throw new UnsupportedOperationException();
    }

    /**
     * Converts a persian date to corresponding julian days.
     *
     * @param persianDate instance of {@code PersianDate} to be converted to julian days
     * @return an integer, that is correspondent julian days of persian date
     */
    static int persianDateToJulianDays(PersianDate persianDate) {
        Objects.requireNonNull(persianDate, "persianDate must not be null");

        int epbase = persianDate.getYear() - 474;
        int epyear = 474 + (epbase % 2820);
        return persianDate.getDayOfMonth() +
                persianDate.getMonth().daysToFirstOfMonth() +
                (epyear * 682 - 110) / 2816 +
                (epyear - 1) * 365 +
                (epbase / 2820 * 1029983) +
                (PERSIAN_EPOCH - 1);
    }

    /**
     * Converts julian days to a {@link PersianDate}.
     *
     * @param julianDays julian days
     * @return a {@code PersianDate} that is correspondent to julian days
     */
    static PersianDate julianDaysToPersianDate(int julianDays) {
        MyUtils.intRequirePositive(julianDays, "julianDays");

        int depoch = julianDays - persianDateToJulianDays(PERSIAN_EPOCH_DATE);
        int cycle = depoch / 1029983;
        int cyear = depoch % 1029983;

        int ycycle, aux1, aux2;
        if (cyear == 1029982) {
            ycycle = 2820;
        } else {
            aux1 = cyear / 366;
            aux2 = cyear % 366;
            ycycle = (2134 * aux1 + 2816 * aux2 + 2815) / 1028522 + aux1 + 1;
        }

        int pYear = ycycle + 2820 * cycle + 474;
        int yday = julianDays - persianDateToJulianDays(PersianDate.of(pYear, 1, 1)) + 1;
        int pMonth = (int) Math.ceil((yday <= 186) ? yday / 31.0 : (yday - 6) / 30.0);
        int pDay = (julianDays - persianDateToJulianDays(PersianDate.of(pYear, pMonth, 1)) + 1);
        return PersianDate.of(pYear, pMonth, pDay);
    }

}
