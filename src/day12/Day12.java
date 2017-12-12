package day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day12 {
    public static void main(String[] args) throws IOException {
        final Map<Integer, Set<Integer>> input = parseInput("src/day12/input.txt");

        final FindNumPipes findNumPipes = new FindNumPipes(input);

        int numGroups = 0;

        while (input.size() > 0) {
            int value = input.keySet().stream()
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);

            final Set<Integer> group = findNumPipes.getConnections(value);
            group.forEach(input::remove);
            numGroups++;
        }

        System.out.println(numGroups);
    }

    private static Map<Integer, Set<Integer>> parseInput(final String filename) throws IOException {
        final Map<Integer, Set<Integer>> parsedInput = new HashMap<>();

        final Stream<String> lines = Files.lines(Paths.get(filename));

        lines.forEach(line -> {
            line = line.replaceAll(",", "");
            String[] splitLine = line.split(" ");
            int self = Integer.valueOf(splitLine[0]);

            if (!parsedInput.containsKey(self)) {
                parsedInput.put(self, new HashSet<>());
            }

            for (int i = 2; i < splitLine.length; i++) {
                String string = splitLine[i];
                string = string.replaceAll(",", "");
                final int integer = Integer.valueOf(string);

                parsedInput.get(self).add(integer);

                // add bidirectionally ---> is this needed?
                if (!parsedInput.containsKey(integer)) {
                    parsedInput.put(integer, new HashSet<>());
                }

                parsedInput.get(integer).add(self);
            }

        });

        return parsedInput;
    }
}
