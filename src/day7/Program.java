package day7;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    public String name;
    public final int weight;
    public final List<String> above;

    public Program(
            final String name,
            final int weight,
            final List<String> above
    ) {
        this.name = name;
        this.weight = weight;
        this.above = above;
    }


    public static Program getProgram(String nameToFind) throws IOException {
        Program program = null;

        FileReader input = new FileReader("src/day7/input.txt");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine;

        while ((myLine = bufRead.readLine()) != null) {
            String[] splitLine = myLine.split(" ");

            String name = splitLine[0];

            if (nameToFind.equals(name)) {
                int weight = Integer.valueOf(splitLine[1].replaceAll("[(]", "").replaceAll("[)]" ,""));

                final List<String> balancedPrograms = new ArrayList<>();

                if (splitLine.length > 2) {
                    for (int i = 3; i < splitLine.length; i++) {
                        String balancedName = splitLine[i].replaceAll(",", "");
                        balancedPrograms.add(balancedName);
                    }
                }

                program = new Program(name, weight, balancedPrograms);
            }

        }

        return program;
    }


    public static boolean aboveBalanced(
            Program program
    ) throws IOException {
        boolean weightSet = false;
        int weight = 0;

        for (String aboveProgramName : program.above) {
            Program aboveProgram = getProgram(aboveProgramName);
            int aboveWeight = Day7.getWeights(aboveProgram);

            if (!weightSet) {
                weight = aboveWeight;
                weightSet = true;
            }

            if (weight != aboveWeight) {
                System.out.println("WEIGHT DIFFERENT FROM ABOVE: " + program.name);
                return false;
            }
        }

        return true;
    }

}
