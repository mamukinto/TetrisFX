package tetraminos;

import javafx.scene.paint.Color;

import java.util.List;

public abstract class Tetramino {
    private String name;
    private Color color;
    private List<Cell> cells;

    public Tetramino() {

    }

    public Tetramino(double x, double y) {
    }

    public Tetramino(String name, Color color, List<Cell> cells) {
        this.name = name;
        this.color = color;
        this.cells = cells;
    }

    public void rotate() {
        //rotate :)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
