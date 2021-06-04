package tetraminos;

import java.util.ArrayList;
import java.util.List;

public class TetraminoFactory {
    public Tetramino getTetramino(String type,double x, double y,double cellSize) {
        switch (type) {
            case "I":
                return new TetraminoI(x,y,cellSize);
            case "J":
                return new TetraminoJ(x,y,cellSize);
            case "L":
                return new TetraminoL(x,y,cellSize);
            case "O":
                return new TetraminoO(x,y,cellSize);
            case "S":
                return new TetraminoS(x,y,cellSize);
            case "Z":
                return new TetraminoZ(x,y,cellSize);
            case "T":
                return new TetraminoT(x,y,cellSize);
            default:
                return null;
        }
    }
}
