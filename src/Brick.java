
import java.awt.Color;
import java.awt.Rectangle;
import wheels.users.RoundedRectangle;

public abstract class Brick {

    public final static int WIDTH = 40;
    public final static int HEIGHT = 20;

    protected final RoundedRectangle _brick;

    protected int _hits;
    protected int _points;

    public Brick(int x, int y, int hits, Color color) {
        _brick = new RoundedRectangle();
        _brick.setFillColor(color);
        _brick.setFrameColor(Color.BLACK);
        _brick.setLocation(x, y);
        _brick.setSize(WIDTH, HEIGHT);
        _hits = hits;
        _points = 0;
    }

    public void setLocation(int x, int y) {
        _brick.setLocation(x, y);
    }

    public Rectangle getBounds() {
        return _brick.getBounds();
    }

    public Color getColor() {
        return _brick.getColor();
    }

    public void setColor(Color color) {
        _brick.setColor(color);
    }

    public int getHits() {
        return _hits;
    }

    public int getPoints() {
        return _points;
    }

    public void gotHit() {
        _hits--;
    }
}
