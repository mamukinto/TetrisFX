package tetraminos;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cell {
    private double centerX;
    private double centerY;
    private double size;
    private boolean taken = false;
    private Color color;

    public Cell(double centerX, double centerY, double size) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.size = size;
    }

    public void draw(GraphicsContext g) {
        g.setFill(getColor());
        g.fillRect(getCenterX() - getSize()/2,getCenterY() - getSize()/2,getSize(),getSize());
        g.setStroke(Color.web("black"));
        g.setLineWidth(2.5);
        g.strokeRect(getCenterX() - getSize()*0.5/2,getCenterY() - getSize()*0.5/2,getSize()*0.5,getSize()*0.5);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
