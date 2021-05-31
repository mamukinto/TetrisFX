package tetraminos;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class TetraminoI extends Tetramino{
    public TetraminoI(double x, double y,double cellSize) {
        this.setName("I");
        this.setColor(Color.web("yellow"));
        List<Cell> body = new ArrayList<>();
        body.add(new Cell(x + cellSize/2,y + cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 3*cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 5*cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 7*cellSize/2,cellSize));
    }
}
