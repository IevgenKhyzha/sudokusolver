package my.app;

public class Cell {

    private int number;
    private boolean isPredefined;

    public Cell(int number, boolean isPredefined) {
        this.number = number;
        this.isPredefined = isPredefined;
    }

    public int getNumber() {
        return number;
    }

    public boolean isPredefined() {
        return isPredefined;
    }
}
