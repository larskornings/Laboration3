
import java.awt.Color;
import wheels.users.Ellipse;
import java.awt.Rectangle;

public class Ball {

    static int DIAMETER = 15;
    final int SPEED = 1;

    private final Ellipse _ball;
    private int dx = SPEED, dy = -SPEED;

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
        } //else if (by >= GameBoard.DISPLAY_HEIGHT) {
//            by = 0;
//        }

        _ball.setLocation(bx + dx, by + dy);
    }

    public void collision(Bat b) {
        Rectangle ballBounds = _ball.getBounds();
        Rectangle batBounds = b.getBounds();

        if (ballBounds.intersects(batBounds)) {
            Rectangle intRect = ballBounds.intersection(batBounds);
            if (intRect.getWidth() >= intRect.getHeight() && intRect.getMinY() == batBounds.getMinY()) { // Boll träffar översidan
                dy = -SPEED;
            } else if (intRect.getWidth() < intRect.getHeight()) { // Boll träffar höger eller vänster sida
                if (intRect.getMaxX() == batBounds.getMaxX()) {
                    dx = SPEED;
                } else {//if (intRect.getMinX() == batBounds.getMinX()) {
                    dx = -SPEED;
                }
            }
        }
    }

    public boolean collision(Brick b) {
        Rectangle ballBounds = _ball.getBounds();
        Rectangle brickBounds = b.getBounds();

        if (ballBounds.intersects(brickBounds) && b.getColor() != null) {
            checkSideHit(ballBounds, brickBounds);
            b.gotHit();
            if (b.getHits() < 1) {
                b.setColor(null);
            }
            return true;
        }
        return false;
    }

    private void checkSideHit(Rectangle b1, Rectangle b2) {
        Rectangle intRect = b1.intersection(b2);

        if (intRect.getWidth() >= intRect.getHeight()) { // Boll träffar under eller över sida
            dy = -dy;
        } else if (intRect.getWidth() < intRect.getHeight()) { // Boll träffar höger eller vänster sida
            dx = -dx;
        }
    }

    public void setLocation(int x, int y) {
        _ball.setLocation(x, y);
    }

    public Color getColor() {
        return _ball.getColor();
    }

    public void setColor(Color color) {
        _ball.setColor(color);
    }

    public int getX() {
        return _ball.getXLocation();
    }

    public int getY() {
        return _ball.getYLocation();
    }
}
