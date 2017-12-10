package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Day9 {
    public static void main(String[] args) throws IOException {
        final List<String> input = parseInput("src/day9/input.txt");

        Stack<String> stack = new Stack<>();
        int sum = 0;
        int garbage = 0;

        for (int i = 0; i < input.size(); i++) {
            final String character = input.get(i);

            if ("!".equals(character)) {
                i++;
                continue;
            }

            if (!stack.empty() && "<".equals(stack.peek())) {
                // currently in garbage
                if (">".equals(character)) {
                    stack.pop();
                } else {
                    garbage++;
                }
            } else if ("<".equals(character)) {
                stack.push(character);
            } else if ("{".equals(character)) {
                stack.push(character);
            } else if ("}".equals(character)) {
                sum += stack.size();
                stack.pop();
            }
        }

        System.out.println(sum);
        System.out.println(garbage);
    }

    private static List<String> parseInput(final String filename) throws IOException {
        List<String> parsedInput = new ArrayList<>();

        final Stream<String> lines = Files.lines(Paths.get(filename));

        lines.forEach(line -> {
            String[] splitLine = line.split("");
            Arrays.stream(splitLine)
                    .forEach(parsedInput::add);
        });

        return parsedInput;
    }

}
