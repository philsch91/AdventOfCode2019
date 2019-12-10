package at.fhcampuswien;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static String[] readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        //String allLines = "";
        Integer number = 0;

        List<String> lineList = new ArrayList<String>();

        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            lineList.add(line);
        }

        //String[] lines = new int[lineList.size()];
        String[] lines = lineList.toArray(new String[0]);

        return lines;
    }

    public static String combine(String[] lines){
        String string = "";

        for (String line : lines){
            string += line;
        }

        return string;
    }
}
