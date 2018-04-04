
import java.awt.Color;

public class Score {

    private final Text _scoreText;
    
    private int score = 0;
    
    public Score() {
        _scoreText = new Text("Score: " + score, 20, Color.BLACK);
    }

    void setLocation(int x, int y) {
        _scoreText.setLocation(x, y);
    }

    void updateScore() {
        _scoreText.setText("Score: " + score);
    }

    void add(int i) {
        score += i;
    }

}
