
import java.awt.Color;
import wheels.users.Ellipse;
import java.awt.Rectangle;

public class Ball {

    public final static int DIAMETER = 15;

    private final int SPEED = 1;
    private int dx = SPEED, dy = -SPEED;

    private final Ellipse _ball;

    public Ball(int x, int y) {
        _ball = new Ellipse();
        _ball.setColor(Color.RED);
        _ball.setLocation(x, y);
        _ball.setSize(DIAMETER, DIAMETER);
    }

    public void move() {
        int bx = _ball.getXLocation(), by = _ball.getYLocation();

        if (bx <= 0) {
            dx = SPEED;
        } else if (bx >= (GameBoard.DISPLAY_WIDTH - DIAMETER)) {
            dx = -SPEED;
        }

        if (by <= 0) {
            dy = SPEED;
        }

        _ball.setLocation(bx + dx, by + dy);
    }

    public void collisionWithBat(Bat b) {
        Rectangle ballBounds = _ball.getBounds();
        Rectangle batBounds = b.getBounds();

        if (ballBounds.intersects(batBounds)) {
            Rectangle intRect = ballBounds.intersection(batBounds);
            if (intRect.getWidth() >= intRect.getHeight() && intRect.getMinY() == batBounds.getMinY()) { // Boll träffar översidan
                dy = -SPEED;
            } else { // Boll träffar höger eller vänster sida
                if (intRect.getMaxX() == batBounds.getMaxX()) { //Bollen träffar höger sida
                    dx = SPEED;
                } else { //Bollen träffar vänster sida      
                    dx = -SPEED;
                }
            }
        }
    }

    public boolean collisionWithBrick(Brick b) {
        Rectangle ballBounds = _ball.getBounds();
        Rectangle brickBounds = b.getBounds();

        if (ballBounds.intersects(brickBounds) && b.isNotRemoved()) {
            checkHit(ballBounds, brickBounds);
            if (b.gotHit()) {
                b.remove();
            }
            return true;
        } else {
            return false;
        }
    }

    private void checkHit(Rectangle b1, Rectangle b2) {
        Rectangle intRect = b1.intersection(b2);

        if (intRect.getWidth() >= intRect.getHeight()) { // Boll träffar under eller över sida
            dy = -dy;
        } else if (intRect.getWidth() < intRect.getHeight()) { // Boll träffar höger eller vänster sida
            dx = -dx;
        }
    }

    public void followBat(Bat bat) {
        int ballOffsetX = (Bat.WIDTH / 2) - Ball.DIAMETER / 2;
        int ballOffsetY = Ball.DIAMETER + 3; // To not be on the bat

        int x = bat.getX() + ballOffsetX;
        int y = bat.getY() - ballOffsetY;

        _ball.setLocation(x, y);
    }

    public void removeBall() {
        _ball.setColor(null);
    }

    public int getY() {
        return _ball.getYLocation();
    }
}