package at.fhcampuswien.adventofcode.puzzle;

import at.fhcampuswien.adventofcode.util.FileUtil;

import java.io.IOException;

public class Day4Part2Puzzle extends Puzzle {

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
            if(countAdjacentDigits(number, 2) > 0 && countNotIncreasingDigits(number) == 0){
                validPasswordCount++;
            }
        }

        return validPasswordCount;
    }

    public int countAdjacentDigits(String number, int maxLength){
        if(number == null || number.length() < 2){
            return 0;
        }
        //111122
        int count = 0;
        for(int i = 0; i < number.length(); i++){
            //System.out.println("i " + i);
            char c = number.charAt(i);
            int length = 1;
            int j = i + 1;

            while(j < number.length() && c == number.charAt(j)){
                i = j;
                length++;
                j++;
                //System.out.println("j " + j);
            }

            if(length == maxLength){
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
