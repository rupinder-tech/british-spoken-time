package com.example.time;

import java.time.LocalTime;

public final class BritishSpokenTime {

    private static final String[] NUMBERS = {
            "zero", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen", "twenty", "twenty-one",
            "twenty-two", "twenty-three", "twenty-four", "twenty-five",
            "twenty-six", "twenty-seven", "twenty-eight", "twenty-nine"
    };

    private BritishSpokenTime() {
    }

    public static String speak(LocalTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();

        if (hour == 0 && minute == 0) {
            return "midnight";
        }

        if (hour == 12 && minute == 0) {
            return "noon";
        }

        if (minute == 0) {
            return NUMBERS[hour == 0 ? 12 : hour] + " o'clock";
        }

        if(minute % 5 != 0){
            return hourName(hour) + " " + minuteName(minute);
        } 

        if (minute <= 29) {
            return speakPast(hour, minute);
        }

        if (minute == 30) {
            return "half past " + hourName(hour);
        }

        return speakTo(hour, minute);
    }

    private static String speakPast(int hour, int minute) {
        String minuteName = NUMBERS[minute];

        if (minute == 15) {
            minuteName = "quarter";
        }

        return minuteName + " past " + hourName(hour);
    }

    private static String speakTo(int hour, int minute) {
        int nextHour = (hour + 1) % 24;
        int minutesTo = 60 - minute;

        String minuteName = NUMBERS[minutesTo];

        if (minutesTo == 15) {
            minuteName = "quarter";
        }

        return minuteName + " to " + hourName(nextHour);
    }

    private static String hourName(int hour) {
        int spokenHour = hour == 0 ? 12 : hour > 12 ? hour - 12 : hour;
        return NUMBERS[spokenHour];
    }

    private static String minuteName(int minute){
        if(minute < 30){
            return NUMBERS[minute];
        }

        int tens = minute / 10;
        int ones = minute % 10;

        String[] tensName = {"","","","thirty","forty","fifty"};

        return tensName[tens] + "-" + NUMBERS[ones];

    }
}