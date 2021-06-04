package tetraminos;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
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

    public void draw(GraphicsContext g) {
        this.cells.forEach(cell -> {
            g.setFill(this.getColor());
            g.fillRect(cell.getCenterX() - cell.getSize()/2,cell.getCenterY() - cell.getSize()/2,cell.getSize(),cell.getSize());
            g.setStroke(Color.web("black"));
            g.strokeRect(cell.getCenterX() - cell.getSize()/2,cell.getCenterY() - cell.getSize()/2,cell.getSize(),cell.getSize());
        });
    }


    public void rotate() {

        double cy = getMassCentre().getCenterY();
        double cx = getMassCentre().getCenterX();



        getCells().forEach(cell -> {
            double py = cell.getCenterY();
            double px = cell.getCenterX();

            px -= cx;
            py -= cy;

            double newx = - py;
            double newy = px;

            px = newx + cx;
            py = newy + cy;

            cell.setCenterY(py);
            cell.setCenterX(px);

        });


    }

    public Cell getMassCentre() {
        return null;
    }

    public void drawPreview(GraphicsContext g) {
        TetraminoFactory tf = new TetraminoFactory();
        Tetramino preview = tf.getTetramino(this.getName(),25,25,25);
        preview.getCells().forEach(cell -> {
            g.setFill(this.getColor());
            g.fillRect(cell.getCenterX() - cell.getSize()/2,cell.getCenterY() - cell.getSize()/2,cell.getSize(),cell.getSize());
            g.setStroke(Color.web("black"));
            g.strokeRect(cell.getCenterX() - cell.getSize()/2,cell.getCenterY() - cell.getSize()/2,cell.getSize(),cell.getSize());
        });
    }

    public void move() {
        cells.forEach(cell -> {
            cell.setCenterY(cell.getCenterY() + cell.getSize());
        });
    }

    public void moveRight() {
        cells.forEach(cell -> {
            cell.setCenterX(cell.getCenterX() + cell.getSize());
        });
    }

    public void moveLeft() {
        cells.forEach(cell -> {
            cell.setCenterX(cell.getCenterX() - cell.getSize());
        });
    }

    public Cell getLowestCell() {
        Cell lowest = new Cell(0,0,cells.get(0).getSize());
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getCenterY() > lowest.getCenterY()) {
                lowest = cells.get(i);
            }
        }
        return lowest;
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
