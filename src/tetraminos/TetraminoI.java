package tetraminos;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class TetraminoI extends Tetramino{
    public TetraminoI(double x, double y,double cellSize) {
        super();
        this.setName("I");
        this.setColor(Color.web("cyan"));
        List<Cell> body = new ArrayList<>();
        body.add(new Cell(x + cellSize/2,y + cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 3*cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 5*cellSize/2,cellSize));
        body.add(new Cell(x + cellSize/2,y + 7*cellSize/2,cellSize));


        Cell massCentre = new Cell(body.get(1).getCenterX(),body.get(1).getCenterY() + cellSize/2,cellSize);


        this.setCells(body);
        body.forEach(cell -> {
            cell.setColor(getColor());
        });
    }

    @Override
    public Cell getMassCentre() {
        Cell massCentre = new Cell(getCells().get(1).getCenterX() + getCells().get(0).getSize()/2,getCells().get(1).getCenterY() + getCells().get(0).getSize()/2,getCells().get(0).getSize());
        return massCentre;
    }

}
