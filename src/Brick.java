
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

    public Rectangle getBounds() {
        return _brick.getBounds();
    }

    public boolean isNotRemoved() {
        return _brick.getColor() != null;
    }

    public boolean isRemoved() {
        return _brick.getColor() == null;
    }

    public void remove() {
        _brick.setColor(null);
    }

    public int getPoints() {
        return _points;
    }

    public boolean gotHit() {
        _hits--;
        return _hits < 1;
    }
}
