package com.github.mfathi91;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyUtilsTest {

    @Test
    public void testOnIsBetween() {
        assertEquals(true, MyUtils.isBetween(2, 1, 3));
        assertEquals(true, MyUtils.isBetween(0, -1, 1));
        assertEquals(true, MyUtils.isBetween(-10, -10000, -10));
        assertEquals(false, MyUtils.isBetween(0, -10, -1));
        assertEquals(false, MyUtils.isBetween(-100, 99, 101));
        assertEquals(false, MyUtils.isBetween(-100, -99, -101));
    }

    @Test
    public void testOnIntegerRequiresRange() {
        assertEquals(5, MyUtils.intRequireRange(5, -100, 100, "sth"));
        assertEquals(-100, MyUtils.intRequireRange(-100, -101, -99, "sth"));
    }

}