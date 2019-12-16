package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.util.FileUtil;

import java.io.IOException;
import java.util.Arrays;

public class Day2Part1Puzzle extends Puzzle {

    @Override
    public Object solve() throws IOException {
        //Position1 = 12
        //Position2 = 2
        String file = "2.txt";

        String[] allLines = FileUtil.readFile(file);
        String line = FileUtil.combine(allLines);

        String[] tmp = line.split(",");
        int[] numbers = new int[tmp.length];

        for (int i = 0; i < tmp.length; i++) {
            numbers[i] = Integer.parseInt(tmp[i]);
        }

        numbers = intcode(numbers);
        int result = numbers[0];

        return result;
    }

    public int[] intcode(int[] numbers) {
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
}
