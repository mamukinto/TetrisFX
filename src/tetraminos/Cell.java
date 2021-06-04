package tetraminos;

public class Cell {
    private double centerX;
    private double centerY;
    private double size;
    private boolean taken = false;

    public Cell(double centerX, double centerY, double size) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.size = size;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
