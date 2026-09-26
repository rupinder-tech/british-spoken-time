package com.example.time;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeParserTest {

    @Test
    void parsesValidTime() {
        assertEquals(LocalTime.of(12, 5), TimeParser.parse("12:05"));
    }

    @Test
    void acceptsSurroundingWhitespace() {
        assertEquals(LocalTime.of(7, 30), TimeParser.parse(" 07:30 "));
    }

    @Test
    void rejectsInvalidTime() {
        assertThrows(IllegalArgumentException.class,
                () -> TimeParser.parse("25:00"));

        assertThrows(IllegalArgumentException.class,
                () -> TimeParser.parse("not-a-time"));

        assertThrows(IllegalArgumentException.class,
                () -> TimeParser.parse(""));
    }

    @Test
    void parsesLastMinuteOfDay() {
        assertEquals(LocalTime.of(23, 59), TimeParser.parse("23:59"));
    }
}