
import java.awt.Color;

public class Brick3 extends Brick{

    public Brick3(int x, int y) {
        super(x, y, 3, Color.DARK_GRAY);
        points = 3;
    }
    
    @Override
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
