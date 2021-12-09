package com.dusinski.aoc;

import com.dusinski.aoc.solutions.day7.WhalesTreachery;
import com.dusinski.aoc.solutions.day8.SevenSegmentSearch;
import com.dusinski.aoc.solutions.day9.SmokeBasin;
import com.dusinski.aoc.util.DataLoadUtil;

import java.util.List;

public class AoC2021 {
    public static void main(String[] args) {
        System.out.println("Hello AoC_2021!");

//        WhalesTreachery wt = new WhalesTreachery();
//        System.out.println("Min req fuel " + wt.getLeastFuelPossiblePart2());

//        SevenSegmentSearch sss = new SevenSegmentSearch();
//                System.out.println("Easy dict count: " + sss.getEasyDigitsPart1());
//                System.out.println("Easy dict count: " + sss.getEasyDigitsPart2());
//                System.out.println("Easy dict count: " + sss.getEasyDigitsPart2SET());

        SmokeBasin sb = new SmokeBasin();
//        System.out.println("getRiskLevelSum: " + sb.getRiskLevelSum());
        System.out.println("getRiskLevelSum: " + sb.getSize3LargestBasins());

    }
}
