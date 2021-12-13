package com.dusinski.aoc.solutions.day13;

import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class TransparentPaper {
    public Map<Point, Integer> dotsMap;
    public Queue<FoldingInstr> foldingInstrList;
    public int maxY;
    public int maxX;

    public TransparentPaper(List<String> input) {
        this.dotsMap = new LinkedHashMap<>();
        this.foldingInstrList = new LinkedList<>();
        for (String s : input) {
            if (!s.isEmpty()) {
                if (s.contains(",")) {
                    this.dotsMap.put(getPoint(s), 1);
                } else {
                    this.foldingInstrList.add(new FoldingInstr(s));
                }
            }
        }
        getMax();
    }

    public TransparentPaper(Map<Point, Integer> newDotsMap, Queue<FoldingInstr> newFoldingInstrList) {
        this.dotsMap = newDotsMap;
        this.foldingInstrList = newFoldingInstrList;
        getMax();
    }

    private void getMax() {
        int mX = Integer.MIN_VALUE;
        int mY = Integer.MIN_VALUE;
        for (Map.Entry<Point, Integer> entry : this.dotsMap.entrySet()) {
            if (entry.getKey().y > mY) {
                mY = entry.getKey().y;
            }
            if (entry.getKey().x > mX) {
                mX = entry.getKey().x;
            }
        }
        this.maxX = mX;
        this.maxY = mY;
    }

    private Point getPoint(String i) {
        String[] sArray = i.split(",");
        return new Point(Integer.parseInt(sArray[0]), Integer.parseInt(sArray[1]));
    }

    public TransparentPaper foldPage() {
        FoldingInstr foldingInstr = this.foldingInstrList.poll();
        Map<Point, Integer> dotsMapAfterFolding = new LinkedHashMap<>();
        if (foldingInstr.direction.equals("y")) {
            dotsMapAfterFolding = foldPageHori(foldingInstr.instrVal);
        } else {
            dotsMapAfterFolding = foldPageVerti(foldingInstr.instrVal);
        }
        return new TransparentPaper(dotsMapAfterFolding, this.foldingInstrList);
    }

    public Map<Point, Integer> foldPageHori(int foldVal) {
        Map<Point, Integer> dotsMapAfterFolding = new LinkedHashMap<>();
        for (Map.Entry<Point, Integer> entry : this.dotsMap.entrySet()) {
            Point currentPoint = entry.getKey();
            if (currentPoint.y < foldVal) {
                dotsMapAfterFolding.put(currentPoint, 1);
            } else {
                Point newPoint = new Point(currentPoint.x, foldVal*2 - currentPoint.y);
                if (dotsMapAfterFolding.containsKey(newPoint)) {
                    int currentVal = dotsMapAfterFolding.get(newPoint) + 1;
                    dotsMapAfterFolding.put(newPoint, currentVal);
                } else {
                    dotsMapAfterFolding.put(newPoint, 1);
                }
            }
        }
        return dotsMapAfterFolding;
    }

    public Map<Point, Integer> foldPageVerti(int foldVal) {
        Map<Point, Integer> dotsMapAfterFolding = new LinkedHashMap<>();
        for (Map.Entry<Point, Integer> entry : this.dotsMap.entrySet()) {
            Point currentPoint = entry.getKey();
            if (currentPoint.x < foldVal) {
                dotsMapAfterFolding.put(currentPoint, 1);
            } else {
                Point newPoint = new Point(foldVal*2  - currentPoint.x, currentPoint.y);
                if (dotsMapAfterFolding.containsKey(newPoint)) {
                    int currentVal = dotsMapAfterFolding.get(newPoint) + 1;
                    dotsMapAfterFolding.put(newPoint, currentVal);
                } else {
                    dotsMapAfterFolding.put(newPoint, 1);
                }
            }
        }
        return dotsMapAfterFolding;
    }

    public char[][] genArray() {
        char[][] resultArray = new char[this.maxY + 1][this.maxX + 1];
        for (Point p : this.dotsMap.keySet()) {
            resultArray[p.y][p.x] = '#';
        }
        return resultArray;
    }

    @Override
    public String toString() {
        return "TransparentPaper{" +
                "dotsMap=" + dotsMap +
                ", foldingInstrList=" + foldingInstrList +
                ", maxY=" + maxY +
                ", maxX=" + maxX +
                '}';
    }

    private class FoldingInstr {
        String direction;
        int instrVal;

        public FoldingInstr(String i) {
            i = i.replace("fold along ", "");
            String[] sArray = i.split("=");
            this.direction = sArray[0];
            this.instrVal = Integer.parseInt(sArray[1]);
        }

        @Override
        public String toString() {
            return "FoldingInstr{" +
                    "direction='" + direction + '\'' +
                    ", instrVal=" + instrVal +
                    '}';
        }
    }
}