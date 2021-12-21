package com.dusinski.aoc.solutions.day14;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.ArrayList;
import java.util.List;

public class ExtendedPolymerization {
    PolymerTemplate pt;

    private void loadData() {
//        List<String> inputList = DataLoadUtil.loadStringList("day14.txt");
        List<String> inputList = DataLoadUtil.loadStringList("test1.txt");
        this.pt = new PolymerTemplate(inputList);
    }

    public long getMostMinusLeastPart1() {
        this.loadData();
        System.out.println(this.pt);
        String input = this.pt.polymerTemplateString;
        for (int i = 0; i < 10; i++) {
            input = this.pt.calculateTemplateString(input);
            System.out.println("i: " + i + " input.length(): " + input.length());
        }

        return this.pt.getMinMaxQuantity(input);
    }

    public long getMostMinusLeastPart2() {
        this.loadData();
        System.out.println(this.pt);
        String input = this.pt.polymerTemplateString;
        List<String> result = new ArrayList<>();
        result.add(input);
        for (int i = 0; i < 40; i++) {

            result = this.pt.calculateTemplateStringVER2(result);
//            System.out.println("i: " + i + " this.pt.getMinMaxQuantity(input): " + this.pt.getMinMaxQuantity(input));
            System.out.println("i: " + i);
        }
        System.out.println("result: " + result);

        return this.pt.getMinMaxQuantityFromList(result);
    }


}
