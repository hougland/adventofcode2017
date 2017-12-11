package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day11 {
    public static void main(String[] args) throws IOException {
        final List<String> input = parseInput("src/day11/input.txt");
        System.out.println(input);

        int x = 0;
        int y = 0;

        for (String instruction : input) {
            switch (instruction) {
                case "n":
                    y++;
                    break;
                case "s":
                    y--;
                    break;
                case "ne":
                    x++;
                    break;
                case "se":
                    x++;
                    y--;
                    break;
                case "sw":
                    x--;
                    break;
                case "nw":
                    x--;
                    y++;
                    break;
            }
        }

        System.out.println("X: " + x + " Y: " + y);
        
        int steps = 0;
        int currX = 0;
        int currY = 0;

        while (currX < x && currY > y) {
            steps++;
            currX++;
            currY--;
        }

        System.out.println("CurrX: " + currX + " CurrY: " + currY);
        System.out.println("Steps: " + steps);

        while (currY > y) {
            steps++;
            currY--;
        }

        System.out.println("Steps: " + steps);
    }

    private static List<String> parseInput(final String filename) throws IOException {
        final List<String> parsedInput = new ArrayList<>();

        final Stream<String> lines = Files.lines(Paths.get(filename));

        lines.forEach(line -> {
            String[] splitLine = line.split(",");
            Arrays.stream(splitLine)
                    .forEach(parsedInput::add);
        });
        return parsedInput;
    }
}
