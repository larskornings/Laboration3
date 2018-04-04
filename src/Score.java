
import java.awt.Color;

public class Score {

    private final Text _scoreText;
    private final Text _ta;
    private int score = 0;

    public Score() {
        _scoreText = new Text(20, Color.BLACK);
        _scoreText.setText("Score: " + score);
        _ta = new Text(40, Color.RED);
    }

    public void setLocation(int x, int y) {
        _scoreText.setLocation(x, y);
    }

    public void updateScore() {
        _scoreText.setText("Score: " + score);
    }

    public void gameWon() {
        _ta.setText("Game Won");
        _ta.setLocation((GameBoard.DISPLAY_WIDTH - _ta.getWidth()) / 2, GameBoard.DISPLAY_HEIGHT / 2);
    }

    public void gameOver() {
        _ta.setText("Game Over");
        _ta.setLocation((GameBoard.DISPLAY_WIDTH - _ta.getWidth()) / 2, GameBoard.DISPLAY_HEIGHT / 2);
    }

    public void add(int i) {
        score += i;
    }

}
