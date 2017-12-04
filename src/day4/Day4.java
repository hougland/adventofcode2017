package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {
    public static void main(String[] args) throws IOException {
        final List<List<String>> input = parseInput("src/day4/input.txt");

        int numValid = 0;

        for (final List<String> line : input) {
            final Set<List<String>> passphrases = new HashSet<>();

            boolean foundAnagram = false;

            for (final String passphrase : line) {
                final List<String> alphabetized = Arrays.stream(passphrase.split(""))
                        .sorted()
                        .collect(Collectors.toList());

                if (passphrases.contains(alphabetized)) {
                    foundAnagram = true;
                } else {
                    passphrases.add(alphabetized);
                }

//                part1
//                if (passphrases.contains(passphrase)) {
//                    foundDup = true;
//                } else {
//                    passphrases.add(passphrase);
//                }
            }

            if (!foundAnagram) {
                numValid++;
            }
        }

        System.out.println(numValid);
    }

    private static List<List<String>> parseInput(final String filename) throws IOException {
        final List<List<String>> parsedInput = new ArrayList<>();

        final Stream<String> lines = Files.lines(Paths.get(filename));

        lines.forEach(line -> parsedInput.add(Arrays.asList(line.split(" "))));

        return parsedInput;
    }
}
