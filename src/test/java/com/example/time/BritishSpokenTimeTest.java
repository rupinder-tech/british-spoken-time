package com.example.time;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BritishSpokenTimeTest {

    @Test
    void speaksMidnightAndNoon() {
        assertEquals("midnight",
                BritishSpokenTime.speak(LocalTime.of(0, 0)));

        assertEquals("noon",
                BritishSpokenTime.speak(LocalTime.of(12, 0)));
    }

    @Test
    void speaksExactHours() {
        assertEquals("one o'clock",
                BritishSpokenTime.speak(LocalTime.of(1, 0)));

        assertEquals("eleven o'clock",
                BritishSpokenTime.speak(LocalTime.of(11, 0)));
    }

    @Test
    void speaksPastTimes() {
        assertEquals("five past two",
                BritishSpokenTime.speak(LocalTime.of(2, 5)));

        assertEquals("quarter past four",
                BritishSpokenTime.speak(LocalTime.of(4, 15)));

        assertEquals("twenty-five past six",
                BritishSpokenTime.speak(LocalTime.of(6, 25)));
    }

    @Test
    void speaksHalfPast() {
        assertEquals("half past seven",
                BritishSpokenTime.speak(LocalTime.of(7, 30)));
    }

    @Test
    void speaksToTimes() {
        assertEquals("twenty-five to eight",
                BritishSpokenTime.speak(LocalTime.of(7, 35)));

        assertEquals("twenty to nine",
                BritishSpokenTime.speak(LocalTime.of(8, 40)));

        assertEquals("quarter to ten",
                BritishSpokenTime.speak(LocalTime.of(9, 45)));

        assertEquals("ten to eleven",
                BritishSpokenTime.speak(LocalTime.of(10, 50)));

        assertEquals("five to twelve",
                BritishSpokenTime.speak(LocalTime.of(11, 55)));
                
        assertEquals("five to twelve",
                BritishSpokenTime.speak(LocalTime.of(23, 55)));
    }

    @Test
    void speaksNonFiveMinuteTimesNumerically(){
        assertEquals("six thirty-two", BritishSpokenTime.speak(LocalTime.of(6,32)));
    }
}