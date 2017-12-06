package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day6 {
    public static void main(String[] args) throws IOException {
        List<Integer> input = parseInput("src/day6/input.txt");

        int cycles = 0;
        boolean foundDup = false;
        boolean foundDupAgain = false;
        boolean setDup = false;
        List<Integer> dup = new ArrayList<>();
        final Set<List<Integer>> seen = new HashSet<>();

        while (!foundDup || !foundDupAgain) {
            cycles++;
            input = redistribute(input);

            if (input.equals(dup)) {
                foundDupAgain = true;
                continue;
            }

            if (seen.contains(input)) {
                if (!setDup) {
                    System.out.println(cycles);

                    dup.addAll(input);
                    setDup = true;
                }
                foundDup = true;
            }

            seen.add(input);
        }

        System.out.println("final " + cycles);
    }

    private static List<Integer> redistribute(
            final List<Integer> banks
    ) {
        final List<Integer> newBanks = banks;

        final int max = Collections.max(banks);

        int indexOfMax = banks.indexOf(max);

        newBanks.set(indexOfMax, 0);

        int currIndex;
        if (indexOfMax + 1 == newBanks.size()) {
            currIndex = 0;
        } else {
            currIndex = indexOfMax + 1;
        }

        for (int i = 0; i < max; i++) {
            int value = newBanks.get(currIndex);

            newBanks.set(currIndex, value + 1);

            if (currIndex + 1 == newBanks.size()) {
                currIndex = 0;
            } else {
                currIndex++;
            }
        }

        return newBanks;
    }


    private static List<Integer> parseInput(final String filename) throws IOException {
        List<Integer> parsedInput = new ArrayList<>();

        final Stream<String> lines = Files.lines(Paths.get(filename));

        lines.forEach(line -> {
                    System.out.println(line);
                    String[] splitLine = line.split("\\t");

                    Arrays.stream(splitLine)
                            .map(Integer::valueOf)
                            .forEach(item -> parsedInput.add(item));
        }
        );

        return parsedInput;
    }
}
