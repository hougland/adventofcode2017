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
        int farthestDistance = 794; // answer to part 1

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

            int distance = getDistance(x, y);
            if (distance > farthestDistance) {
                farthestDistance = distance;
            }
        }

        System.out.println("X: " + x + " Y: " + y);

        System.out.println("Farthest distance: " + farthestDistance);
    }

    private static int getDistance(
            final int x,
            final int y
    ) {
        if (x == 0) {
            return Math.abs(y);
        }

        if (y == 0) {
            return Math.abs(x);
        }

        if ((x > 0 && y > 0) || (x < 0 && y < 0)) {
            // quadrant 1 or 3
            return Math.abs(x) + Math.abs(y);
        }

        int steps = 0;
        int currX = 0;
        int currY = 0;

        if (x > 0 && y < 0) {
            // quadrant 2
            // go diagonally down as long as possible
            while (currX < x && currY > y) {
                steps++;
                currX++;
                currY--;
            }

            // then over
            while (currX == x && currY != y) {
                steps++;
                currY--;
            }

            while (currY == y && currX != x) {
                steps++;
                currX++;
            }

            return steps;
        }

        if (x < 0 && y > 0) {
            // quadrant 4
            // go diagonally down as long as possible
            while (currX > x && currY < y) {
                steps++;
                currX--;
                currY++;
            }

            // then over
            while (currX == x && currY != y) {
                steps++;
                currY++;
            }

            while (currY == y && currX != x) {
                steps++;
                currX--;
            }

            return steps;
        }

        return 0;
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
