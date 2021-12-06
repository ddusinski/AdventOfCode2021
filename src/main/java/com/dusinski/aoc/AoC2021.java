package com.dusinski.aoc;

import com.dusinski.aoc.solutions.day6.Lanternfish;

public class AoC2021 {
    public static void main(String[] args) {
        System.out.println("Hello AoC_2021!");

        Lanternfish lf = new Lanternfish();
        System.out.println("After 80 days: " + lf.getIterative(80));
    }
}
