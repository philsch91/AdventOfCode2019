package at.fhcampuswien.adventofcode.puzzle;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Day2Part1PuzzleTest {

    @Test
    public void solve() throws IOException {
        Day2Part1Puzzle puzzle = new Day2Part1Puzzle();
        int result = (int) puzzle.solve();
        assertEquals(8017076, result);
    }
}