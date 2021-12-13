package com.dusinski.aoc;

import com.dusinski.aoc.solutions.day13.TransparentOrigami;

public class AoC2021 {
    public static void main(String[] args) {
        System.out.println("Hello AoC_2021!");

//        PassagePathing pp = new PassagePathing();
//        System.out.println("getPathsCount(): " + pp.getPathsCount());
//        System.out.println("getPathsCount(): " + pp.getPathsCountPart2()); -- not finished

        TransparentOrigami to = new TransparentOrigami();
//        System.out.println("getDotsCountAfterOneFold(): " + to.getDotsCountAfterOneFold());
        System.out.println("getDotsCountAfterOneFold(): \n" + to.getDotsCountAfterAllFolds());


    }
}
