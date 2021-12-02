package com.dusinski.aoc.solutions.day2;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.List;

public class Dive {

    public static long getFinalPosMulti() {
        List<String> instrList = DataLoadUtil.loadStringList("day2.txt");
        long depth = 0;
        long horPosition = 0;
        for (String instr : instrList) {
            String instrType = instr.split(" ")[0];
            int instrVal = Integer.parseInt(instr.split(" ")[1]);
            switch (instrType) {
                case "forward":
                    horPosition += instrVal;
                    break;
                case "up":
                    depth -= instrVal;
                    break;
                case "down":
                    depth += instrVal;
                    break;
            }
        }
        return depth * horPosition;
    }

    public static long getFinalPosMultiWithAim() {
        List<String> instrList = DataLoadUtil.loadStringList("day2.txt");
        long depth = 0;
        long horPosition = 0;
        long aim = 0;
        for (String instr : instrList) {
            String instrType = instr.split(" ")[0];
            int instrVal = Integer.parseInt(instr.split(" ")[1]);
            switch (instrType) {
                case "forward":
                    horPosition -= instrVal;
                    depth -= aim * instrVal;
                    break;
                case "up":
                    aim -= instrVal;
                    break;
                case "down":
                    aim += instrVal;
                    break;
            }
        }
        return depth * horPosition;
    }

}
