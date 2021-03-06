import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {

    Integer[][] xPos = new Integer[3][3];
    Integer[][] xPosTemp = new Integer[3][3];
    int diffLVL;
    boolean gameStarted = false;

    @FXML
    private GridPane ticTacToeBoard;

    @FXML
    private Button startButton;

    @FXML
    private Label endingText;

    @FXML
    private RadioButton radioButtonHard;

    @FXML
    private RadioButton radioButtonEasy;

    @FXML
    private Button startPlayerButton;

    @FXML
    private Button restartButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //wyzerowanie tablic
        for (Integer[] xPo : xPos) {
            Arrays.fill(xPo, 0);
        }
        for (Integer[] xPo : xPosTemp) {
            Arrays.fill(xPo, 0);
        }
        endingText.setVisible(false);
        restartButton.setDisable(true);
        radioButtonEasy.setSelected(true);
        diffLVL = 1;

        radioButtonEasy.setOnMouseClicked(event -> {
            if (radioButtonEasy.isSelected()) radioButtonHard.setSelected(false);
            radioButtonEasy.setSelected(true);
            diffLVL = 1;
        });

        radioButtonHard.setOnMouseClicked(event -> {
            if (radioButtonHard.isSelected()) radioButtonEasy.setSelected(false);
            radioButtonHard.setSelected(true);
            diffLVL = 2;
        });

        ticTacToeBoard.setOnMouseClicked(event -> {
            Label x = new Label("X");
            x.setFont(new Font(120));
            if (xPos[(int) Math.ceil(event.getSceneX() / 200) - 1][(int) Math.ceil(event.getSceneY() / 200) - 1] != 1 && xPos[(int) Math.ceil(event.getSceneX() / 200) - 1][(int) Math.ceil(event.getSceneY() / 200) - 1] != -1 && !endingText.isVisible() && gameStarted) {
                ticTacToeBoard.add(x, (int) Math.ceil(event.getSceneX() / 200) - 1, (int) Math.ceil(event.getSceneY() / 200) - 1);
                xPos[(int) Math.ceil(event.getSceneX() / 200) - 1][(int) Math.ceil(event.getSceneY() / 200) - 1] = 1;
                xPosTemp[(int) Math.ceil(event.getSceneX() / 200) - 1][(int) Math.ceil(event.getSceneY() / 200) - 1] = 1;
                winningLosingCheck(xPos, false);
                if (!endingText.isVisible()) {
                    aiMove(diffLVL);
                }
            }
        });

        startButton.setOnMouseClicked(event -> {
            aiMove(diffLVL);
            radioButtonHard.setDisable(true);
            radioButtonEasy.setDisable(true);
            gameStarted = true;
            startButton.setDisable(true);
            startPlayerButton.setDisable(true);
            restartButton.setDisable(false);
            ticTacToeBoard.setGridLinesVisible(true);
        });

        startPlayerButton.setOnMouseClicked(event -> {
            radioButtonHard.setDisable(true);
            radioButtonEasy.setDisable(true);
            gameStarted = true;
            startButton.setDisable(true);
            startPlayerButton.setDisable(true);
            restartButton.setDisable(false);
            ticTacToeBoard.setGridLinesVisible(true);
        });

        restartButton.setOnMouseClicked(event -> {
            for (Integer[] xPo : xPos) {
                Arrays.fill(xPo, 0);
            }
            for (Integer[] xPo : xPosTemp) {
                Arrays.fill(xPo, 0);
            }
            ticTacToeBoard.setGridLinesVisible(false);
            ticTacToeBoard.getChildren().clear();
            ticTacToeBoard.setGridLinesVisible(true);
            startButton.setDisable(false);
            startPlayerButton.setDisable(false);
            restartButton.setDisable(true);
            radioButtonEasy.setSelected(true);
            diffLVL = 1;
            gameStarted = false;
            endingText.setVisible(false);
            radioButtonEasy.setDisable(false);
            radioButtonHard.setDisable(false);
            radioButtonEasy.setSelected(true);
            radioButtonHard.setSelected(false);
        });

    }

    public void aiMove(int diffLVL){
        Label X = new Label("X");
        X.setFont(new Font(120));
        //wstawianie x
        Integer[] temp = new Integer[2];
        int previousRate = Integer.MIN_VALUE;
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                if (xPos[x][y] == 0){
                    xPos[x][y] = -1;
                    xPosTemp[x][y] = -1;
                    int rate = miniMax(diffLVL,false, xPosTemp,0);
                    xPos[x][y] = 0;
                    xPosTemp[x][y] = 0;
                    if (previousRate < rate){
                        previousRate = rate;
                        temp[0] = x;
                        temp[1] = y;
                    }
                }
            }
        }
        ticTacToeBoard.add(X, temp[0], temp[1]);
        xPos[temp[0]][temp[1]] = -1;
        xPosTemp[temp[0]][temp[1]] = -1;
        winningLosingCheck(xPos,true);
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

    public int winningCheck(Integer[][] posToCheck, boolean AI){
        //poziomo
        if (posToCheck[0][0] != 0 && posToCheck[0][1] != 0 && posToCheck[0][2] != 0 ) if (AI) {return 1;}else {return -1;}
        if (posToCheck[1][0] != 0 && posToCheck[1][1] != 0 && posToCheck[1][2] != 0 ) if (AI) {return 1;}else {return -1;}
        if (posToCheck[2][0] != 0 && posToCheck[2][1] != 0 && posToCheck[2][2] != 0 ) if (AI) {return 1;}else {return -1;}
        //pionowo
        if (posToCheck[0][0] != 0 && posToCheck[1][0] != 0 && posToCheck[2][0] != 0 ) if (AI) {return 1;}else {return -1;}
        if (posToCheck[0][1] != 0 && posToCheck[1][1] != 0 && posToCheck[2][1] != 0 ) if (AI) {return 1;}else {return -1;}
        if (posToCheck[0][2] != 0 && posToCheck[1][2] != 0 && posToCheck[2][2] != 0 ) if (AI) {return 1;}else {return -1;}
        //przekatne
        if (posToCheck[0][0] != 0 && posToCheck[1][1] != 0 && posToCheck[2][2] != 0 ) if (AI) {return 1;}else {return -1;}
        if (posToCheck[0][2] != 0 && posToCheck[1][1] != 0 && posToCheck[2][0] != 0 ) if (AI) {return 1;}else {return -1;}

        return 0;
    }

    public int miniMax(int difficultyLvL,boolean AI, Integer[][] posTable, int movesToBeDone){
        int winnerCheck = winningCheck(posTable,AI);
        if (winnerCheck != 0) return winnerCheck;

        if (AI) {//AI
            int bestRate = Integer.MIN_VALUE;
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (posTable[x][y] == 0){
                        posTable[x][y] = -1;
                        int rate = miniMax(difficultyLvL,false,posTable, movesToBeDone + 1);
                        rate = rate - movesToBeDone;
                        posTable[x][y] = 0;
                        if (difficultyLvL == 2) { if (rate > bestRate) bestRate = rate; } else {if (rate < bestRate) bestRate = rate;}
                    }
                }
            }
            return bestRate;
        }else {//symulacja gracza
            int bestRate = Integer.MAX_VALUE;
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (posTable[x][y] == 0){
                        posTable[x][y] = 1;
                        int rate = miniMax(difficultyLvL,true,posTable, movesToBeDone + 1);
                        posTable[x][y] = 0;
                        rate = rate + movesToBeDone;
                        if (difficultyLvL == 2) { if (rate < bestRate) bestRate = rate; } else {if (rate > bestRate) bestRate = rate;}
                    }
                }
            }
            return bestRate;
        }
    }
}
