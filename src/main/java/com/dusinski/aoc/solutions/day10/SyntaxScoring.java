package com.dusinski.aoc.solutions.day10;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SyntaxScoring {

    private final List<NavSysLine> navSysLineList = new ArrayList<>();

    public SyntaxScoring() {
        List<String> inputList = DataLoadUtil.loadStringList("day10.txt");
        inputList.forEach(s -> this.navSysLineList.add(new NavSysLine(s)));
    }

    public int getTotalSyntaxErrorScore() {
        int scoreSum = 0;
        for (NavSysLine line : this.navSysLineList) {
            scoreSum += line.getErrorScore();
        }
        return scoreSum;
    }

    public long getMiddleStorePart2() {
        List<Long> scoreList = new ArrayList<>();
        this.navSysLineList.forEach(NavSysLine::getErrorScore);
        this.navSysLineList.removeIf(s -> s.isIllegal);
        this.navSysLineList.forEach(NavSysLine::removeOccurrences);
        this.navSysLineList.forEach(s -> scoreList.add(s.lineScore));
        scoreList.sort(Comparator.naturalOrder());
        return scoreList.get(scoreList.size() / 2);
    }
}



