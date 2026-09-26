package com.example.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class TimeParser {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("H:mm");

    private TimeParser() {
    }

    public static LocalTime parse(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Time must not be blank");
        }

        try {
            return LocalTime.parse(input.trim(), FORMATTER);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException(
                    "Invalid time. Expected HH:mm, for example 12:05",
                    exception);
        }
    }
}