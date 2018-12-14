package my.app;

import my.app.algorithmbeans.Algorithm;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ArgsValues argsValues;
        if (args.length == 0) {
            argsValues = new ArgsValues("/Users/eugenkhyzha/Documents/sudoku files/sudoku_in_un_all.txt","/Users/eugenkhyzha/Documents/sudoku files/sudokuout.html", "DFS");//sudoku_in_un_all.txt , sudoku_in_un_1cell,
        } else {
            argsValues = getValuesFromArgs(args);
        }

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("my.app.configuration");
        Algorithm algorithmBean = (Algorithm) applicationContext.getBean(argsValues.getAlgorithm());

        new SudokuSolver(argsValues.getInPath(), argsValues.getOutPath(), algorithmBean);
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
        HelpFormatter formatter = new HelpFormatter();
        CommandLine commandLine = null;

        try {
            commandLine = parser.parse(options, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }

        String inputFilePath = commandLine.getOptionValue("input");
        String outputFilePath = commandLine.getOptionValue("output");
        String algorithmString = commandLine.getOptionValue("algorithm");

        return new ArgsValues(inputFilePath, outputFilePath, algorithmString);
    }
}
