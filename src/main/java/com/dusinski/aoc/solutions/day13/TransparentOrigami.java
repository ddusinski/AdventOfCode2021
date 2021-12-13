package com.dusinski.aoc.solutions.day13;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.List;

public class TransparentOrigami {

    private TransparentPaper tp;

    private void loadData() {
        List<String> inputList = DataLoadUtil.loadStringList("day13.txt");
        this.tp = new TransparentPaper(inputList);
    }

    public int getDotsCountAfterOneFold() {
        loadData();
        return this.tp.foldPage().dotsMap.size();
    }

    public String getDotsCountAfterAllFolds() {
        loadData();
        int counter = this.tp.foldingInstrList.size();
        for (int i = 0; i < counter; i++) {
            this.tp = this.tp.foldPage();

        }
        return printArray(this.tp.genArray());

    }

    private String printArray(char[][] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == '#') {
                    sb.append(a[i][j]);
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
