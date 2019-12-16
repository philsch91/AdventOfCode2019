package at.fhcampuswien.adventofcode.puzzle;

import java.io.IOException;

public abstract class Puzzle {
    public String inputfile;
    public abstract Object solve() throws IOException;
}
