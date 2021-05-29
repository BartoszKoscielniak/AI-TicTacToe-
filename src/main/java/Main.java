import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start (Stage primaryStage ) throws Exception {
        Parent root  = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/gameBoard.fxml")));
        primaryStage.setTitle( "Tic Tac Toe" );
        primaryStage.setResizable( false );
        primaryStage.setScene( new Scene( root ) );
        primaryStage.show();
    }
}
