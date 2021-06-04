package tetraminos;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class TetraminoL extends Tetramino {
    public TetraminoL(double x, double y,double cellSize) {
        super();
        this.setName("L");
        this.setColor(Color.web("orange"));
        List<Cell> body = new ArrayList<>();
        body.add(new Cell(x + cellSize/2,y + cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 3*cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 5*cellSize/2,cellSize));
        body.add(new Cell(x + 3*cellSize/2,y + 5*cellSize/2,cellSize));
        this.setCells(body);
    }

    @Override
    public Cell getMassCentre() {
        Cell massCentre = new Cell(getCells().get(1).getCenterX() + getCells().get(0).getSize()/2,
                getCells().get(1).getCenterY() + getCells().get(0).getSize()/2,
                getCells().get(0).getSize());
        return massCentre;
    }
}
