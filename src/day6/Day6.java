package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day6 {
    public static void main(String[] args) throws IOException {
        final List<Integer> input = parseInput("src/day6/input.txt");

        int cycles = 0;
        boolean foundDup = false;
        boolean foundDupAgain = false;
        final List<Integer> dup = new ArrayList<>();
        final Set<List<Integer>> seen = new HashSet<>();

        while (!(foundDup && foundDupAgain)) {
            cycles++;
            redistribute(input);

            if (input.equals(dup)) {
                foundDupAgain = true;
                System.out.println("Saw input equalling dup at [" + cycles + "] num cycles");
            }

            if (seen.contains(input)) {
                if (dup.size() == 0) {
                    System.out.println("Saw first dup at [" + cycles + "] num cycles");
                    dup.addAll(input);
                }
                foundDup = true;
            }

            seen.add(input);
        }
    }

    private static void redistribute(
            final List<Integer> banks
    ) {
        final int max = Collections.max(banks);

        int indexOfMax = banks.indexOf(max);

        banks.set(indexOfMax, 0);

        int currIndex = 0;
        if (!(indexOfMax + 1 == banks.size())) {
            currIndex = indexOfMax + 1;
        }

        for (int i = 0; i < max; i++) {
            int value = banks.get(currIndex);

            banks.set(currIndex, value + 1);

            if (currIndex + 1 == banks.size()) {
                currIndex = 0;
            } else {
                currIndex++;
            }
        }
    }

    private static List<Integer> parseInput(final String filename) throws IOException {
        List<Integer> parsedInput = new ArrayList<>();

        final Stream<String> lines = Files.lines(Paths.get(filename));

        lines.forEach(line -> {
            String[] splitLine = line.split("\\t");
            Arrays.stream(splitLine)
                    .map(Integer::valueOf)
                    .forEach(parsedInput::add);
        });

        return parsedInput;
    }
}
