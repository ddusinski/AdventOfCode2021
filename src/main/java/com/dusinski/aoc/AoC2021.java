package com.dusinski.aoc;

import com.dusinski.aoc.solutions.day15.Chiton;

public class AoC2021 {
    public static void main(String[] args) {
        System.out.println("Hello AoC_2021!");

//        PassagePathing pp = new PassagePathing();
//        System.out.println("getPathsCount(): " + pp.getPathsCount());
//        System.out.println("getPathsCount(): " + pp.getPathsCountPart2()); -- not finished

//        ExtendedPolymerization ep = new ExtendedPolymerization();
//        System.out.println("getMostMinusLeastPart2(): " + ep.getMostMinusLeastPart1());
//        System.out.println("getMostMinusLeastPart2(): " + ep.getMostMinusLeastPart2()); -- not finished


        Chiton chiton = new Chiton();
        System.out.println("chiton.getLowestTotalRiskPart1(): " + chiton.genGraphPart1());
//        System.out.println("chiton.getLowestTotalRiskPart2(): " + chiton2.genGraph2());

//        PacketDecoder pd = new PacketDecoder();






    }
}
