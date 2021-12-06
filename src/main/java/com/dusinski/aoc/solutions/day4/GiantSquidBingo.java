package com.dusinski.aoc.solutions.day4;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiantSquidBingo {

    private List<Integer> instrList = new ArrayList<>();
    private List<BingoBoard> bingoBoardList = new ArrayList<>();

    public GiantSquidBingo() {
        List<String> bingInstr = DataLoadUtil.loadStringList("day4.txt");
        readInput(bingInstr);

    }

    public int calcPart1() {
        return calcBingo();
    }

    public int calcPart2() {
        return calcBingo2();
    }
    public void clearBingoBoards(){
        this.instrList = new ArrayList<>();
        List<String> bingInstr = DataLoadUtil.loadStringList("day4.txt");
        readInput(bingInstr);
    }


    private int calcBingo() {
        for (Integer instr : this.instrList) {
            for (BingoBoard board : this.bingoBoardList) {
                if (board.markValueOnTheBoard(instr)) {
                    return board.sumOfArray * instr;
                }
            }
        }
        return -1;
    }

    private int calcBingo2() {
        int winNum = 0;
        int boardCount = this.bingoBoardList.size();
        int currentInstr = 0;
        BingoBoard lastBB = null;
        for (Integer instr : this.instrList) {
            for (BingoBoard board : this.bingoBoardList) {
                if (boardCount > 0) {
                    if (board.winOrderNumber == -1 && board.markValueOnTheBoard(instr)) {
                        board.winOrderNumber = winNum;
                        winNum++;
                        boardCount--;
                        currentInstr = instr;
                    }
                }
            }
        }
        lastBB = this.bingoBoardList.get(0);
        for (BingoBoard board : this.bingoBoardList) {
            if (board.winOrderNumber > lastBB.winOrderNumber) {
                lastBB = board;
            }
        }
        return lastBB.sumOfArray * currentInstr;
    }

    private void readInput(List<String> input) {
        String instrString = input.get(0);
        Arrays.asList(instrString.split(",")).forEach(s -> this.instrList.add(Integer.parseInt(s)));
        int[][] tempBoard = new int[5][5];
        int tempRowNum = 0;
        for (int i = 2; i < input.size(); i++) {
            if (!input.get(i).isEmpty()) {
                String startString = input.get(i).replace("  ", " ");
                if (startString.charAt(0) == ' ') {
                    startString = startString.substring(1, startString.length());
                }
                String[] stringRow = startString.split(" ");
                for (int j = 0; j < stringRow.length; j++) {
                    tempBoard[tempRowNum][j] = Integer.parseInt(stringRow[j]);
                }
                tempRowNum++;
            } else {
                tempRowNum = 0;
                bingoBoardList.add(new BingoBoard(tempBoard));
                tempBoard = new int[5][5];
            }
        }
        bingoBoardList.add(new BingoBoard(tempBoard));
    }


}
