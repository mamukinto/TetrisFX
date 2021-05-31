package tetraminos;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class TetraminoJ extends Tetramino {
    public TetraminoJ(double x, double y,double cellSize) {
        this.setName("J");
        this.setColor(Color.web("red"));
        List<Cell> body = new ArrayList<>();
        body.add(new Cell(x + cellSize/2,y + cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 3*cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 5*cellSize/2,cellSize));
        body.add(new Cell(x - cellSize/2,y + 5*cellSize/2,cellSize));
    }
}
