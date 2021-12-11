package com.dusinski.aoc;

import com.dusinski.aoc.solutions.day11.DumboOctopus;

public class AoC2021 {
    public static void main(String[] args) {
        System.out.println("Hello AoC_2021!");

        DumboOctopus dumboOctopus = new DumboOctopus();
//        System.out.println("getFlashesAfter100Steps(): " + dumboOctopus.getFlashesAfter100Steps());
        System.out.println("getFlashesAfter100Steps(): " + dumboOctopus.getStepWhenAllFlash());
    }
}
