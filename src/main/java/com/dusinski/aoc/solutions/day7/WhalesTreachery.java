package com.dusinski.aoc.solutions.day7;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.Collections;
import java.util.List;


public class WhalesTreachery {
    List<Integer> crabHorLev;
    CalcReqFuel part1 = (a, b) -> Math.abs(a - b);
    CalcReqFuel part2 = (a, b) -> {
        int n = Math.abs(a - b);
        return (n + 1) * n / 2;
    };

    public WhalesTreachery() {
        crabHorLev = DataLoadUtil.loadDelimitedStringToList("day7.txt");
    }

    public int getLeastFuelPossiblePart1() {
        return calcFuelConsumption(part1);
    }

    public int getLeastFuelPossiblePart2() {
        return calcFuelConsumption(part2);
    }

    public int calcFuelConsumption(CalcReqFuel calcFunction) {
        int minElement = Collections.min(this.crabHorLev);
        int maxElement = Collections.max(this.crabHorLev);
        int minReqLevel = Integer.MAX_VALUE;

        for (int i = minElement; i <= maxElement; i++) {
            int sumOfFuel = 0;
            for (int level : this.crabHorLev) {
                sumOfFuel += calcFunction.calcFuel(level, i);
            }
            if (sumOfFuel < minReqLevel) {
                minReqLevel = sumOfFuel;
            }
        }
        return minReqLevel;
    }

    private interface CalcReqFuel {
        int calcFuel(int startLevel, int reqLevel);
    }
}
