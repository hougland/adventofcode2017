package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day7 {
    public static void main(String[] args) throws IOException {
        final List<Program> input = parseInput("src/day7/input.txt");

        // bottom node
        String[] programs = {"oxxhpbs", "tiekffc", "nxgtet", "nidfqb", "cctmov", "wpwznmz", "dwggjb"};
        // bottom node
        Program bottomProgram = new Program("qibuqqg", 31, new ArrayList<>(Arrays.asList(programs)));

//        String[] programs = {"ikplxqu", "wacoc", "wknuyhc"};
//        String[] programs = {"ugml", "padx", "fwft"};
//        Program bottomProgram = new Program("tknk", 41, new ArrayList<>(Arrays.asList(programs)));

//        String[] programs1 = {"yrqddoe", "yfiqm", "pwqemv", "wsabbfv", "gilaaof", "mdamnrb", "olimc"};
//        Program program1 = new Program("oxxhpbs", 52, new ArrayList<>(Arrays.asList(programs1));


        // dwggjb is the problem because all of the others are equal

        findUnbalancedNode(bottomProgram);

    }

    public static void findUnbalancedNode(
            Program bottomProgram
    ) throws IOException {
        // get the above programs for a program
        List<Program> abovePrograms = new ArrayList<>();
        bottomProgram.above.forEach(name -> {
            try {
                abovePrograms.add(Program.getProgram(name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Map<Integer, List<Program>> programWeights = new HashMap<>();

        // get the weights for each of the above programs
        for (Program aboveProgram : abovePrograms) {
            int weights = getWeights(aboveProgram);

            if (programWeights.containsKey(weights)) {
                programWeights.get(weights).add(aboveProgram);
            } else {
                final ArrayList<Program> tempPrograms = new ArrayList<>();
                tempPrograms.add(aboveProgram);
                programWeights.put(weights, tempPrograms);
            }
        }

        // find the problematic node
        for (int key : programWeights.keySet()) {
            List<Program> tempProgramList = programWeights.get(key);
            if (tempProgramList.size() == 1 && programWeights.size() > 1) {
                Program unbalancedProgram = tempProgramList.get(0);
                System.out.println("Unbalanced node name: " + unbalancedProgram.name + " with weight: " + unbalancedProgram.weight);
//                Program balancedProgram = programWeights.get(1).get(0);
//                System.out.println("Balanced node on same level weight: " + balancedProgram.weight + " name: " + balancedProgram.name);
                // this is our unbalanced stack, dig deeper
                // call this function recursively until...what??
                findUnbalancedNode(unbalancedProgram);
            }
        }
    }

    public static int getWeights(
            Program program
    ) throws IOException {
        if (program.above.size() == 0) {
            return program.weight;
        }

        int sum = program.weight;

        for (String name : program.above) {
            Program aboveProgram = Program.getProgram(name);

            sum += getWeights(aboveProgram);
        }

        if (program.name.equals("egbzge")) {
            System.out.println("egbzge above weight: " + sum);
        }

        if (program.name.equals("gokeude")) {
            System.out.println("gokeude above weight: " + sum);
        }

        if (program.name.equals("tmgvgbg")) {
            System.out.println("tmgvgbg above weight: " + sum);
        }

        if (program.name.equals("qqouxxb")) {
            System.out.println("qqouxxb above weight: " + sum);
        }

        return sum;
    }


    private static List<Program> parseInput(final String filename) throws IOException {
        final List<Program> parsedInput = new ArrayList<>();

        final Stream<String> lines = Files.lines(Paths.get(filename));

        lines.forEach(line -> {
            // change this

            String[] splitLine = line.split(" ");
            String name = splitLine[0];
            int weight = Integer.valueOf(splitLine[1].replaceAll("[(]", "").replaceAll("[)]" ,""));

            final List<String> balancedPrograms = new ArrayList<>();

            if (splitLine.length > 2) {
                for (int i = 3; i < splitLine.length; i++) {
                    String balancedName = splitLine[i].replaceAll(",", "");
                    balancedPrograms.add(balancedName);
                }
            }

            final Program program = new Program(name, weight, balancedPrograms);

            parsedInput.add(program);


        });

        return parsedInput;
    }


    // part 1
    //        for (int i = 0; i < input.size(); i++) {
//            Program program = input.get(i);
//
//            boolean foundLinkUpwards = false;
//
//            for (int j = 0; j < input.size(); j++) {
//                Program maybeBalanced = input.get(j);
//
//                if (maybeBalanced.above.contains(program.name)) {
//                    foundLinkUpwards = true;
//                }
//            }
//
//            if (foundLinkUpwards) {
//                noLinks.remove(program);
//            }
//
//        }
//        System.out.println("Size " + noLinks.size());
//        System.out.println("Answer: " + noLinks.get(0).name);
}
