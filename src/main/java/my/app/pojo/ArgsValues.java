package my.app.pojo;

public class ArgsValues {

    private String inPath;
    private String outPath;
    private String algorithm;

    public ArgsValues(String inPath, String outPath, String algorithm) {
        this.inPath = inPath;
        this.outPath = outPath;
        this.algorithm = algorithm;
    }

    public String getInPath() {
        return inPath;
    }

    public String getOutPath() {
        return outPath;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
