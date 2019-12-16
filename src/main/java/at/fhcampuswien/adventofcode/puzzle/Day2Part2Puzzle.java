package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.util.FileUtil;

import java.io.IOException;
import java.util.Arrays;

public class Day2Part2Puzzle extends Puzzle {
    @Override
    public Object solve() throws IOException {
        //Position1 = 12
        //Position2 = 2
        String file = "2.txt";

        String[] allLines = FileUtil.readFile(file);
        String line = FileUtil.combine(allLines);

        //System.out.println(line);     //debug

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
        return answer;
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
