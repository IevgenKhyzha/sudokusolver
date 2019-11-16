package my.app;

import my.app.algorithmbeans.Algorithm;
import my.app.pojo.ArgsValues;
import org.apache.commons.cli.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ArgsValues argsValues;
        if (args.length == 0) {
            argsValues = new ArgsValues("D:\\DOCUMENTS\\sudokufiles\\sudoku_5.txt",
                    "D:\\DOCUMENTS\\sudokufiles\\sudokuout.html", "DFS");
        } else {
            argsValues = getValuesFromArgs(args);
        }

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("my.app.configuration");

        new SudokuSolver(argsValues.getInPath(), argsValues.getOutPath(),
                (Algorithm) applicationContext.getBean(argsValues.getAlgorithm()));
    }

    private static ArgsValues getValuesFromArgs(String[] args) {
        // java -jar sudoku-solver.jar -in <puzzle-file> -out <solution-file> -alg <ALGORITHM>

        Options options = new Options();

        Option input = new Option("in", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("out", "output", true, "output file");
        output.setRequired(true);
        options.addOption(output);

        Option algorithm = new Option("alg", "algorithm", true, "algorithm to use");
        output.setRequired(true);
        options.addOption(algorithm);

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = null;

        try {
            commandLine = parser.parse(options, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            new HelpFormatter().printHelp("utility-name", options);
            System.exit(1);
        }

        return new ArgsValues(commandLine.getOptionValue("input"), commandLine.getOptionValue("output"),
                commandLine.getOptionValue("algorithm"));
    }
}
