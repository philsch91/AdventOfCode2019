package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.Point;
import at.fhcampuswien.adventofcode.util.FileUtil;

import java.io.IOException;
import java.util.*;

public class Day3Part2Puzzle extends Puzzle {

    @Override
    public Object solve() throws IOException {
        String file = "day3.txt";
        String[] allLines = FileUtil.readFile(file);

        String[] w1 = allLines[0].split(",");
        String[] w2 = allLines[1].split(",");

        Point[] wire1 = parseWire(w1);
        wire1 = removeOriginPoint(wire1);

        Point[] wire2 = parseWire(w2);
        wire2 = removeOriginPoint(wire2);

        Hashtable<String, Point> pointTable1 = convertToPointTable(wire1);
        Hashtable<String, Point> pointTable2 = convertToPointTable(wire2);

        Point[] crossingPoints = extractCrossingPoints(pointTable1, pointTable2);
        crossingPoints = removeOriginPoint(crossingPoints);

        return extractShortestWireLength(crossingPoints, wire1, wire2);
    }

    public int extractShortestWireLength(Point[] crossingPoints, Point[]... wires){
        int pathCount = wires.length;

        if (pathCount <= 1){
            return -1;
        }

        int shortestLength = -1;

        for(Point crossingPoint : crossingPoints){
            int i = 0;
            int length = 0;

            while(i < pathCount){
                for(Point point : wires[i]){
                    length++;
                    if(point.equals(crossingPoint)){
                        break;
                    }
                }
                i++;
            }

            if(length < shortestLength || shortestLength == -1){
                shortestLength = length;
            }
        }

        return shortestLength;
    }

    public Point[] removeDuplicatePointsWithTable(Point[] points){
        Hashtable<String, Integer> pointCountTable = convertToPointCountTable(points);
        ArrayList<Point> pointList = new ArrayList<>();

        for(Point point : points){
            String key = point.toString();
            if(pointCountTable.containsKey(key) && pointCountTable.get(key) == 1){
                pointList.add(point);
            }
        }
        return pointList.toArray(new Point[0]);
    }

    public Point[] removeDuplicatePoints(Point[] points){
        ArrayList<Point> pointList = new ArrayList<>();

        for(Point point : points){
            int count = 0;
            for(Point ipoint : points){
                if(ipoint.equals(point)){
                    count++;
                }
            }

            if(point.x != 0 && point.y != 0 && count == 1){
                pointList.add(point);
            }
        }
        return pointList.toArray(new Point[0]);
    }

    public Point[] removeOriginPoint(Point[] points){
        List<Point> pointList = new ArrayList<>();
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

    public Hashtable<String, Integer> convertToPointCountTable(Point[] points){
        Hashtable<String, Integer> pointTable = new Hashtable<>();

        for(Point point : points){
            int count = 1;
            String key = point.toString();
            if(pointTable.containsKey(key)){
                count += pointTable.get(key);
            }
            pointTable.put(point.toString(), count);
        }
        return pointTable;
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
}
