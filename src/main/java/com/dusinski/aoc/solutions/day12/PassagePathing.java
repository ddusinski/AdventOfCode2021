package com.dusinski.aoc.solutions.day12;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.List;

public class PassagePathing {
    private final CavesMap cm;

    public PassagePathing() {
        List<String> inputList = DataLoadUtil.loadStringList("day12.txt");
//        List<String> inputList = DataLoadUtil.loadStringList("test.txt");
        this.cm = new CavesMap(inputList);
    }

    public int getPathsCount() {
        return cm.getPathsCount();
    }

    public int getPathsCountPart2() {
        return cm.getPathsCountPart2();
    }


}
