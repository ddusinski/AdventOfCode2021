package com.dusinski.aoc;

import com.dusinski.aoc.solutions.day1.SonarSweep;
import com.dusinski.aoc.solutions.day10.SyntaxScoring;
import com.dusinski.aoc.solutions.day11.DumboOctopus;
import com.dusinski.aoc.solutions.day13.TransparentOrigami;
import com.dusinski.aoc.solutions.day15.Chiton;
import com.dusinski.aoc.solutions.day17.TrickShot;
import com.dusinski.aoc.solutions.day2.Dive;
import com.dusinski.aoc.solutions.day3.BinaryDiagnostic;
import com.dusinski.aoc.solutions.day4.GiantSquidBingo;
import com.dusinski.aoc.solutions.day5.HydrothermalVenture;
import com.dusinski.aoc.solutions.day6.Lanternfish;
import com.dusinski.aoc.solutions.day7.WhalesTreachery;
import com.dusinski.aoc.solutions.day8.SevenSegmentSearch;
import com.dusinski.aoc.solutions.day9.SmokeBasin;
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
        System.out.println("Day 1 time elapsed: " + watch.getTime());
    }

    @Test
    public void testDive() {
        StopWatch watch = new StopWatch();
        watch.start();
        assertEquals(1990000, Dive.getFinalPosMulti());
        assertEquals(1975421260, Dive.getFinalPosMultiWithAim());
        watch.stop();
        System.out.println("Day 2 time elapsed: " + watch.getTime());
    }

    @Test
    public void testBinaryDiagnostic() {
        StopWatch watch = new StopWatch();
        watch.start();
        assertEquals(3374136, BinaryDiagnostic.getPowerConsumption());
        assertEquals(4432698, BinaryDiagnostic.getLifeSupportRating());
        watch.stop();
        System.out.println("Day 3 time elapsed: " + watch.getTime());
    }

    @Test
    public void testGiantSquidBingo() {
        StopWatch watch = new StopWatch();
        watch.start();
        GiantSquidBingo gsb = new GiantSquidBingo();
        assertEquals(16716, gsb.calcPart1());
        gsb.clearBingoBoards();
        assertEquals(4880, gsb.calcPart2());
        watch.stop();
        System.out.println("Day 4 time elapsed: " + watch.getTime());
    }

    @Test
    public void testHydrothermalVenture() {
        StopWatch watch = new StopWatch();
        watch.start();
        HydrothermalVenture hv = new HydrothermalVenture();
        assertEquals(8060, hv.getHVOverlapCount());
        hv.reloadDiagram();
        assertEquals(21577, hv.getHVDOverlapCount());
        watch.stop();
        System.out.println("Day 5 time elapsed: " + watch.getTime());
    }

    @Test
    public void testLanternfish() {
        StopWatch watch = new StopWatch();
        watch.start();
        Lanternfish lf = new Lanternfish();
//        assertEquals(383160, lf.getGenNumAfter80days());
        assertEquals(383160, lf.getIterative(80));
//        assertEquals(1721148811504L, lf.getIterative(256));
        watch.stop();
        System.out.println("Day 6 time elapsed: " + watch.getTime());
    }

    @Test
    public void testWhalesTreachery() {
        StopWatch watch = new StopWatch();
        watch.start();
        WhalesTreachery wt = new WhalesTreachery();
        assertEquals(343605, wt.getLeastFuelPossiblePart1());
        assertEquals(96744904, wt.getLeastFuelPossiblePart2());
        watch.stop();
        System.out.println("Day 7 time elapsed: " + watch.getTime());
    }

    @Test
    public void testSevenSegmentSearch() {
        StopWatch watch = new StopWatch();
        watch.start();
        SevenSegmentSearch sss = new SevenSegmentSearch();
        assertEquals(344, sss.getEasyDigitsPart1());
        assertEquals(1048410, sss.getEasyDigitsPart2SET());
        watch.stop();
        System.out.println("Day 8 time elapsed: " + watch.getTime());
    }

    @Test
    public void testSmokeBasinSum() {
        StopWatch watch = new StopWatch();
        watch.start();
        SmokeBasin sb = new SmokeBasin();
        assertEquals(436, sb.getRiskLevelSum());
        assertEquals(1317792, sb.getSize3LargestBasins());
        watch.stop();
        System.out.println("Day 9 time elapsed: " + watch.getTime());
    }

    @Test
    public void testSyntaxScoring() {
        StopWatch watch = new StopWatch();
        watch.start();
        SyntaxScoring ss = new SyntaxScoring();
        assertEquals(394647, ss.getTotalSyntaxErrorScore());
        assertEquals(2380061249L, ss.getMiddleStorePart2());
        watch.stop();
        System.out.println("Day 10 time elapsed: " + watch.getTime());
    }

    @Test
    public void testSDumboOctopus() {
        StopWatch watch = new StopWatch();
        watch.start();
        DumboOctopus dOct = new DumboOctopus();
        assertEquals(314, dOct.getStepWhenAllFlash());
        assertEquals(1591, dOct.getFlashesAfter100Steps());
        watch.stop();
        System.out.println("Day 11 time elapsed: " + watch.getTime());
    }

//    @Test
//    public void testPassagePathing() {
//        StopWatch watch = new StopWatch();
//        watch.start();
//        PassagePathing pp = new PassagePathing();
//        assertEquals(3576, pp.getPathsCount());
//        watch.stop();
//        System.out.println("Day 12 time elapsed: " + watch.getTime());
//    }

    @Test
    public void testTransparentOrigami() {
        StopWatch watch = new StopWatch();
        watch.start();
        TransparentOrigami to = new TransparentOrigami();

        String result="###..####.####...##.#..#.###..####.####\n" +
                "#..#....#.#.......#.#..#.#..#.#.......#\n" +
                "#..#...#..###.....#.####.#..#.###....#.\n" +
                "###...#...#.......#.#..#.###..#.....#..\n" +
                "#....#....#....#..#.#..#.#.#..#....#...\n" +
                "#....####.#.....##..#..#.#..#.#....####\n";

        assertEquals(610, to.getDotsCountAfterOneFold());
        assertEquals(result, to.getDotsCountAfterAllFolds());
        watch.stop();
        System.out.println("Day 13 time elapsed: " + watch.getTime());
    }


    @Test
    public void testChitonGraph() {
        StopWatch watch = new StopWatch();
        watch.start();
        Chiton chiton = new Chiton();
        assertEquals(361, chiton.genGraphPart1());
//        assertEquals(3570, ts.getMaxHigh());
        watch.stop();
        System.out.println("Day 15 time elapsed: " + watch.getTime());
    }



    @Test
    public void testTrickShot() {
        StopWatch watch = new StopWatch();
        watch.start();
        TrickShot ts = new TrickShot();
        assertEquals(1919, ts.getPossibleVelCount());
        assertEquals(3570, ts.getMaxHigh());
        watch.stop();
        System.out.println("Day 17 time elapsed: " + watch.getTime());
    }

}
