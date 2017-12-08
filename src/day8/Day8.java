package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day8 {
    public static void main(String[] args) throws IOException {
        final List<Instruction> input = parseInput("src/day8/input.txt");

        final Map<String, Integer> registerToValue = new HashMap<>();

        int max = 3880; // solution to part 1

        for (Instruction instruction : input) {
            if (!registerToValue.containsKey(instruction.registerThatWillChange)) {
                registerToValue.put(instruction.registerThatWillChange, 0);
            }

            if (!registerToValue.containsKey(instruction.operationRegister)) {
                registerToValue.put(instruction.operationRegister, 0);
            }

            if (runCondition(registerToValue, instruction.operationRegister, instruction.operation, instruction.operationValue)) {
                int value = registerToValue.get(instruction.registerThatWillChange);

                if ("inc".equals(instruction.instruction)) {
                    registerToValue.put(instruction.registerThatWillChange, value += instruction.valueThatWillBeAddedOrSubtracted);
                } else {
                    registerToValue.put(instruction.registerThatWillChange, value -= instruction.valueThatWillBeAddedOrSubtracted);
                }
            }

            int currentMaxValue = Collections.max(registerToValue.values());

            if (currentMaxValue > max) {
                max = currentMaxValue;
            }
        }

        System.out.println(Collections.max(registerToValue.values()));
        System.out.println(max);
    }

    private static boolean runCondition(
            final Map<String, Integer> registerToValues,
            final String register,
            final String operation,
            final int operationValue
    ) {
        final int currentValue = registerToValues.get(register);

        switch (operation) {
            case (">"):
                return currentValue > operationValue;
            case ("<"):
                return currentValue < operationValue;
            case (">="):
                return currentValue >= operationValue;
            case ("<="):
                return currentValue <= operationValue;
            case ("!="):
                return currentValue != operationValue;
            case ("=="):
                return currentValue == operationValue;
            default:
                throw new RuntimeException("Unexpected operator");
        }
    }

    private static List<Instruction> parseInput(final String filename) throws IOException {
        List<Instruction> parsedInput = new ArrayList<>();

        final Stream<String> lines = Files.lines(Paths.get(filename));

        lines.forEach(line -> {
            String[] splitLine = line.split(" ");

            final String register = splitLine[0];
            final String instruction = splitLine[1];
            final int value = Integer.valueOf(splitLine[2]);
            final String targetRegister = splitLine[4];
            final String opperation = splitLine[5];
            final int targetValue = Integer.valueOf(splitLine[6]);

            Instruction instructionObj = new Instruction(register, instruction, value, targetRegister, opperation, targetValue);

            parsedInput.add(instructionObj);
        });

        return parsedInput;
    }
}
