package com.exebar.poc.java.threads;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonthTests {

    @Test
    void test() {
        Month august = Month.AUGUST;
        Month february = Month.FEBRUARY;
        assertEquals(8, august.getValue());

        assertEquals(31, august.length(true));
        assertEquals(31, august.length(false));

        assertEquals(Month.JULY, august.firstMonthOfQuarter());
        assertEquals(Month.JANUARY, february.firstMonthOfQuarter());

        assertEquals(28, february.length(false));
        assertEquals(29, february.length(true));

        assertEquals(32, february.firstDayOfYear(true));
        assertEquals(32, february.firstDayOfYear(false));

        // days in jan thru aug in a leap year:
        // 31 + 29 + 31 + 30 + 31 + 30 + 31 = 7*30 + 3 = 213
        assertEquals(214, august.firstDayOfYear(true));
        assertEquals(31, august.maxLength());
        assertEquals(31, august.minLength());
        assertEquals("AUGUST", august.name());
        assertEquals("August", august.getDisplayName(TextStyle.FULL, Locale.getDefault()));
        assertEquals("Aug", august.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        assertEquals("A", august.getDisplayName(TextStyle.NARROW, Locale.getDefault()));

        assertEquals("августа", august.getDisplayName(TextStyle.FULL, new Locale("ru")));
        assertEquals("август", august.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
    }
}
