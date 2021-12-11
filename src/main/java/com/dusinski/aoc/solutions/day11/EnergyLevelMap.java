package com.dusinski.aoc.solutions.day11;

import java.util.List;

public class EnergyLevelMap {
    private final int[][] energyMap;
    public int size;

    public EnergyLevelMap(List<String> input) {
        this.energyMap = new int[input.size()][input.get(0).length()];
        this.size =input.size()*input.get(0).length();
        for (int i = 0; i < this.energyMap.length; i++) {
            for (int j = 0; j < this.energyMap[i].length; j++) {
                this.energyMap[i][j] = Integer.parseInt(String.valueOf(input.get(i).charAt(j)));
            }
        }
    }

    public int calcOneStep() {
        int flashCount = 0;
        addOneStep();// here I'm increasing each for one
        for (int i = 0; i < this.energyMap.length; i++) {
            for (int j = 0; j < this.energyMap[0].length; j++) {
                if (this.energyMap[i][j] == 10) {
                    flashCount += fireOctopus(i, j);
                }
            }
        }
        return flashCount;
    }

    private void addOneStep() {
        for (int i = 0; i < this.energyMap.length; i++) {
            for (int j = 0; j < this.energyMap[0].length; j++) {
                this.energyMap[i][j]++;
            }
        }
    }

    private int fireOctopus(int row, int column) {
        if (row < 0 || row == this.energyMap.length || column < 0 || column == this.energyMap[row].length) {
            return 0; // out of the map
        }
        if (this.energyMap[row][column] == 0) {  // already flashed
            return 0;
        }

        if (this.energyMap[row][column] < 9) {     // check if flashed already
            this.energyMap[row][column]++;
            return 0;
        }

        if (this.energyMap[row][column] == 9 || this.energyMap[row][column] == 10) {     // flashes
            this.energyMap[row][column] = 0;
            return 1
                    + fireOctopus(row - 1, column)
                    + fireOctopus(row + 1, column)
                    + fireOctopus(row - 1, column - 1)
                    + fireOctopus(row - 1, column + 1)
                    + fireOctopus(row + 1, column - 1)
                    + fireOctopus(row + 1, column + 1)
                    + fireOctopus(row, column + 1)
                    + fireOctopus(row, column - 1)
                    ;
        }
        return 0; // low then 9
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.energyMap.length; i++) {
            for (int j = 0; j < this.energyMap[0].length; j++) {
                sb.append(this.energyMap[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}