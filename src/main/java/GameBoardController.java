import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    Integer[][] xPos = new Integer[3][3];

    @FXML
    private RadioButton radioButtonPC;

    @FXML
    private GridPane ticTacToeBoard;

    @FXML
    private Button startButton;

    @FXML
    private RadioButton radioButtonPLAYER;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //wyzerowanie tablicy
        for (Integer[] xPo : xPos) {
            Arrays.fill(xPo, 0);
        }

        ticTacToeBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Label x = new Label("X");

                if (xPos[(int) Math.ceil(event.getSceneX() / 200) - 1][(int) Math.ceil(event.getSceneY() / 200) - 1] != 1) {
                    ticTacToeBoard.add(x, (int) Math.ceil(event.getSceneX() / 200) - 1, (int) Math.ceil(event.getSceneY() / 200) - 1);
                    xPos[(int) Math.ceil(event.getSceneX() / 200) - 1][(int) Math.ceil(event.getSceneY() / 200) - 1] = 1;
                    pcMove();
                }
            }
        });
    }

    public void pcMove(){
        //wstawianie x
        Label X = new Label("X");
        outer:
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                if (xPos[x][y] != 1){
                    ticTacToeBoard.add(X, x, y);
                    xPos[x][y] = 1;
                    break outer;
                }
            }
        }

        //wyswietlanie
        for (int a = 0; a < xPos.length; a++){
            for (int b = 0; b < xPos[a].length; b++ ){
                System.out.print(xPos[b][a] + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public void miniMax(){

    }
}
