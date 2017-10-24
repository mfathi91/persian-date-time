package com.github.mfathi91.time;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersianMonthTest {

    @Test
    public void testOnGetPersianName() {
        assertEquals(PersianMonth.ORDIBEHESHT.getPersianName(), "اردیبهشت");
        assertEquals(PersianMonth.ABAN.getPersianName(), "آبان");
    }

    @Test
    public void testOnGetValue() {
        assertEquals(PersianMonth.FARVARDIN.getValue(), 1);
        assertEquals(PersianMonth.ORDIBEHESHT.getValue(), 2);
        assertEquals(PersianMonth.ESFAND.getValue(), 12);
    }

    @Test
    public void testOnLength() {
        assertEquals(PersianMonth.FARVARDIN.length(true), 31);
        assertEquals(PersianMonth.FARVARDIN.length(false), 31);
        assertEquals(PersianMonth.SHAHRIVAR.length(true), 31);
        assertEquals(PersianMonth.SHAHRIVAR.length(false), 31);
        assertEquals(PersianMonth.MEHR.length(true), 30);
        assertEquals(PersianMonth.MEHR.length(false), 30);
        assertEquals(PersianMonth.ESFAND.length(true), 30);
        assertEquals(PersianMonth.ESFAND.length(false), 29);
    }

    @Test
    public void testOnMaxLength() {
        assertEquals(PersianMonth.FARVARDIN.maxLength(), 31);
        assertEquals(PersianMonth.SHAHRIVAR.maxLength(), 31);
        assertEquals(PersianMonth.MEHR.maxLength(), 30);
        assertEquals(PersianMonth.ESFAND.maxLength(), 30);
    }

    @Test
    public void testOnMinLength() {
        assertEquals(PersianMonth.FARVARDIN.minLength(), 31);
        assertEquals(PersianMonth.SHAHRIVAR.minLength(), 31);
        assertEquals(PersianMonth.MEHR.minLength(), 30);
        assertEquals(PersianMonth.ESFAND.minLength(), 29);
    }

    @Test
    public void testOnPlus() {
        assertEquals(PersianMonth.AZAR, PersianMonth.AZAR.plus(0));
        assertEquals(PersianMonth.ORDIBEHESHT, PersianMonth.FARVARDIN.plus(1));
        assertEquals(PersianMonth.ESFAND, PersianMonth.FARVARDIN.plus(11));
        assertEquals(PersianMonth.FARVARDIN, PersianMonth.FARVARDIN.plus(12));
        assertEquals(PersianMonth.ABAN, PersianMonth.SHAHRIVAR.plus(38));
        assertEquals(PersianMonth.ESFAND, PersianMonth.TIR.plus(-4));
        assertEquals(PersianMonth.AZAR, PersianMonth.ESFAND.plus(-27));
    }

    @Test
    public void testOnMinus() {
        assertEquals(PersianMonth.AZAR, PersianMonth.AZAR.minus(0));
        assertEquals(PersianMonth.FARVARDIN, PersianMonth.ORDIBEHESHT.minus(1));
        assertEquals(PersianMonth.FARVARDIN, PersianMonth.ORDIBEHESHT.minus(13));
        assertEquals(PersianMonth.ESFAND, PersianMonth.TIR.minus(4));
        assertEquals(PersianMonth.MEHR, PersianMonth.MORDAD.minus(-26));
    }

    @Test
    public void testOnDaysToFirstOfMonth() {
        assertEquals(0, PersianMonth.FARVARDIN.daysToFirstOfMonth());
        assertEquals(31, PersianMonth.ORDIBEHESHT.daysToFirstOfMonth());
        assertEquals(62, PersianMonth.KHORDAD.daysToFirstOfMonth());
        assertEquals(93, PersianMonth.TIR.daysToFirstOfMonth());
        assertEquals(124, PersianMonth.MORDAD.daysToFirstOfMonth());
        assertEquals(155, PersianMonth.SHAHRIVAR.daysToFirstOfMonth());
        assertEquals(186, PersianMonth.MEHR.daysToFirstOfMonth());
        assertEquals(216, PersianMonth.ABAN.daysToFirstOfMonth());
        assertEquals(246, PersianMonth.AZAR.daysToFirstOfMonth());
        assertEquals(276, PersianMonth.DEY.daysToFirstOfMonth());
        assertEquals(306, PersianMonth.BAHMAN.daysToFirstOfMonth());
        assertEquals(336, PersianMonth.ESFAND.daysToFirstOfMonth());
    }
}