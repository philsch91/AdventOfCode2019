package at.fhcampuswien.adventofcode.puzzle;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Day4Part1PuzzleTest {

    @Test
    public void solve() throws IOException {
        Day4Part1Puzzle puzzle = new Day4Part1Puzzle();
        int result = (int) puzzle.solve();
        assertEquals(1063, result);
    }

    @Test
    public void testCountTwoAdjacentDigits_found3(){
        Day4Part1Puzzle puzzle = new Day4Part1Puzzle();
        int result = puzzle.countTwoAdjacentDigits("111123");
        System.out.println("Day4Part1PuzzleTest testCountTwoAdjacentDigits_found3 " + Integer.toString(result));
        assertEquals(3, result);
    }

    @Test
    public void testCountTwoAdjacentDigits_found5(){
        Day4Part1Puzzle puzzle = new Day4Part1Puzzle();
        int result = puzzle.countTwoAdjacentDigits("111111");
        System.out.println("Day4Part1PuzzleTest testCountTwoAdjacentDigits_found5 " + Integer.toString(result));
        assertEquals(5, result);
    }

    @Test
    public void testCountTwoAdjacentDigits_notFound(){
        Day4Part1Puzzle puzzle = new Day4Part1Puzzle();
        int result = puzzle.countTwoAdjacentDigits("123789");
        System.out.println("Day4Part1PuzzleTest testCountTwoAdjacentDigits_notFound " + Integer.toString(result));
        assertEquals(0, result);
    }

    @Test
    public void testCountNotIncreasingDigits_found1(){
        Day4Part1Puzzle puzzle = new Day4Part1Puzzle();
        int result = puzzle.countNotIncreasingDigits("223450");
        System.out.println("Day4Part1PuzzleTest testCountNotIncreasingDigits_found1 " + Integer.toString(result));
        assertEquals(1, result);
    }

    @Test
    public void testCountNotIncreasingDigits_notFound(){
        Day4Part1Puzzle puzzle = new Day4Part1Puzzle();
        int result = puzzle.countNotIncreasingDigits("123789");
        System.out.println("Day4Part1PuzzleTest testCountNotIncreasingDigits_notFound " + Integer.toString(result));
        assertEquals(0, result);
    }
}