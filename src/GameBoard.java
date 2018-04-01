
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GameBoard extends wheels.users.Frame implements ActionListener, MouseMotionListener, MouseListener {

    final static int DISPLAY_WIDTH = 700;
    final static int DISPLAY_HEIGHT = 500;

    Timer t;
    Ball _ball;
    Bat _bat;
    Text ta;
    Score _score;
    
    final int DELAY = 3;
    final int brick3Rows = 1, brick2Rows = 1, brick1Rows = 5;
    final int brickRows = brick1Rows + brick2Rows + brick3Rows;
    final int brickColumns = 12;
    final int brickStartX = 104, brickStartY = 80;

    Brick[][] _bricks = new Brick[brickRows][brickColumns];

    boolean startMove = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (startMove == true) {
            _ball.move();
            _score.updateScore();
            if (noBricks()) {
                gameWon();
                t.stop();
            }
            if (_ball.getY() > DISPLAY_HEIGHT / 2 && _ball.getY() < DISPLAY_HEIGHT) {
                _ball.collision(_bat);
            } else if (_ball.getY() >= DISPLAY_HEIGHT) {
                gameOver();
                t.stop();
            } else {
                checkBrickCollision();
            }
        }
    }

    //Invoked when the mouse button has been clicked (pressed and released) on a component.
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //Invoked when a mouse button has been pressed on a component.
    @Override
    public void mousePressed(MouseEvent e) {

    }

    //Invoked when a mouse button has been released on a component.
    @Override
    public void mouseReleased(MouseEvent e) {
        startMove = true;
    }

    //Invoked when the mouse enters a component.
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    //Invoked when the mouse exits a component.
    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseDraggedOrMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseDraggedOrMoved(e);
    }

    public void mouseDraggedOrMoved(MouseEvent e) {
        _bat.setLocation(e.getX(), _bat.getY());

        int ballOffsetX = (Bat.WIDTH / 2) - Ball.DIAMETER / 2;
        int ballOffsetY = Ball.DIAMETER + 3; // To not be on the bat

        if (startMove == false) {
            _ball.setLocation(_bat.getX() + ballOffsetX, _bat.getY() - ballOffsetY);
        }
    }

    public GameBoard() {
        super();
    }

    public void initGame() {
        // Start position Bat
        int batX = DISPLAY_WIDTH / 2;
        int batY = DISPLAY_HEIGHT * 7 / 8;
        _bat = new Bat(batX, batY);

        // Start position Ball
        int ballX = _bat.getX() + Bat.WIDTH / 2 - Ball.DIAMETER / 2;
        int ballY = _bat.getY() - Ball.DIAMETER - 3;
        _ball = new Ball(ballX, ballY);

        int cBrickY = brickStartY;
        for (int i = 0; i < brickRows; i++) {
            for (int j = 0; j < brickColumns; j++) {
                int cBrickX = brickStartX + j * (Brick.WIDTH);
                if (i < brick3Rows) {
                    _bricks[i][j] = new Brick3(cBrickX, cBrickY);
                } else if (i < brick2Rows + brick3Rows) {
                    _bricks[i][j] = new Brick2(cBrickX, cBrickY);
                } else {
                    _bricks[i][j] = new Brick1(cBrickX, cBrickY);
                }
            }
            cBrickY += Brick.HEIGHT;
        }

        _score = new Score();
        _score.setLocation(-20, DISPLAY_HEIGHT - 60);
    }

    public void run() {
        _dp.addMouseMotionListener(this);
        _dp.addMouseListener(this);

        initGame();
        t = new Timer(DELAY, this);
        t.start();
    }

    public void checkBrickCollision() {
        for (int i = 0; i < (brickRows); i++) {
            for (int j = 0; j < brickColumns; j++) {
                // Gör bara en kollision
                if (_ball.collision(_bricks[i][j])) {
                    if ( _bricks[i][j].getColor() == null) {
                        if ( _bricks[i][j] instanceof Brick3) {
                            _score.add( _bricks[i][j].getPoints());
                        } else if ( _bricks[i][j] instanceof Brick2) {
                            _score.add( _bricks[i][j].getPoints());
                        } else {
                            _score.add( _bricks[i][j].getPoints());
                        }
                    }
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        GameBoard g = new GameBoard();
        g.run();
    }

    public boolean noBricks() {
        for (int i = 0; i < (brickRows); i++) {
            for (int j = 0; j < brickColumns; j++) {
                if (_bricks[i][j].getColor() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void gameWon() {
        ta = new Text("Game won");
        ta.setLocation((DISPLAY_WIDTH - ta.getWidth()) / 2, DISPLAY_HEIGHT / 2);
    }

    public void gameOver() {
        ta = new Text("Game Over");
        ta.setLocation((DISPLAY_WIDTH - ta.getWidth()) / 2, DISPLAY_HEIGHT / 2);
    }
}
