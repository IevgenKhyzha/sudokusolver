package my.app.utils;

import my.app.pojo.Cell;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SudokuFileUtils {

    private String inFilePath;
    private String outFilePath;

    private SudokuFileUtils(String inFilePath, String outFilePath) {
        this.inFilePath = inFilePath;
        this.outFilePath = outFilePath;
    }

    private static SudokuFileUtils instance;

    public static SudokuFileUtils getInstance(String inFilePath, String outFilePath) {
        if (instance == null) {
            instance = new SudokuFileUtils(inFilePath, outFilePath);
        }
        return instance;
    }

    private static final int NON_READABLE_LINE_PERIOD = 4;
    private static final int NON_READABLE_CHARACTER_IN_LINE = 8;
    private static final String EMPTY_PLACE = " ";

    public int[][] transformASCIIFileToArray() {
        int[][] sudokuArray = new int[9][9];

        try (BufferedReader br = new BufferedReader(new FileReader(new File(inFilePath)))) {
            int lineNumberInFile = 0;
            int lineInArray = 0;

            String line;
            while ((line = br.readLine()) != null) {
                int columnInArray = 0;
                if (lineInArray > 8) {
                    break;
                }
                if (lineNumberInFile % NON_READABLE_LINE_PERIOD == 0) {
                    lineNumberInFile++;
                    continue;
                }
                // work with one line
                for (int numberPlaceInLine = 2; numberPlaceInLine <= 22; numberPlaceInLine = numberPlaceInLine + 2) {
                    if (numberPlaceInLine % NON_READABLE_CHARACTER_IN_LINE == 0) {
                        continue;
                    }
                    if (!EMPTY_PLACE.equals(String.valueOf(line.charAt(numberPlaceInLine)))) {
                        sudokuArray[lineInArray][columnInArray] =
                                Integer.parseInt(String.valueOf(line.charAt(numberPlaceInLine)));
                    } else {
                        sudokuArray[lineInArray][columnInArray] = 0;
                    }
                    columnInArray++;
                }
                lineInArray++;
                lineNumberInFile++;
            }

        } catch(IOException e) {
            System.out.println("Error in input file find process " + e.getMessage());
            System.exit(1);
        }

        return sudokuArray;
    }

    public void createOutPutFile(Cell[][] resolvedSudokuArray) {
        try (InputStream templateInputStream = getClass().getResourceAsStream("sudokutemplate.html")) {
            String sudokuHtmlTemplate = IOUtils.toString(templateInputStream, StandardCharsets.UTF_8);

            for (int i = 0; i < resolvedSudokuArray.length; i++) {
                for (int j = 0; j < resolvedSudokuArray[i].length; j++) {
                    sudokuHtmlTemplate = sudokuHtmlTemplate.replace("${" + i + "" + j  +"}",
                            String.valueOf(resolvedSudokuArray[i][j].getNumber()));
                    sudokuHtmlTemplate = sudokuHtmlTemplate.replace("${s" + i + "" + j + "}",
                            resolvedSudokuArray[i][j].isPredefined() ? "style=\"color:blue;\"" : "style=\"color:red;\"");
                }
            }

            FileUtils.writeStringToFile(new File(outFilePath), sudokuHtmlTemplate, StandardCharsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
