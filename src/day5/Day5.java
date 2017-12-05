package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day5 {
    public static void main(String[] args) throws IOException {
        final List<Integer> input = parseInput("src/day5/input.txt");

        int numSteps = 0;
        int currentIndex = 0;

        while (currentIndex < input.size()) {
            final int value = input.get(currentIndex);

            if (value >= 3) {
                input.set(currentIndex, value - 1);
            } else {
                input.set(currentIndex, value + 1);
            }

            numSteps++;
            currentIndex = currentIndex + value;
        }

        System.out.println(numSteps);
    }

    private static List<Integer> parseInput(final String filename) throws IOException {
        final List<Integer> parsedInput = new ArrayList<>();

        final Stream<String> lines = Files.lines(Paths.get(filename));

        lines.forEach(line -> parsedInput.add(Integer.valueOf(line)));

        return parsedInput;
    }
}
