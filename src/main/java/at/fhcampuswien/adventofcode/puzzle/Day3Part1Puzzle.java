package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.Point;
import at.fhcampuswien.adventofcode.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Day3Part1Puzzle extends Puzzle {

    @Override
    public Object solve() throws IOException {
        String file = "day3.txt";
        String[] allLines = FileUtil.readFile(file);

        String[] w1 = allLines[0].split(",");
        String[] w2 = allLines[1].split(",");
        /*
        int[] bounds = readBounds(w1);
        System.out.println("minX: " + bounds[0]);
        System.out.println("maxX: " + bounds[1]);
        System.out.println("minY: " + bounds[2]);
        System.out.println("maxY: " + bounds[3]);

        bounds = readBounds(w2);
        System.out.println("minX: " + bounds[0]);
        System.out.println("maxX: " + bounds[1]);
        System.out.println("minY: " + bounds[2]);
        System.out.println("maxY: " + bounds[3]);
        */
        Point[] wire1 = parseWire(w1);
        Point[] wire2 = parseWire(w2);

        //System.out.println(wire1.length);
        //System.out.println(wire2.length);

        Hashtable<String, Point> pointTable1 = convertToPointTable(wire1);
        Hashtable<String, Point> pointTable2 = convertToPointTable(wire2);

        Point[] crossingPoints = extractCrossingPoints(pointTable1, pointTable2);
        crossingPoints = removeOriginPoint(crossingPoints);

        int leastDistance = calculateManhattanDistance(crossingPoints[0]);

        for(Point p : crossingPoints){
            //System.out.println(p);
            int manhattanDistance  = calculateManhattanDistance(p);

            if (manhattanDistance < leastDistance) {
                leastDistance = manhattanDistance;
            }
        }

        return leastDistance;
    }

    public int calculateManhattanDistance(Point point){
        return Math.abs(point.x) + Math.abs(point.y);
    }

    public Point[] removeOriginPoint(Point[] points){
        ArrayList<Point> pointList = new ArrayList<>();
        for(Point point : points){
            if(point.x != 0 || point.y != 0){
                pointList.add(point);
            }
        }
        return pointList.toArray(new Point[0]);
    }

    public Point[] extractCrossingPoints(Map<String, Point>... pointTables){
        List<Point> pointList = new ArrayList<Point>();
        Integer pathCount = pointTables.length;

        if (pathCount <= 1){
            return pointList.toArray(new Point[0]);
        }

        Integer i = 0;
        Integer k = 0;
        //System.out.println(pointTables[i].size());  //debug

        for(String key : pointTables[i].keySet()){
            boolean contains = true;    //equals
            Integer j = i + 1;

            while(j < pathCount && contains){
                contains = pointTables[j].containsKey(key);
                j++;
            }

            if (contains){
                pointList.add(pointTables[0].get(key));
            }

            //System.out.println(k);  //debug
            k++;
        }

        return pointList.toArray(new Point[0]);
    }

    public Hashtable<String, Point> convertToPointTable(Point[] points){
        Hashtable<String, Point> pointTable = new Hashtable<>();

        for(Point point : points){
            //String key = Integer.toString(point.x) + ";" + Integer.toString(point.y);
            //pointTable.add(point.toString());
            pointTable.put(point.toString(), point);
        }
        return pointTable;
    }

    public Point[] parseWire(String[] wire){
        List<Point> pointList = new ArrayList<Point>();

        int x = 0;
        int y = 0;

        for(String str : wire){
            String direction = str.substring(0,1);
            Integer length = Integer.parseInt(str.substring(1));

            Integer newX = x;
            Integer newY = y;

            if (direction.equals("R")){
                newX += length;
            } else if (direction.equals("D")){
                newY -= length;
            } else if (direction.equals("L")){
                newX -= length;
            } else if (direction.equals("U")){
                newY += length;
            }

            /*
            Integer maxX = Math.max(x, newX);
            Integer minX = Math.min(x, newX);
            Integer maxY = Math.max(y, newY);
            Integer minY = Math.min(y, newY);

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
            */

            while(newX > x){
                Point point = new Point(x, y);
                //System.out.println(point);
                pointList.add(point);
                x++;
            }

            while(newX < x){
                Point point = new Point(x, y);
                //System.out.println(point);
                pointList.add(point);
                x--;
            }

            while(newY > y){
                Point point = new Point(x, y);
                //System.out.println(point);
                pointList.add(point);
                y++;
            }

            while(newY < y){
                Point point = new Point(x, y);
                //System.out.println(point);
                pointList.add(point);
                y--;
            }
        }

        return pointList.toArray(new Point[0]);
    }

    public int[] readBounds(String[]... wires){
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;

        int x = 0;
        int y = 0;

        for(String[] wire : wires){
            for(String str : wire){
                String direction = str.substring(0,1);
                Integer length = Integer.parseInt(str.substring(1));

                if (direction.equals("R")){
                    x += length;
                } else if (direction.equals("D")){
                    y -= length;
                } else if (direction.equals("L")){
                    x -= length;
                } else if (direction.equals("U")){
                    y += length;
                }

                minX = Math.min(x, minX);
                maxX = Math.max(x, maxX);

                minY = Math.min(y, minY);
                maxY = Math.max(y, maxY);
            }
        }

        int[] bounds = new int[4];
        bounds[0] = minX;
        bounds[1] = maxX;
        bounds[2] = minY;
        bounds[3] = maxY;

        return bounds;
    }
}
