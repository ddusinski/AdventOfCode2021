package com.dusinski.aoc.solutions.test;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.List;

public class test {

    public static Long rocketEquationPart1() {
        List<Long> numbers = DataLoadUtil.loadLongList("day1_test.txt");
        return numbers.stream().reduce(0L, (x, y) -> x + Math.floorDiv(y, 3) - 2);
    }

    public static Long rocketEquationPart2() {
        List<Long> numbers = DataLoadUtil.loadLongList("day1_test.txt");
        return numbers.stream().reduce(0L, (x, y) -> x + findFuelSeries(y));
    }

    private static long findFuelSeries(long fuel) {
        long possibleFuel = Math.floorDiv(fuel, 3) - 2;
        if (possibleFuel < 0) {
            return 0;
        } else
            return possibleFuel + findFuelSeries(possibleFuel);
    }

}
