import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tetraminos.Cell;
import tetraminos.Tetramino;
import tetraminos.TetraminoFactory;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class Main extends Application {
    private static final double FULL_WIDTH = 350;
    private static final double WIDTH = 250;
    private static final double HEIGTH = 500;
    private static final double CELL_SIZE = 25;
    private static final Random rand = new Random();
    private static final TetraminoFactory tf = new TetraminoFactory();
    private static final List<String> tetraminoTypes = Arrays.asList("O", "S", "Z", "T", "J", "L", "I");
    private static Tetramino nextTetramino = tf.getTetramino(tetraminoTypes.get(rand.nextInt(7)), WIDTH / 2, 50, CELL_SIZE);
    private static Tetramino currentTetramino;
    private static List<Cell> placedCells = new ArrayList<>();
    private static double score = 0;
    private static int delay = 800;
    private static ByteBuffer buffer = ByteBuffer.allocate(1231231231);
    private static List<List<Cell>> allCells = new ArrayList<>();
    private static GraphicsContext mainContext = null;


    private static VBox scoreboard = new VBox();
    private static Label scoreLabel1 = new Label();
    private static Label scoreLabel2 = new Label();
    private static Canvas nextTetraminoCanvas = new Canvas(100, 125);
    private static Label nextTetraminoLabel = new Label();
    private static Label guide = new Label(" Move left or \n right (<-/->)  \n UP to rotate \n Tetramino \n :)))");



    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, FULL_WIDTH, HEIGTH);
        stage.setScene(scene);
        Canvas canvas = new Canvas(WIDTH, HEIGTH);
        spawnTetramino();
        refresher(canvas.getGraphicsContext2D());
        mainContext = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        canvas.setStyle("-fx-border-width: 2px; -fx-border-color: #9b9b9b;");
        turnOnControls(canvas);
        generateScoreboard();
        initAllCells();


        root.setLeft(canvas);
        root.setRight(scoreboard);


        stage.show();

    }

    private void initAllCells() {

        for (int i = 0; i < HEIGTH; i += CELL_SIZE) {
            allCells.add(new ArrayList<>());
            for (int j = 0; j < WIDTH; j += CELL_SIZE) {
                allCells.get(allCells.size() - 1).add(new Cell(j + CELL_SIZE/2, i + CELL_SIZE/2, CELL_SIZE));
            }
        }

    }

    private void generateScoreboard() {
        scoreboard.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        scoreboard.setStyle("-fx-border-width: 2px; -fx-border-color: #9b9b9b;");
        scoreboard.setSpacing(10);
        scoreLabel1.setStyle("-fx-font-size: 36px; -fx-text-fill: #9b9b9b;");
        scoreLabel2.setStyle("-fx-font-size: 36px; -fx-text-fill: #9b9b9b;");
        nextTetraminoLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #9b9b9b;");
        guide.setStyle("-fx-font-size: 14px; -fx-text-fill: #9b9b9b;");
        scoreboard.getChildren().add(nextTetraminoLabel);
        scoreboard.getChildren().add(nextTetraminoCanvas);
        scoreboard.getChildren().add(scoreLabel1);
        scoreboard.getChildren().add(scoreLabel2);
        scoreboard.getChildren().add(guide);
    }

    private void refreshScoreBoard() {
        scoreLabel1.setText("Score: ");
        scoreLabel2.setText(String.valueOf(score));
        nextTetraminoLabel.setText("Next: " + nextTetramino.getName());
        clearCanvas(nextTetraminoCanvas.getGraphicsContext2D());

        if (score > 1000) {
            scoreLabel2.setStyle("-fx-font-size: 24px; -fx-text-fill: #9b9b9b;");
        }

        nextTetramino.drawPreview(nextTetraminoCanvas.getGraphicsContext2D());
    }

    private void turnOnControls(Canvas canvas) {
        canvas.setOnKeyReleased(keyPress -> {
            switch (keyPress.getCode()) {
                case RIGHT:
                    if (isSpaceToRight()) {
                        currentTetramino.moveRight();
                        redrawScene(mainContext);
                    }
                    break;
                case LEFT:
                    if (isSpaceToLeft()) {
                        currentTetramino.moveLeft();
                        redrawScene(mainContext);
                    }
                    break;
                case UP:
                    currentTetramino.rotate();
                    redrawScene(mainContext);
                    break;
                case DOWN:
                    if (!checkIfCurrentTetraminoLanded()) {
                        currentTetramino.move();
                    }
                    redrawScene(mainContext);
                    score += 5;
                    break;
//                case SPACE:
//                    allCells.forEach(row -> {
//                        row.forEach(cell -> {
//                            System.out.print(cell.isTaken() + " ");
//                        });
//                        System.out.println();
//                    });
//                    checkForRowAndScore();
//                    break;
            }
        });
    }

    private void generateNextTetramino() {
        nextTetramino = tf.getTetramino(tetraminoTypes.get(rand.nextInt(7)), WIDTH / 2, 0, CELL_SIZE);
    }

    private void generateCurrentTetramino() {
        currentTetramino = nextTetramino;
    }

    private void spawnTetramino() {
        generateCurrentTetramino();
        generateNextTetramino();
    }

    private boolean isSpaceToRight() {
        AtomicBoolean isSpace = new AtomicBoolean(true);
        placedCells.forEach(placedCell -> {
                currentTetramino.getCells().forEach(currentCell -> {
                    if (currentCell.getCenterY() == placedCell.getCenterY()) {
                        if (currentCell.getCenterX() + currentCell.getSize() >= placedCell.getCenterX()) {
                            isSpace.set(false);
                        }
                    }
                });
        });

        if (isSpace.get()) {
            currentTetramino.getCells().forEach(cell -> {
                if (cell.getCenterX() + cell.getSize() / 2 >= WIDTH) {
                    isSpace.set(false);
                }
            });
        }

        return isSpace.get();
    }

    private boolean isSpaceToLeft() {
        AtomicBoolean isSpace = new AtomicBoolean(true);
            placedCells.forEach(placedCell -> {
                currentTetramino.getCells().forEach(currentCell -> {
                    if (currentCell.getCenterY() == placedCell.getCenterY()) {
                        if (currentCell.getCenterX() - currentCell.getSize() <= placedCell.getCenterX()) {
                            isSpace.set(false);
                        }
                    }
                });
            });

        if (isSpace.get()) {
            currentTetramino.getCells().forEach(cell -> {
                if (cell.getCenterX() - cell.getSize() / 2 <= 0) {
                    isSpace.set(false);
                }
            });
        }
        return isSpace.get();
    }

    private boolean checkIfCurrentTetraminoLanded() {
        AtomicBoolean isLanded = new AtomicBoolean(false);
            placedCells.forEach(placedCell -> {
                currentTetramino.getCells().forEach(currentCell -> {
                    if (currentCell.getCenterX() == placedCell.getCenterX()) {
                        if (currentCell.getCenterY() + currentCell.getSize() >= placedCell.getCenterY()) {
                            isLanded.set(true);
                        }
                    }
                });
            });

        if (!isLanded.get()) {
            isLanded.set(currentTetramino.getLowestCell().getCenterY() + currentTetramino.getLowestCell().getSize() >= HEIGTH);
        }

        return isLanded.get();
    }

    private void checkForRowAndScore() {
        allCells.forEach(row -> {
            List<Boolean> cells = new ArrayList<>();
            row.forEach(cell -> {
                cells.add(cell.isTaken());
            });
            if (!cells.contains(false)) {
                score += 100;
                row.forEach(cell -> {
                    cell.setTaken(false);
                });
                removeCells(row);
                moveDownAllCellsUpperThan(row.get(0).getCenterY());
            }
        });
    }

    private void moveDownAllCellsUpperThan(double centerY) {
        placedCells.forEach(cell -> {
            if (cell.getCenterY() < centerY) {
                cell.setCenterY(cell.getCenterY() + cell.getSize());
            }
        });
    }

    private void removeCells(List<Cell> cells) {
        List<Cell> cellsToRemove = new ArrayList<>();
        for (Cell placedCell : placedCells) {
            for (Cell cell : cells) {
                if (cell.getCenterY() == placedCell.getCenterY() && cell.getCenterX() == placedCell.getCenterX()) {
                    cellsToRemove.add(placedCell);
                }
            }
        }
        placedCells.removeAll(cellsToRemove);
    }

    private void clearCanvas(GraphicsContext g) {
        g.setFill(Color.web("black"));
        g.fillRect(0, 0, WIDTH, HEIGTH);
        g.setStroke(Color.web("#9b9b9b"));
        g.setLineWidth(0.5);
        allCells.forEach(row -> {
            row.forEach(cell -> {
                g.strokeRect(cell.getCenterX() - CELL_SIZE/2,cell.getCenterY() - CELL_SIZE/2,CELL_SIZE,CELL_SIZE);
            });
        });
    }

    private void drawPlacedTetraminos(GraphicsContext g) {
        placedCells.forEach(placedCell -> {
            placedCell.draw(g);
        });
    }

    private void redrawScene(GraphicsContext g) {
        clearCanvas(g);
        Platform.runLater(Main.this::refreshScoreBoard);
        if (checkIfCurrentTetraminoLanded()) {
            score += 10;
            placedCells.addAll(currentTetramino.getCells());
            spawnTetramino();
            takeCell();
            checkForRowAndScore();
            delay -= 10;
        }
        drawPlacedTetraminos(g);
        currentTetramino.draw(g);

        buffer.clear();
    }

    private void refresher(GraphicsContext g) {
        class Refresh extends TimerTask {
            public void run() {
                redrawScene(g);
                currentTetramino.move();
            }
        }

        Timer timer = new Timer();
        timer.schedule(new Refresh(), 0, 400);
    }

    private void takeCell() {
        allCells.forEach(row -> {
            row.forEach(cell -> {
                placedCells.forEach(placedCell -> {
                    if (placedCell.getCenterX() == cell.getCenterX() && placedCell.getCenterY() == cell.getCenterY()) {
                        cell.setTaken(true);
                    }
                });
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
