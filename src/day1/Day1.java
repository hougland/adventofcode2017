package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {
    public static void main(String[] args) throws IOException {
        final List<Integer> digits = parseInput("src/day1/input.txt");

        // part 2
        final int stepsToHalfway = digits.size() / 2;
        final List<Integer> doubleDigits = Stream.concat(digits.stream(), digits.stream()).collect(Collectors.toList());
        int sum = 0;

        for (int i = 0; i < digits.size(); i++) {
            int curr = digits.get(i);
            int halfway = doubleDigits.get(i + stepsToHalfway);

            if (curr == halfway) {
                sum += curr;
            }
        }

        System.out.println(sum);
    }

    private static List<Integer> parseInput(final String filename) throws IOException {
        String[] digits = null;

        FileReader input = new FileReader(filename);
        BufferedReader bufRead = new BufferedReader(input);
        String myLine;

        while ((myLine = bufRead.readLine()) != null) {
            digits = myLine.split("");
        }

        return Arrays.stream(digits)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private void part1(final List<Integer> digits) {
        int sum = 0;

        for (int i = 0; i < digits.size() - 1; i++) {
            int curr = digits.get(i);
            int next = digits.get(i + 1);

            if (curr == next) {
                sum += curr;
            }
        }

        final int last = digits.get(digits.size() -1);
        final int first = digits.get(0);
        if (last == first) {
            sum += last;
        }

        System.out.println(sum);
    }
}
