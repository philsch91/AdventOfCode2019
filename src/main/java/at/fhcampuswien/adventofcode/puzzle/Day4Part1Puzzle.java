package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.util.FileUtil;

import java.io.IOException;

public class Day4Part1Puzzle extends Puzzle {

    @Override
    public Object solve() throws IOException {
        String file = "day4.txt";
        String[] allLines = FileUtil.readFile(file);

        String[] bounds = allLines[0].split("-");
        //String lowerBound = bounds[0];
        //String upperBound = bounds[1];
        int lowerBound = Integer.parseInt(bounds[0]);
        int upperBound = Integer.parseInt(bounds[1]);
        //int length = String.valueOf(upperBound).length();

        int validPasswordCount = 0;
        for(int i = lowerBound + 1; i < upperBound; i++){
            String number = String.valueOf(i);
            if(countTwoAdjacentDigits(number) > 0 && countNotIncreasingDigits(number) == 0){
                validPasswordCount++;
            }
        }

        return validPasswordCount;
    }

    public int countTwoAdjacentDigits(String number){
        if(number == null || number.length() < 2){
            return 0;
        }

        //String[] numberChars = number.split("");
        int count = 0;
        for(int i = 1; i < number.length(); i++){
            if(number.charAt(i) == number.charAt(i-1)){
                count++;
            }
        }

        return count;
    }

    public int countNotIncreasingDigits(String number){
        if(number == null || number.length() < 2){
            return 0;
        }

        int count = 0;
        for(int i = 1; i < number.length(); i++){
            int digit = number.charAt(i) - '0';
            int lastDigit = number.charAt(i-1) - '0';
            if(digit < lastDigit){
                count++;
            }
        }

        return count;
    }
}
