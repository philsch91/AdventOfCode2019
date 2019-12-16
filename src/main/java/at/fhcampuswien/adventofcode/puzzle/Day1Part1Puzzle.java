package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.util.FileUtil;

import java.io.IOException;

public class Day1Part1Puzzle extends Puzzle {

    @Override
    public Object solve() throws IOException {
        String file = "1.txt";

        Integer number = 0;

        String[] lines = FileUtil.readFile(file);

        for(String line : lines){
            //System.out.println(line);
            Integer mass = Integer.parseInt(line);
            number += calculateMass(mass);
        }

        return number;
    }

    public Integer calculateMass(Integer mass) {
        mass = mass / 3;
        mass = mass - 2;
        return mass;
    }
}
