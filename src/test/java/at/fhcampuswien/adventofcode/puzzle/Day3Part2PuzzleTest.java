package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.Point;
import at.fhcampuswien.adventofcode.util.FileUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class Day3Part2PuzzleTest {

    @Test
    public void testSolve() throws IOException{
        Day3Part2Puzzle puzzle = new Day3Part2Puzzle();
        int result = (int) puzzle.solve();
        assertEquals(163676, result);
    }

    @Test
    public void testSolve_example1() throws IOException {
        String file = "day32test.txt";
        String[] allLines = FileUtil.readFile(file);

        String[] w1 = allLines[0].split(",");
        String[] w2 = allLines[1].split(",");

        Day3Part2Puzzle puzzle = new Day3Part2Puzzle();

        Point[] wire1 = puzzle.parseWire(w1);
        wire1 = puzzle.removeOriginPoint(wire1);
        //wire1 = puzzle.removeDuplicatePoints(wire1);

        Point[] wire2 = puzzle.parseWire(w2);
        wire2 = puzzle.removeOriginPoint(wire2);
        //wire2 = puzzle.removeDuplicatePoints(wire2);

        Hashtable<String, Point> pointTable1 = puzzle.convertToPointTable(wire1);
        Hashtable<String, Point> pointTable2 = puzzle.convertToPointTable(wire2);

        Point[] crossingPoints = puzzle.extractCrossingPoints(pointTable1, pointTable2);
        crossingPoints = puzzle.removeOriginPoint(crossingPoints);

        int result =  puzzle.extractShortestWireLength(crossingPoints, wire1, wire2);
        System.out.println("Day3Part2PuzzleTest example1 " + Integer.toString(result));
        assertEquals(610, result);
    }

    @Test
    public void testSolve_example2() throws IOException {
        String file = "day32test.txt";
        String[] allLines = FileUtil.readFile(file);

        String[] w1 = allLines[3].split(",");
        String[] w2 = allLines[4].split(",");

        Day3Part2Puzzle puzzle = new Day3Part2Puzzle();

        Point[] wire1 = puzzle.parseWire(w1);
        wire1 = puzzle.removeOriginPoint(wire1);

        Point[] wire2 = puzzle.parseWire(w2);
        wire2 = puzzle.removeOriginPoint(wire2);

        /*
        for(Point point : wire1){
            System.out.println(point);
        } */

        Hashtable<String, Point> pointTable1 = puzzle.convertToPointTable(wire1);
        Hashtable<String, Point> pointTable2 = puzzle.convertToPointTable(wire2);

        Point[] crossingPoints = puzzle.extractCrossingPoints(pointTable1, pointTable2);
        crossingPoints = puzzle.removeOriginPoint(crossingPoints);

        int result =  puzzle.extractShortestWireLength(crossingPoints, wire1, wire2);
        System.out.println("Day3Part2PuzzleTest example2 " + Integer.toString(result));
        assertEquals(410, result);
    }

    @Test
    public void testSolve_example3() throws IOException {
        String file = "day32test.txt";
        String[] allLines = FileUtil.readFile(file);

        String[] w1 = allLines[6].split(",");
        String[] w2 = allLines[7].split(",");

        Day3Part2Puzzle puzzle = new Day3Part2Puzzle();

        Point[] wire1 = puzzle.parseWire(w1);
        wire1 = puzzle.removeOriginPoint(wire1);
        //wire1 = puzzle.removeDuplicatePoints(wire1);

        Point[] wire2 = puzzle.parseWire(w2);
        wire2 = puzzle.removeOriginPoint(wire2);
        //wire2 = puzzle.removeDuplicatePoints(wire2);
        /*
        for(Point point : wire1){
            System.out.println(point);
        } */

        Hashtable<String, Point> pointTable1 = puzzle.convertToPointTable(wire1);
        Hashtable<String, Point> pointTable2 = puzzle.convertToPointTable(wire2);

        Point[] crossingPoints = puzzle.extractCrossingPoints(pointTable1, pointTable2);
        crossingPoints = puzzle.removeOriginPoint(crossingPoints);

        int result =  puzzle.extractShortestWireLength(crossingPoints, wire1, wire2);
        System.out.println("Day3Part2PuzzleTest example3 " + Integer.toString(result));
        assertEquals(30, result);
    }
}