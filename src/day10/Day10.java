package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10 {
    public static void main(String[] args) throws IOException {
        final List<Byte> input = parseInput("src/day10/input.txt");
        System.out.println("Lengths: " + input);

        final int listSize = 256;

        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            list.add(i);
        }

        int roundsCount = 0;
        int skipSize = 0;
        int selectedIndex = 0;

        while (roundsCount < 64) {
            for (int i = 0; i < input.size(); i++) {
                final int length = input.get(i);

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

            roundsCount++;
        }

        final List<Integer> denseHash = getDenseHashElements(list);

        final List<String> hexValues = new ArrayList<>();

        denseHash.stream()
                .map(Integer::toHexString)
                .map(num -> num.length() == 2 ? num : "0" + num)
                .forEach(hexValues::add);

        System.out.println("Dense hash: " + denseHash);
        System.out.println("Hex string: " + String.join("", hexValues));
    }

    private static List<Integer> getDenseHashElements(
            final List<Integer> spareHash
    ) {
        final List<Integer> denseHashes = new ArrayList<>();

        for (int i = 0; i < 256; i += 16) {
            int denseHashElement = getDenseHashElement(spareHash.subList(i, i + 16));
            denseHashes.add(denseHashElement);
        }

        return denseHashes;
    }

    private static int getDenseHashElement(
            final List<Integer> spareHashElements
    ) {
        if (spareHashElements.size() == 1) {
            return spareHashElements.get(0);
        }

        return spareHashElements.get(0) ^ getDenseHashElement(spareHashElements.subList(1, spareHashElements.size()));
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
            int newIndex = (currentIndex + moveForwardThisMany) - listSize;
            if (newIndex < listSize) {
                return newIndex;
            } else {
                return getNewIndex(newIndex, listSize, 0, 0);
            }
        }
    }

    private static List<Byte> parseInput(final String filename) throws IOException {
        final List<Byte> parsedInput = new ArrayList<>();

        final byte[] encoded = Files.readAllBytes(Paths.get(filename));

        for (byte anEncoded : encoded) {
            parsedInput.add(anEncoded);
        }

        final byte[] addedSalt = {17, 31, 73, 47, 23};
        for (byte anAddedSalt : addedSalt) {
            parsedInput.add(anAddedSalt);
        }

        return parsedInput;
    }
}
