package at.fhcampuswien.adventofcode.puzzle;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Day1Part1PuzzleTest {

    @Test
    public void testSolve() throws IOException {
        Day1Part1Puzzle puzzle = new Day1Part1Puzzle();
        int result = (int) puzzle.solve();
        assertEquals(3219099, result);
    }

    @Test
    public void testCalculateMass() {
        int mass = 137569;
        Day1Part1Puzzle puzzle = new Day1Part1Puzzle();
        int result = puzzle.calculateMass(mass);
        assertEquals(45854, result);
    }
}
