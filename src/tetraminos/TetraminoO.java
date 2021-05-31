package tetraminos;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class TetraminoO extends Tetramino{
    public TetraminoO(double x, double y,double cellSize) {
        this.setName("O");
        this.setColor(Color.web("pink"));
        List<Cell> body = new ArrayList<>();
        body.add(new Cell(x + cellSize/2,y + cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 3*cellSize/2,cellSize));
        body.add(new Cell(x + 3*cellSize/2,y + cellSize/2,cellSize));
        body.add(new Cell(x + 3*cellSize/2,y + 3*cellSize/2,cellSize));
    }
}
