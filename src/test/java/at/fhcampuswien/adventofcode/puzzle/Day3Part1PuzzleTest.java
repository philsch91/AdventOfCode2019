package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.Point;
import at.fhcampuswien.adventofcode.util.FileUtil;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Day3Part1PuzzleTest {

    @Test
    public void testSolve() throws IOException {
        Day3Part1Puzzle puzzle = new Day3Part1Puzzle();
        int result = (int) puzzle.solve();
        assertEquals(8015, result);
    }

    @Test
    public void testCalculateManhattanDistance() {
        Day3Part1Puzzle puzzle = new Day3Part1Puzzle();
        Point point = new Point(6491, 1524);
        int distance = puzzle.calculateManhattanDistance(point);
        assertEquals(8015, distance);
    }

    @Test
    public void testParseWire() throws IOException {
        String file = "day3.txt";
        String[] allLines = FileUtil.readFile(file);
        String[] w1 = allLines[0].split(",");
        String[] w2 = allLines[1].split(",");

        Day3Part1Puzzle puzzle = new Day3Part1Puzzle();
        Point[] wire1 =  puzzle.parseWire(w1);
        Point[] wire2 =  puzzle.parseWire(w2);
        System.out.println("wire1.length " + wire1.length);
        System.out.println("wire2.length " + wire2.length);
        assertEquals(154138, wire1.length);
    }

    @Test
    public void testReadBounds() throws IOException {
        String file = "day3.txt";
        String[] allLines = FileUtil.readFile(file);

        String[] w1 = allLines[0].split(",");

        Day3Part1Puzzle puzzle = new Day3Part1Puzzle();
        int[] bounds = puzzle.readBounds(w1);

        System.out.println("bounds.minX: " + bounds[0]);
        System.out.println("bounds.maxX: " + bounds[1]);
        System.out.println("bounds.minY: " + bounds[2]);
        System.out.println("bounds.maxY: " + bounds[3]);

        String[] w2 = allLines[1].split(",");
        int[] bounds2 = puzzle.readBounds(w2);

        System.out.println("bounds2.minX: " + bounds2[0]);
        System.out.println("bounds2.maxX: " + bounds2[1]);
        System.out.println("bounds2.minY: " + bounds2[2]);
        System.out.println("bounds2.maxY: " + bounds2[3]);

        assertEquals(3539, bounds[3]);
    }
}