package com.dusinski.aoc.solutions.day5;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.ArrayList;
import java.util.List;

public class HydrothermalVenture {

    List<Instruction> instructionList = new ArrayList<>();
    HydrothermalDiagram hd;

    public HydrothermalVenture() {
        List<String> lineInstr = DataLoadUtil.loadStringList("day5.txt");
        lineInstr.forEach(s -> instructionList.add(new Instruction(s)));
        Instruction.CooPoint maxPoint = HydrothermalDiagram.getMaxPoint(instructionList);
        this.hd = new HydrothermalDiagram(maxPoint.getX(), maxPoint.getY());
    }

    public void reloadDiagram(){
        Instruction.CooPoint maxPoint = HydrothermalDiagram.getMaxPoint(instructionList);
        this.hd = new HydrothermalDiagram(maxPoint.getX(), maxPoint.getY());
    }

    public int getHVOverlapCount() {
        hd.fillDiagramHV(instructionList);
        return hd.getAtLeastTwoOverlaps();
    }

    public int getHVDOverlapCount() {
        hd.fillDiagramHVD(instructionList);
        return hd.getAtLeastTwoOverlaps();
    }

}
