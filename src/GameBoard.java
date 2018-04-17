
import javax.swing.Timer;
import java.awt.event.*;
import wheels.users.Frame;

public class GameBoard extends Frame implements ActionListener, MouseMotionListener, MouseListener {

    public final static int DISPLAY_WIDTH = 700;
    public final static int DISPLAY_HEIGHT = 500;

    private final int DELAY = 2;
    private final int brick3Rows = 1, brick2Rows = 2, brick1Rows = 4;
    private final int brickRows = brick1Rows + brick2Rows + brick3Rows;
    private final int brickColumns = 12;
    private final int brickStartX = 104, brickStartY = 80;

    private final Brick[][] _bricks = new Brick[brickRows][brickColumns];

    private final Timer t;
    private final Ball _ball;
    private final Bat _bat;
    private final Score _score;

    private boolean gameStarted = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStarted == true) {
            _ball.move();
            _score.updateScore();
            if (noBricks()) {
                _score.youWin();
                _ball.removeBall();
                t.stop();
            } else if (_ball.getY() > DISPLAY_HEIGHT / 2 && _ball.getY() < DISPLAY_HEIGHT) {
                _ball.collisionWithBat(_bat);
            } else if (_ball.getY() >= DISPLAY_HEIGHT) {
                _score.gameOver();
                _ball.removeBall();
                t.stop();
            } 
            else {
                checkBrickCollision();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gameStarted = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

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

    private void mouseDraggedOrMoved(MouseEvent e) {
        _bat.setLocation(e.getX(), _bat.getY());

        if (gameStarted == false) {
            _ball.followBat(_bat);
        }
    }

    public GameBoard() {
        super();
        t = new Timer(DELAY, this);

        int batX = DISPLAY_WIDTH / 2;
        int batY = DISPLAY_HEIGHT * 7 / 8;
        _bat = new Bat(batX, batY);

        int ballX = _bat.getX() + Bat.WIDTH / 2 - Ball.DIAMETER / 2;
        int ballY = _bat.getY() - Ball.DIAMETER - 3;
        _ball = new Ball(ballX, ballY);

        initBricks();

        _score = new Score();
        _score.setLocation(10, DISPLAY_HEIGHT - 30);

    }

    private void initBricks() {
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

    }

    private void checkBrickCollision() {
        for (int i = 0; i < (brickRows); i++) {
            for (int j = 0; j < brickColumns; j++) {
                if (_ball.collisionWithBrick(_bricks[i][j])) {
                    if (_bricks[i][j].isRemoved()) {
                        _score.add(_bricks[i][j].getPoints());
                    }
                    // Gör bara en kollision
                    return;
                }
            }
        }
    }

    private boolean noBricks() {
        for (int i = 0; i < (brickRows); i++) {
            for (int j = 0; j < brickColumns; j++) {
                if (_bricks[i][j].isNotRemoved()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void run() {
        _dp.addMouseMotionListener(this);
        _dp.addMouseListener(this);

        t.start();
    }

    public static void main(String[] args) {
        GameBoard g = new GameBoard();
        g.run();
    }
}
