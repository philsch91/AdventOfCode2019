package at.fhcampuswien.adventofcode.puzzle;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Day4Part2PuzzleTest {

    @Test
    public void solve() throws IOException {
        Day4Part2Puzzle puzzle = new Day4Part2Puzzle();
        int result = (int) puzzle.solve();
        assertEquals(686, result);
    }

    @Test
    public void testCountAdjacentDigits_found3When2() {
        Day4Part2Puzzle puzzle = new Day4Part2Puzzle();
        int result = puzzle.countAdjacentDigits("112233", 2);
        System.out.println("Day4Part2PuzzleTest testCountAdjacentDigits_found3When2 " + Integer.toString(result));
        assertEquals(3, result);
    }

    @Test
    public void testCountAdjacentDigits_found1When2() {
        Day4Part2Puzzle puzzle = new Day4Part2Puzzle();
        int result = puzzle.countAdjacentDigits("111122", 2);
        System.out.println("Day4Part2PuzzleTest testCountAdjacentDigits_found1When2 " + Integer.toString(result));
        assertEquals(1, result);
    }

    @Test
    public void testCountAdjacentDigits_notFoundWhen2() {
        Day4Part2Puzzle puzzle = new Day4Part2Puzzle();
        int result = puzzle.countAdjacentDigits("123444", 2);
        System.out.println("Day4Part2PuzzleTest testCountAdjacentDigits_notFoundWhen2 " + Integer.toString(result));
        assertEquals(0, result);
    }
}