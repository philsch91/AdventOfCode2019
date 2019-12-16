package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.Point;
import at.fhcampuswien.adventofcode.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3Part10Puzzle extends Puzzle {

    @Override
    public Object solve() throws IOException {
        String file = "day3.txt";
        String[] allLines = FileUtil.readFile(file);

        String[] w1 = allLines[0].split(",");
        String[] w2 = allLines[1].split(",");

        Point[] wire1 = parseWire(w1);
        Point[] wire2 = parseWire(w2);

        System.out.println("wires parsed");

        Point[] intersectionPoints = extractIntersectionPoints(wire1, wire2);
        intersectionPoints = removeOriginPoint(intersectionPoints);

        int leastDistance = calculateManhattanDistance(intersectionPoints[0]);

        for(Point p : intersectionPoints){
            System.out.println(p);
            int manhattanDistance  = calculateManhattanDistance(p);

            if (manhattanDistance < leastDistance) {
                leastDistance = manhattanDistance;
            }
        }

        return leastDistance;
    }

    public static int calculateManhattanDistance(Point point){
        return Math.abs(point.x) + Math.abs(point.y);
    }

    public static Point[] removeOriginPoint(Point[] points){
        ArrayList<Point> pointList = new ArrayList<>();
        for(Point point: points){
            if(point.x != 0 && point.y != 0){
                pointList.add(point);
            }
        }
        return pointList.toArray(new Point[0]);
    }

    public static Point[] extractIntersectionPoints(Point[]... pointArrays){
        List<Point> pointList = new ArrayList<Point>();
        Integer pathCount = pointArrays.length;

        if (pathCount <= 1){
            return pointList.toArray(new Point[0]);
        }

        Integer i = 0;
        Integer k = 0;
        System.out.println(pointArrays[i].length);

        for(Point point1 : pointArrays[i]){
            boolean exists = true;
            Integer j = i + 1;

            while(j < pathCount && exists){
                boolean equals = false;
                for(Point point2 : pointArrays[j]){
                    if (point2.equals(point1)){
                        equals = true;
                    }
                }
                exists = equals;
                j++;
            }

            if (exists){
                pointList.add(point1);
            }

            System.out.println(k);
            k++;
        }

        return pointList.toArray(new Point[0]);
    }

    public static Point[] parseWire(String[] wire){
        //Point[] points = new Point[wire.length+1];
        List<Point> pointList = new ArrayList<Point>();

        int x = 0;
        int y = 0;

        //points[0] = new Point(x, y);
        //pointList.add(new Point(x, y));

        int i = 1;
        for(String str : wire){
            String direction = str.substring(0,1);
            Integer length = Integer.parseInt(str.substring(1));

            Integer xi = x;
            Integer yi = y;

            if (direction.equals("R")){
                x += length;
            } else if (direction.equals("D")){
                y -= length;
            } else if (direction.equals("L")){
                x -= length;
            } else if (direction.equals("U")){
                y += length;
            }

            Integer maxX = Math.max(x, xi);
            Integer minX = Math.min(x, xi);
            Integer maxY = Math.max(y, yi);
            Integer minY = Math.min(y, yi);

            while(minX <= maxX){
                Point point = new Point(minX, y);
                //System.out.println(point);
                pointList.add(point);
                minX++;
            }

            while(minY <= maxY){
                Point point = new Point(x, minY);
                //System.out.println(point);
                pointList.add(point);
                minY++;
            }

            //Point point = new Point(x, y);
            //System.out.println(point);

            //points[i] = point;
            //pointList.add(point);
            //i++;
        }

        return pointList.toArray(new Point[0]);
    }
}
