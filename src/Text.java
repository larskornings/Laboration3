
import java.awt.*;
import javax.swing.*;
import wheels.users.Frame;

public final class Text {

    private final JTextArea _text;

    private final int _width, _height;

    public Text(int fontSize, int width, int height, Color color) {
        _text = new JTextArea("");
        _text.setEditable(false);
        _text.setBackground(Frame._dp.getBackground());
        _text.setFont(new Font("Helvetica", Font.BOLD, fontSize));
        _text.setForeground(color);
        _text.setSize(0, 0);   // Kommer inte att ritas ut
        _width = width;
        _height = height;

        Frame._dp.add(_text);
    }

    public void setLocation(int x, int y) {
        _text.setLocation(x, y);
    }

    public void setText(String text) {
        _text.setText(text);
        _text.setSize(_width, _height);
    }

    public int getWidth() {
        return _text.getWidth();
    }
}
