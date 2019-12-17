package at.fhcampuswien.adventofcode.puzzle;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Day2Part2PuzzleTest {

    @Test
    public void solve() throws IOException {
        Day2Part2Puzzle puzzle = new Day2Part2Puzzle();
        int result = (int) puzzle.solve();
        assertEquals(3146, result);
    }
}