package at.fhcampuswien.adventofcode;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");
        //exam1();
        //exam2();
        //exam3();
        //exam4();
        exam5();
    }

    public static void exam5() throws IOException {
        String file = "day3.txt";
        String[] allLines = FileUtil.readFile(file);

        String[] w1 = allLines[0].split(",");
        String[] w2 = allLines[1].split(",");

        int x = 0;
        int y = 0;

        Point[] wire1 = parseWire(w1);
        Point[] wire2 = parseWire(w2);

        System.out.println("wires parsed");
        Point[] intersectionPoints = extractIntersectionPoints(wire1, wire2);

        Integer leastDistance = calculateManhattanDistance(intersectionPoints[1]);

        for(Point p : intersectionPoints){
            System.out.println(p);
            if(p.x == 0 && p.y == 0) continue;
            Integer manhattanDistance  = calculateManhattanDistance(p);
            if (manhattanDistance < leastDistance) {
                leastDistance = manhattanDistance;
            }
        }

        System.out.println("result exam 5: " + Integer.toString(leastDistance));
    }

    public static int calculateManhattanDistance(Point point){
        return Math.abs(point.x) + Math.abs(point.y);
    }

    public static Point[] extractIntersectionPoints(Point[]... pointArrays){
        List<Point> pointList = new ArrayList<Point>();
        Integer pathCount = pointArrays.length;

        if (pathCount <= 1){
            return pointList.toArray(new Point[0]);
        }

        Integer i = 0;
        Integer j = 0;
        System.out.println(pointArrays[i].length);

        for(Point point1 : pointArrays[i]){
            boolean exists = true;
            i = 0;

            while((i+1) < pathCount && exists){
                boolean equals = false;
                for(Point point2 : pointArrays[i+1]){
                    if (point2.equals(point1)){
                        equals = true;
                    }
                }
                exists = equals;
                i++;
            }

            if (exists){
                pointList.add(point1);
            }

            System.out.println(j);
            j++;
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
        for(String str: wire){
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

    public static void exam4() throws IOException {
        //Position1 = 12
        //Position2 = 2
        String file = "2.txt";

        String[] allLines = FileUtil.readFile(file);
        String line = FileUtil.combine(allLines);

        System.out.println(line);

        String[] tmp = line.split(",");
        int[] numbers = new int[tmp.length];

        for (int i = 0; i < tmp.length; i++) {
            numbers[i] = Integer.parseInt(tmp[i]);
        }

        Integer result = 19690720;
        boolean found = false;

        for (int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                numbers[1] = noun;
                numbers[2] = verb;

                int[] res = intcode(numbers);

                if (res[0] == result) {
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        //100 * noun + verb
        int answer = (numbers[1] * 100 + numbers[2]);
        System.out.println("exam4: " + answer);
    }

    public static int[] intcode(int[] numbers) {
        int[] num = Arrays.copyOf(numbers, numbers.length);
        int index = 0;
        Integer opcode = num[index];

        while (opcode != 99) {
            int address1 = num[index + 1];
            int address2 = num[index + 2];
            int resultAddress = num[index + 3];
            int result = 0;

            if (opcode == 1) {
                result = num[address1] + num[address2];
            } else if (opcode == 2) {
                result = num[address1] * num[address2];
            }

            num[resultAddress] = result;
            index += 4;
            opcode = num[index];
        }
        return num;
    }

    public static void exam3() throws IOException {
        //Position1 = 12
        //Position2 = 2
        String file = "2.txt";

        String[] allLines = FileUtil.readFile(file);
        String line = FileUtil.combine(allLines);

        String[] tmp = line.split(",");
        Integer[] numbers = new Integer[tmp.length];

        for (int i = 0; i < tmp.length; i++) {
            numbers[i] = Integer.parseInt(tmp[i]);
        }

        Integer index = 0;
        Integer opcode = numbers[index];

        while (opcode != 99) {
            Integer address1 = numbers[index + 1];
            Integer address2 = numbers[index + 2];
            Integer resultaddress = numbers[index + 3];

            Integer result = 0;

            if (opcode == 1) {
                result = numbers[address1] + numbers[address2];
            } else if (opcode == 2) {
                result = numbers[address1] * numbers[address2];
            }

            numbers[resultaddress] = result;
            index = index + 4;
            opcode = numbers[index];
        }

        System.out.println("result exam3: " + Integer.toString(numbers[0]));
    }

    public static void exam2() throws IOException {
        String file = "1.txt";

        Integer number = 0;

        String[] lines = FileUtil.readFile(file);

        for(String line : lines){
            System.out.println(line);
            Integer mass = Integer.parseInt(line);
            System.out.println("fuel: " + calculateFuel(mass));
            number = number + calculateFuel(mass);
        }

        System.out.println("result exam2: " + Integer.toString(number));
    }

    public static Integer calculateFuel(Integer fuel) {
        fuel = fuel / 3;
        fuel = fuel - 2;

        if (fuel < 0) {
            fuel = 0;
        }

        if (fuel > 0) {
            fuel = fuel + calculateFuel(fuel);
        }

        return fuel;
    }

    public static void exam1() throws IOException {
        String file = "1.txt";

        Integer number = 0;

        String[] lines = FileUtil.readFile(file);

        for(String line : lines){
            System.out.println(line);
            Integer mass = Integer.parseInt(line);
            number += calculateMass(mass);
        }

        System.out.println("result exam1: " + Integer.toString(number));
    }

    public static Integer calculateMass(Integer mass) {
        mass = mass / 3;
        mass = mass - 2;
        return mass;
    }
}
