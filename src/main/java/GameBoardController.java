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

    @FXML
    private Label endingText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //wyzerowanie tablicy
        for (Integer[] xPo : xPos) {
            Arrays.fill(xPo, 0);
        }
        endingText.setVisible(false);

        ticTacToeBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {//1 gracz | -1 AI

                Label x = new Label("X");
                if (xPos[(int) Math.ceil(event.getSceneX() / 200) - 1][(int) Math.ceil(event.getSceneY() / 200) - 1] != 1 && xPos[(int) Math.ceil(event.getSceneX() / 200) - 1][(int) Math.ceil(event.getSceneY() / 200) - 1] != -1 && !endingText.isVisible()) {
                    ticTacToeBoard.add(x, (int) Math.ceil(event.getSceneX() / 200) - 1, (int) Math.ceil(event.getSceneY() / 200) - 1);
                    xPos[(int) Math.ceil(event.getSceneX() / 200) - 1][(int) Math.ceil(event.getSceneY() / 200) - 1] = 1;
                    winningLosingCheck(xPos, false);
                    if (!endingText.isVisible()) {
                        pcMove();
                    }
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
                if (xPos[x][y] != 1 && xPos[x][y] != -1){
                    ticTacToeBoard.add(X, x, y);
                    xPos[x][y] = -1;
                    break outer;
                }
            }
        }
        winningLosingCheck(xPos,true);
        //wyswietlanie
        for (int a = 0; a < xPos.length; a++){
            for (int b = 0; b < xPos[a].length; b++ ){
                System.out.print(xPos[a][b] + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void winningLosingCheck(Integer[][] posToCheck, boolean AI){
        //poziomo
        if (posToCheck[0][0] != 0 && posToCheck[0][1] != 0 && posToCheck[0][2] != 0 ) if (AI) {endingText.setText("You Won!"); endingText.setVisible(true);} else {endingText.setText("You Lost!"); endingText.setVisible(true);}
        if (posToCheck[1][0] != 0 && posToCheck[1][1] != 0 && posToCheck[1][2] != 0 ) if (AI) {endingText.setText("You Won!"); endingText.setVisible(true);} else {endingText.setText("You Lost!"); endingText.setVisible(true);}
        if (posToCheck[2][0] != 0 && posToCheck[2][1] != 0 && posToCheck[2][2] != 0 ) if (AI) {endingText.setText("You Won!"); endingText.setVisible(true);} else {endingText.setText("You Lost!"); endingText.setVisible(true);}
        //pionowo
        if (posToCheck[0][0] != 0 && posToCheck[1][0] != 0 && posToCheck[2][0] != 0 ) if (AI) {endingText.setText("You Won!"); endingText.setVisible(true);} else {endingText.setText("You Lost!"); endingText.setVisible(true);}
        if (posToCheck[0][1] != 0 && posToCheck[1][1] != 0 && posToCheck[2][1] != 0 ) if (AI) {endingText.setText("You Won!"); endingText.setVisible(true);} else {endingText.setText("You Lost!"); endingText.setVisible(true);}
        if (posToCheck[0][2] != 0 && posToCheck[1][2] != 0 && posToCheck[2][2] != 0 ) if (AI) {endingText.setText("You Won!"); endingText.setVisible(true);} else {endingText.setText("You Lost!"); endingText.setVisible(true);}
        //przekatne
        if (posToCheck[0][0] != 0 && posToCheck[1][1] != 0 && posToCheck[2][2] != 0 ) if (AI) {endingText.setText("You Won!"); endingText.setVisible(true);} else {endingText.setText("You Lost!"); endingText.setVisible(true);}
        if (posToCheck[0][2] != 0 && posToCheck[1][1] != 0 && posToCheck[2][0] != 0 ) if (AI) {endingText.setText("You Won!"); endingText.setVisible(true);} else {endingText.setText("You Lost!"); endingText.setVisible(true);}
    }

    /*public int miniMax(int difficultyLvL,boolean AI, Integer[][] posTable){

        if (AI) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (posTable[x][y] == 0){
                        posTable[x][y] = -1;
                        return miniMax(difficultyLvL,false,posTable);
                    }
                }
            }
        }else {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (posTable[x][y] == 0){
                        posTable[x][y] = 1;
                        return miniMax(difficultyLvL,true,posTable);
                    }
                }
            }
        }
    }*/
}
