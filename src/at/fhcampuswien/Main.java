package at.fhcampuswien;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");
        //exam1();
        //exam2();
        //exam3();
        exam4();
    }

    public static void exam4() throws IOException {
        //Position1 = 12
        //Position2 = 2
        String file = "2.txt";

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        String allLines = "";
        Integer number = 0;

        while ((line = reader.readLine()) != null) {
            allLines = allLines + line;
        }

        System.out.println(allLines);

        String[] tmp = allLines.split(",");
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

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        String allLines = "";
        Integer number = 0;

        while ((line = reader.readLine()) != null) {
            allLines = allLines + line;
        }

        System.out.println(allLines);

        String[] tmp = allLines.split(",");
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

        System.out.println("result exam3:" + Integer.toString(numbers[0]));

        reader.close();
    }

    public static void exam1() throws IOException {
        String file = "1.txt";

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        Integer number = 0;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            Integer mass = Integer.parseInt(line);
            number += calculateMass(mass);
        }

        reader.close();

        System.out.println("result exam1: " + Integer.toString(number));
    }

    public static void exam2() throws IOException {
        String file = "1.txt";

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        Integer number = 0;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            Integer mass = Integer.parseInt(line);
            System.out.println("fuel: " + calculateFuel(mass));
            number = number + calculateFuel(mass);
        }

        reader.close();

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

    public static Integer calculateMass(Integer mass) {
        mass = mass / 3;
        mass = mass - 2;
        return mass;
    }

}
