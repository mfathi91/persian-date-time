package com.mahmoud;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class PersianJulianConverterTest {

    @Test
    public void testOnPersianDateToJulianDays() {
        PersianDate persianDate1 = PersianDate.of(1979, 3, 8);
        assertEquals(2670838, PersianJulianConverter.persianDateToJulianDays(persianDate1));
        PersianDate persianDate2 = PersianDate.of(1407, 5, 18);
        assertEquals(2461991, PersianJulianConverter.persianDateToJulianDays(persianDate2));
    }

    @Test
    public void testOnJulianDaysToPersianDate() {
        assertReflectionEquals(PersianDate.of(1394, 12, 10), PersianJulianConverter.julianDaysToPersianDate(2457447));
        assertReflectionEquals(PersianDate.of(1336, 8, 3), PersianJulianConverter.julianDaysToPersianDate(2436136));
    }

}