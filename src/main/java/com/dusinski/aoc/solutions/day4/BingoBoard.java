package com.dusinski.aoc.solutions.day4;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BingoBoard {
    int  winOrderNumber=-1;
    int sumOfArray = 0;
    private int[][] boardArray; // x - column, y - row
    private int[] rowOccupation = new int[5];
    private int[] columnOccupation = new int[5];
    private Map<Integer, List<BoardPos>> valuePositionMap = new LinkedHashMap<>();

    public BingoBoard(int[][] inputArray) {
        genPositionMap(inputArray);
        getSumOfArray(inputArray);
        this.boardArray = inputArray;
    }

    private void getSumOfArray(int[][] inputArray) {
        for (int i = 0; i < inputArray[0].length; i++) {
            for (int j = 0; j < inputArray[1].length; j++) {
                this.sumOfArray += inputArray[i][j];
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < this.boardArray[0].length; i++) {
            for (int j = 0; j < this.boardArray[1].length; j++) {
                out.append(this.boardArray[i][j]).append(" ");
            }
            out.append("\n");
        }
        return out.toString();
    }

    public boolean markValueOnTheBoard(int val) {
        if (!this.valuePositionMap.containsKey(val)) {
            return false;
        } else {
            for (BoardPos boardPos : this.valuePositionMap.get(val)) {
                this.columnOccupation[boardPos.column]++;
                this.rowOccupation[boardPos.row]++;
                this.sumOfArray -= val;
                if (this.columnOccupation[boardPos.column] == 5) {
                    return true;
                }
                if (this.rowOccupation[boardPos.row] == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private void genPositionMap(int[][] inputArray) {
        for (int i = 0; i < inputArray[0].length; i++) {
            for (int j = 0; j < inputArray[1].length; j++) {
                int currentVal = inputArray[i][j];
                if (!this.valuePositionMap.containsKey(currentVal)) {
                    ArrayList<BoardPos> tempList = new ArrayList<>();
                    tempList.add(new BoardPos(i, j));
                    this.valuePositionMap.put(currentVal, tempList);
                } else {
                    this.valuePositionMap.get(currentVal).add(new BoardPos(i, j));
                }
            }
        }
    }

    private class BoardPos {
        int row;
        int column;

        BoardPos(int x, int y) {
            this.row = x;
            this.column = y;
        }
    }
}
