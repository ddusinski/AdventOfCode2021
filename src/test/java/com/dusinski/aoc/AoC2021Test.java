package com.dusinski.aoc;

import com.dusinski.aoc.solutions.day1.SonarSweep;
import com.dusinski.aoc.solutions.day2.Dive;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for AoC2021
 */
public class AoC2021Test {
    @Test
    public void testSonarSweep() {
        StopWatch watch = new StopWatch();
        watch.start();
        assertEquals(1154, SonarSweep.getLargerMeasurementsCount());
        assertEquals(1127, SonarSweep.getThreeMeasurementsSlideCount());
        watch.stop();
        System.out.println("Day 1 Sonar Sweep time elapsed: " + watch.getTime());
    }
    @Test
    public void testDive() {
        StopWatch watch = new StopWatch();
        watch.start();
        assertEquals(1990000, Dive.getFinalPosMulti());
        assertEquals(1975421260, Dive.getFinalPosMultiWithAim());
        watch.stop();
        System.out.println("Day 2 Dive time elapsed: " + watch.getTime());
    }



}
