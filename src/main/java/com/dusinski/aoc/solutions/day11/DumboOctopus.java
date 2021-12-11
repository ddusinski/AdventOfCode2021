package com.dusinski.aoc.solutions.day11;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.List;

public class DumboOctopus {
    private EnergyLevelMap elm = null;

    private void loadData() {
        List<String> inputList = DataLoadUtil.loadStringList("day11.txt");
        this.elm = new EnergyLevelMap(inputList);
    }

    public int getFlashesAfter100Steps() {
        loadData();
        int result = 0;
        for (int i = 0; i < 99; i++) {
            result += this.elm.calcOneStep();
        }
        return result;
    }

    public int getStepWhenAllFlash() {
        loadData();
        int result = this.elm.calcOneStep();
        int counter = 1;
        while (result != this.elm.size) {
            result = this.elm.calcOneStep();
            counter++;
        }
        return counter;
    }


}
