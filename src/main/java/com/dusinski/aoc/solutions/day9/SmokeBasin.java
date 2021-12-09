package com.dusinski.aoc.solutions.day9;

import com.dusinski.aoc.util.DataLoadUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmokeBasin {
    private final CaveHighMap caveHighMap;


    public SmokeBasin() {
        List<String> inputList = DataLoadUtil.loadStringList("day9.txt");
//        List<String> inputList = DataLoadUtil.loadStringList("test.txt");
        this.caveHighMap = new CaveHighMap(inputList);
    }

    public int getRiskLevelSum() {
        return this.caveHighMap.getRiskLevelSum();
    }

    public int getSize3LargestBasins() {
        this.caveHighMap.getRiskLevelSum();
        return this.caveHighMap.getLargestBasinMulti();
    }


    private static class CaveHighMap {
        private final int[][] highMap;
        private final List<Point> pointList = new ArrayList<>();


        public CaveHighMap(List<String> input) {
            this.highMap = new int[input.size()][input.get(0).length()];
            for (int i = 0; i < this.highMap.length; i++) {
                for (int j = 0; j < this.highMap[i].length; j++) {
                    this.highMap[i][j] = Integer.parseInt(String.valueOf(input.get(i).charAt(j)));
                }
            }
        }

        public int getLargestBasinMulti() {
            List<Integer> basinSumList = new ArrayList<>();
            this.pointList.forEach(p -> basinSumList.add(getBasinSum(p.x, p.y)));
            basinSumList.sort(Collections.reverseOrder());
            return basinSumList.get(0) * basinSumList.get(1) * basinSumList.get(2);
        }

        public int getBasinSum(int row, int column) {
            if (row < 0 || row == this.highMap.length || column < 0 || column == this.highMap[row].length) {
                return 0;
            } else if (this.highMap[row][column] == 9) {
                return 0;
            } else {
                this.highMap[row][column] = 9;
                return 1 + getBasinSum(row - 1, column) + getBasinSum(row + 1, column) + getBasinSum(row, column - 1) + getBasinSum(row, column + 1);
            }
        }

        public int getRiskLevelSum() {
            int riskLevelSum = 0;
            this.pointList.clear();
            for (int i = 0; i < this.highMap.length; i++) {
                for (int j = 0; j < this.highMap[i].length; j++) {
                    if (isTopBigger(i, j) && isBottomBigger(i, j) && isLeftBigger(i, j) && isRightBigger(i, j)) {
                        riskLevelSum += Integer.parseInt(this.highMap[i][j] + "") + 1;
                        this.pointList.add(new Point(i, j));
                    }
                }
            }
            return riskLevelSum;
        }

        private boolean isTopBigger(int row, int column) {
            if (row >= 1) {
                return this.highMap[row - 1][column] > this.highMap[row][column];
            }
            return true;
        }

        private boolean isBottomBigger(int row, int column) {
            if (row + 1 < this.highMap.length) {
                return this.highMap[row + 1][column] > this.highMap[row][column];
            }
            return true;
        }

        private boolean isLeftBigger(int row, int column) {
            if (column >= 1) {
                return this.highMap[row][column - 1] > this.highMap[row][column];
            }
            return true;
        }

        private boolean isRightBigger(int row, int column) {
            if (column + 1 < this.highMap[row].length) {
                return this.highMap[row][column + 1] > this.highMap[row][column];
            }
            return true;
        }

        public String print() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.highMap.length; i++) {
                for (int j = 0; j < this.highMap[0].length; j++) {
                    sb.append(this.highMap[i][j]);
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
