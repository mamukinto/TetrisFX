package tetraminos;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class TetraminoS extends Tetramino {
    public TetraminoS(double x, double y,double cellSize) {
        this.setName("S");
        this.setColor(Color.web("blue"));
        List<Cell> body = new ArrayList<>();
        body.add(new Cell(x + cellSize/2,y + cellSize/2,cellSize));
        body.add(new Cell(x + 3*cellSize/2,y + cellSize/2,cellSize));
        body.add(new Cell(x + 3*cellSize/2,y - cellSize/2,cellSize));
        body.add(new Cell(x + 5*cellSize/2,y - cellSize/2,cellSize));
    }
}
