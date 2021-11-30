package com.dusinski.aoc;

import com.dusinski.aoc.solutions.test.test;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for AoC2021
 */
public class AoC2021Test {
    @Test
    public void rocketEquationPart1() { //day1
        assertEquals(Long.valueOf(3252208L), test.rocketEquationPart1());
        assertEquals(Long.valueOf(4875451L), test.rocketEquationPart2());
    }
}
