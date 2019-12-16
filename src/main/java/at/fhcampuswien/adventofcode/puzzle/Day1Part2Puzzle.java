package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.util.FileUtil;

import java.io.IOException;

public class Day1Part2Puzzle extends Puzzle {

    @Override
    public Object solve() throws IOException {
        String file = "1.txt";

        Integer number = 0;

        String[] lines = FileUtil.readFile(file);

        for(String line : lines){
            //System.out.println(line);
            Integer mass = Integer.parseInt(line);
            Integer fuel = calculateFuel(mass);
            //System.out.println("fuel: " + fuel);
            number += fuel;
        }

        return number;
    }

    public Integer calculateFuel(Integer fuel) {
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
}
