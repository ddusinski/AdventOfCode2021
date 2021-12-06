package com.dusinski.aoc.solutions.day5;

import java.util.ArrayList;
import java.util.List;

public class HydrothermalDiagram {
    private final int[][] diagram;
    private final List<Instruction> instructionList = new ArrayList();

    public HydrothermalDiagram(int n, int m) {
        int max = Math.max(n, m);
        this.diagram = new int[max + 1][max + 1];
    }

    public static Instruction.CooPoint getMaxPoint(List<Instruction> instrList) {
        int x = 0;
        int y = 0;
        for (Instruction instr : instrList) {
            if (instr.startPoint.getX() > x) {
                x = instr.startPoint.getX();
            }
            if (instr.endPoint.getX() > x) {
                x = instr.endPoint.getX();
            }
            if (instr.startPoint.getY() > y) {
                y = instr.startPoint.getY();
            }
            if (instr.endPoint.getY() > y) {
                y = instr.endPoint.getY();
            }
        }

        return new Instruction.CooPoint(x, y);
    }

    public void fillDiagramHV(List<Instruction> instrList) {
        instrList.forEach(this::insertInstructionHV);
    }

    public void fillDiagramHVD(List<Instruction> instrList) {
        instrList.forEach(this::insertInstructionHVD);
    }

    private void insertInstructionHV(Instruction instr) {
        if (instr.startPoint.getX() == instr.endPoint.getX()) {
            // fill vertical
            for (int i = 0; i < Math.abs(instr.endPoint.getY() - instr.startPoint.getY()) + 1; i++) {
                this.diagram[Math.min(instr.endPoint.getY(), instr.startPoint.getY()) + i][instr.startPoint.getX()]++;
            }
        } else if (instr.startPoint.getY() == instr.endPoint.getY()) {
            // fill horizontal
            for (int i = 0; i < Math.abs(instr.endPoint.getX() - instr.startPoint.getX()) + 1; i++) {
                this.diagram[instr.startPoint.getY()][Math.min(instr.endPoint.getX(), instr.startPoint.getX()) + i]++;
            }
        }
    }

    private void insertInstructionHVD(Instruction instr) {
        if (instr.startPoint.getX() == instr.endPoint.getX()) {
            // fill vertical
            for (int i = 0; i < Math.abs(instr.endPoint.getY() - instr.startPoint.getY()) + 1; i++) {
                this.diagram[Math.min(instr.endPoint.getY(), instr.startPoint.getY()) + i][instr.startPoint.getX()]++;
            }
        } else if (instr.startPoint.getY() == instr.endPoint.getY()) {
            // fill horizontal
            for (int i = 0; i < Math.abs(instr.endPoint.getX() - instr.startPoint.getX()) + 1; i++) {
                this.diagram[instr.startPoint.getY()][Math.min(instr.endPoint.getX(), instr.startPoint.getX()) + i]++;
            }
        } else {//find min x
            Instruction.CooPoint min;
            Instruction.CooPoint max;

            if (instr.startPoint.getX() < instr.endPoint.getX()) {
                min = instr.startPoint;
                max = instr.endPoint;
            } else {
                min = instr.endPoint;
                max = instr.startPoint;
            }
            if (min.getY() < max.getY()) {
                for (int i = 0; i < max.getX() - min.getX() + 1; i++) {
                    this.diagram[min.getY() + i][min.getX() + i]++;
                }
            } else {
                for (int i = 0; i < max.getX() - min.getX() + 1; i++) {
                    this.diagram[min.getY() - i][min.getX() + i]++;
                }
            }

        }

    }

    public int getAtLeastTwoOverlaps() {
        int overlapCount = 0;
        for (int i = 0; i < this.diagram.length; i++) {
            for (int j = 0; j < this.diagram[0].length; j++) {
                if (this.diagram[i][j] > 1) {
                    overlapCount++;
                }
            }
        }
        return overlapCount;
    }

    public String getDiagramString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.diagram.length; i++) {
            for (int j = 0; j < this.diagram[0].length; j++) {
                sb.append(this.diagram[i][j]).append(" ");
            }
            sb.append("\n");
        }


        return sb.toString();
    }

}
