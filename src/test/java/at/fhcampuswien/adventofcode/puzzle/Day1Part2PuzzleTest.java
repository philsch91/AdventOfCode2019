package at.fhcampuswien.adventofcode.puzzle;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Day1Part2PuzzleTest {

    @Test
    public void solve() throws IOException {
        Day1Part2Puzzle puzzle = new Day1Part2Puzzle();
        int result = (int) puzzle.solve();
        assertEquals(4825810, result);
    }

    @Test
    public void calculateFuel() {
    }
}