package com.example.time;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter time in HH:mm format:");
        String input = scanner.nextLine();
        try {
            System.out.println("British spoken time: " + BritishSpokenTime.speak(TimeParser.parse(input)));
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}