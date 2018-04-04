
import java.awt.Color;
import wheels.users.Rectangle;

public class Bat {

    public final static int WIDTH = 80;
    public final static int HEIGHT = 10;

    private final Rectangle _bat;

    public Bat(int x, int y) {
        _bat = new Rectangle(Color.BLUE);
        _bat.setLocation(x - (WIDTH / 2), y);
        _bat.setSize(WIDTH, HEIGHT);
    }

    public void setLocation(int x, int y) {
        if (x > GameBoard.DISPLAY_WIDTH - WIDTH) {
            x = GameBoard.DISPLAY_WIDTH - WIDTH;
        }
        _bat.setLocation(x, y);
    }

    public int getX() {
        return _bat.getXLocation();
    }

    public int getY() {
        return _bat.getYLocation();
    }

    public java.awt.Rectangle getBounds() {
        return _bat.getBounds();
    }

}
