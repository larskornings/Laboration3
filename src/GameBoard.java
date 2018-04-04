
import javax.swing.Timer;
import java.awt.event.*;

public class GameBoard extends wheels.users.Frame implements ActionListener, MouseMotionListener, MouseListener {

    public final static int DISPLAY_WIDTH = 700;
    public final static int DISPLAY_HEIGHT = 500;

    private final int DELAY = 3;
    private final int brick3Rows = 1, brick2Rows = 1, brick1Rows = 5;
    private final int brickRows = brick1Rows + brick2Rows + brick3Rows;
    private final int brickColumns = 12;
    private final int brickStartX = 104, brickStartY = 80;

    private final Brick[][] _bricks = new Brick[brickRows][brickColumns];

    private final Timer t;
    private final Ball _ball;
    private final Bat _bat;
    private final Score _score;

    private boolean startMove = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (startMove == true) {
            _ball.move();
            _score.updateScore();
            if (noBricks()) {
                _score.gameWon();
                t.stop();
            }
            if (_ball.getY() > DISPLAY_HEIGHT / 2 && _ball.getY() < DISPLAY_HEIGHT) {
                _ball.collision(_bat);
            } else if (_ball.getY() >= DISPLAY_HEIGHT) {
                _score.gameOver();
                t.stop();
            } else {
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
        startMove = true;
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
        t = new Timer(DELAY, this);

        int batX = DISPLAY_WIDTH / 2;
        int batY = DISPLAY_HEIGHT * 7 / 8;
        _bat = new Bat(batX, batY);

        int ballX = _bat.getX() + Bat.WIDTH / 2 - Ball.DIAMETER / 2;
        int ballY = _bat.getY() - Ball.DIAMETER - 3;
        _ball = new Ball(ballX, ballY);

        _score = new Score();
        _score.setLocation(10, DISPLAY_HEIGHT - 30);
    }

    public void initGame() {
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

    public void checkBrickCollision() {
        for (int i = 0; i < (brickRows); i++) {
            for (int j = 0; j < brickColumns; j++) {
                if (_ball.collision(_bricks[i][j])) {
                    if (_bricks[i][j].getColor() == null) {
                        if (_bricks[i][j] instanceof Brick3) {
                            _score.add(_bricks[i][j].getPoints());
                        } else if (_bricks[i][j] instanceof Brick2) {
                            _score.add(_bricks[i][j].getPoints());
                        } else {
                            _score.add(_bricks[i][j].getPoints());
                        }
                    }
                    // Gör bara en kollision
                    return;
                }
            }
        }
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

    public void run() {
        _dp.addMouseMotionListener(this);
        _dp.addMouseListener(this);

        initGame();

        t.start();
    }

    public static void main(String[] args) {
        GameBoard g = new GameBoard();
        g.run();
    }
}
