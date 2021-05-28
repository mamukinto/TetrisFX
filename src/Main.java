import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static final double WIDTH = 600;
    private static final double HEIGTH = 800;

    @Override
    public void start(Stage stage) throws Exception{
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,WIDTH,HEIGTH);
        stage.setScene(scene);

        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
