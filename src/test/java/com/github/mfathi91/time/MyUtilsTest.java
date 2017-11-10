package com.github.mfathi91.time;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyUtilsTest {

    @Test
    public void testOnIsBetweenInt() {
        assertEquals(true, MyUtils.isBetween(2, 1, 3));
        assertEquals(true, MyUtils.isBetween(0, -1, 1));
        assertEquals(true, MyUtils.isBetween(-10, -10000, -10));
        assertEquals(false, MyUtils.isBetween(0, -10, -1));
        assertEquals(false, MyUtils.isBetween(-100, 99, 101));
        assertEquals(false, MyUtils.isBetween(-100, -99, -101));
    }

    @Test
    public void testOnIsBetweenLong() {
        assertEquals(true, MyUtils.isBetween(2L, 1L, 3L));
        assertEquals(true, MyUtils.isBetween(0L, -1L, 1L));
        assertEquals(true, MyUtils.isBetween(-10L, -10000L, -10L));
        assertEquals(false, MyUtils.isBetween(0L, -10L, -1L));
        assertEquals(false, MyUtils.isBetween(-100L, 99L, 101L));
        assertEquals(false, MyUtils.isBetween(-100L, -99L, -101L));
    }

    @Test
    public void testOnIntegerRequiresRange() {
        assertEquals(5, MyUtils.intRequireRange(5, -100, 100, "sth"));
        assertEquals(-100, MyUtils.intRequireRange(-100, -101, -99, "sth"));
    }

    @Test
    public void testOnIntRequireRange() {
        MyUtils.intRequireRange(Integer.MAX_VALUE - 1,
                Integer.MAX_VALUE - 2, Integer.MAX_VALUE, "exception");
        MyUtils.intRequireRange(Integer.MIN_VALUE +1,
                Integer.MIN_VALUE, Integer.MIN_VALUE + 2, "exception");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOnIntRequireRangeException() {
        MyUtils.intRequireRange(Integer.MIN_VALUE +1, Integer.MIN_VALUE + 2,
                Integer.MIN_VALUE, "exception");
    }

    @Test
    public void testOnIntRequirePositive() {
        MyUtils.intRequirePositive(1, "one");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOnIntRequirePositiveException() {
        MyUtils.intRequirePositive(0, "zero");
    }

    @Test
    public void testOnLongRequireRange() {
        MyUtils.longRequireRange(Long.MAX_VALUE - 1,
                Long.MAX_VALUE - 2, Long.MAX_VALUE, "exception");
        MyUtils.longRequireRange(Long.MIN_VALUE +1,
                Long.MIN_VALUE, Long.MIN_VALUE + 2, "exception");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOnLongRequireRangeException() {
        MyUtils.longRequireRange(Long.MIN_VALUE +1, Long.MIN_VALUE + 2,
                Long.MIN_VALUE, "exception");
    }

    @Test
    public void testOnLongRequirePositive() {
        MyUtils.longRequirePositive(1L, "one");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOnLongRequirePositiveException() {
        MyUtils.longRequirePositive(0L, "zero");
    }

}