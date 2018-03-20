
import java.awt.Color;
import java.awt.Rectangle;
import wheels.users.RoundedRectangle;

public abstract class Brick {

    private final RoundedRectangle _brick;
    final static int WIDTH = 40;
    final static int HEIGHT = 20;
    private int _hits;

    public Brick(int x, int y, int hits) {
        this(x, y, hits, Color.YELLOW);
    }

    public Brick(int x, int y, int hits, Color color) {
        _brick = new RoundedRectangle();
        _brick.setFillColor(color);
        _brick.setFrameColor(Color.BLACK);
        _brick.setLocation(x, y);
        _brick.setSize(WIDTH, HEIGHT);
        _hits = hits;
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

    public void gotHit() {
        _hits--;
        
        if(_hits == 2) {
            _brick.setFillColor(Color.GRAY);
        }
        if(_hits == 1) {
            _brick.setFillColor(Color.LIGHT_GRAY);
        }

        
    }
}
