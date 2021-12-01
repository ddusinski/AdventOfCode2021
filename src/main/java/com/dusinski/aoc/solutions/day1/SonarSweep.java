package com.dusinski.aoc.solutions.day1;

import com.dusinski.aoc.util.DataLoadUtil;

import java.util.List;

public class SonarSweep {

    public static int getLargerMeasurementsCount() {
        List<Long> depthLevels = DataLoadUtil.loadLongList("day1.txt");
        int largerMeasurementsCounter = 0;
        long previousDepth = depthLevels.get(0);
        for (int i = 1; i < depthLevels.size(); i++) {
            if (depthLevels.get(i) > previousDepth) {
                largerMeasurementsCounter++;
            }
            previousDepth = depthLevels.get(i);
        }
        return largerMeasurementsCounter;
    }

    public static int getThreeMeasurementsSlideCount() {
        List<Long> depthLevels = DataLoadUtil.loadLongList("day1.txt");
        int largerMeasurementsCounter = 0;
        long previousSlideSum = getThreeSlideCount(depthLevels, 0);
        for (int i = 1; i < depthLevels.size(); i++) {
            long nextSlideSum = getThreeSlideCount(depthLevels, i);
            if (nextSlideSum != -1) {
                if (nextSlideSum > previousSlideSum) {
                    largerMeasurementsCounter++;
                }
                previousSlideSum = nextSlideSum;
            } else return largerMeasurementsCounter;
        }
        return -1;
    }

    private static long getThreeSlideCount(List<Long> input, int position) {
        if (input.size() > position + 2) {
            return input.get(position) + input.get(position + 1) + input.get(position + 2);
        } else
            return -1;
    }
}
