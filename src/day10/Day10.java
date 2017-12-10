package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10 {
    public static void main(String[] args) throws IOException {
        final List<Integer> input = parseInput("src/day10/input.txt");
        final int listSize = 256;

        System.out.println(input);

        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(i);
        }

        int skipSize = 0;
        int selectedIndex = 0;

        for (int i = 0; i < input.size(); i++) {
            final int length = input.get(i);
            if (length > listSize) {
                continue;
            }

            final List<Integer> sublist;

            if (selectedIndex + length < list.size()) {
                sublist = list.subList(selectedIndex, selectedIndex + length);
                Collections.reverse(sublist);
            } else {
                // wrap
                final List<Integer> first = list.subList(selectedIndex, list.size());
                final int difference = list.size() - 1 - selectedIndex;
                final List<Integer> second = list.subList(0, length - difference - 1);
                sublist = Stream.concat(first.stream(), second.stream()).collect(Collectors.toList());
                Collections.reverse(sublist);

                int counter = 0;

                // first half
                for (int j = selectedIndex; j < list.size(); j++) {
                    list.set(j, sublist.get(counter));
                    counter++;
                }

                // second half
                for (int j = 0; j < length - difference - 1; j++) {
                    list.set(j, sublist.get(counter));
                    counter++;
                }
            }

            selectedIndex = getNewIndex(selectedIndex, listSize, length, skipSize);
            skipSize++;
        }

        System.out.println();
        System.out.println();
        System.out.println(list.get(0) * list.get(1));
    }

    private static int getNewIndex(
            final int currentIndex,
            final int listSize,
            final int length,
            final int skipSize
    ) {
        final int moveForwardThisMany = length + skipSize;

        if (moveForwardThisMany + currentIndex < listSize) {
            return moveForwardThisMany + currentIndex;
        } else {
            return (currentIndex + moveForwardThisMany) - listSize;
        }
    }

    private static List<Integer> parseInput(final String filename) throws IOException {
        List<Integer> parsedInput = new ArrayList<>();

        final Stream<String> lines = Files.lines(Paths.get(filename));

        lines.forEach(line -> {
            String[] splitLine = line.split(",");
            Arrays.stream(splitLine)
                    .map(Integer::valueOf)
                    .forEach(parsedInput::add);
        });

        return parsedInput;
    }
}
