package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) throws IOException {
        final List<List<Integer>> input = parseInput("src/day2/input.txt");

        final List<Integer> products = new ArrayList<>();

        for (final List<Integer> line : input) {
            for (int i = 0; i < line.size(); i++) {
                for (int j = i + 1; j < line.size(); j++) {
                    final int number1 = line.get(i);
                    final int number2 = line.get(j);

                    if (number1 % number2 == 0) {
                        products.add(Math.abs(number1 / number2));
                    }

                    if (number2 % number1 == 0) {
                        products.add(Math.abs(number2 / number1));
                    }
                }
            }
        }

        int sum = products.stream().mapToInt(Math::toIntExact).sum();
        System.out.println(sum);


//        part 1
//        for (final List<Integer> line : input) {
//            int largest = 0;
//            int smallest = 922337203;
//
//            for (final int number : line) {
//                if (number > largest) {
//                    largest = number;
//                }
//
//                if (number < smallest) {
//                    smallest = number;
//                }
//            }
//
//            difference.add(largest - smallest);
//        }
    }

    private static List<List<Integer>> parseInput(final String filename) throws IOException {
        List<List<Integer>> parsedInput = new ArrayList<>();

        FileReader input = new FileReader(filename);
        BufferedReader bufRead = new BufferedReader(input);
        String myLine;

        while ((myLine = bufRead.readLine()) != null) {
            String[] line = myLine.split("\t");
            List<Integer> parsedLine = Arrays.stream(line)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            parsedInput.add(parsedLine);
        }

        return parsedInput;
    }
}
