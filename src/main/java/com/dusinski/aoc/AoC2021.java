package com.dusinski.aoc;

import com.dusinski.aoc.solutions.day7.WhalesTreachery;

public class AoC2021 {
    public static void main(String[] args) {
        System.out.println("Hello AoC_2021!");

        WhalesTreachery wt = new WhalesTreachery();
        System.out.println("Min req fuel " + wt.getLeastFuelPossiblePart2());
    }
}
