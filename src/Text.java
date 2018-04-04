
import java.awt.*;
import javax.swing.*;
import wheels.users.Frame;

public final class Text {

    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_BORDER_WIDTH = 30;

    private final JTextArea _text;
    private final int _borderWidth;

    public Text(int size, Color color) {
        _borderWidth = DEFAULT_BORDER_WIDTH;

        _text = new JTextArea("");
        _text.setEditable(false);
        _text.setBackground(Frame._dp.getBackground());

        Font font = new Font("Helvetica", Font.BOLD, size);
        _text.setFont(font);
        _text.setForeground(color);

        setWidth(DEFAULT_WIDTH);

        Frame._dp.add(_text);

    }

    public void setLocation(int x, int y) {
        _text.setLocation(x, y);
    }

    public void setSize(Dimension d) {
        int newTWidth = d.width - 2 * _borderWidth;
        int newTHeight = d.height - 2 * _borderWidth;

        Dimension prefD = _text.getPreferredScrollableViewportSize();

        if (newTHeight > prefD.height) {
            newTHeight = prefD.height;
        }

        _text.setSize(new Dimension(newTWidth, newTHeight));
    }

    public void setWidth(int width) {
        Dimension d = _text.getPreferredScrollableViewportSize();
        setSize(new Dimension(d.width + 2 * _borderWidth,
                d.height + 2 * _borderWidth));
    }

    public void setText(String text) {
        _text.setText(text);
        setWidth(getWidth());
    }

    public int getWidth() {
        return _text.getWidth();
    }
}
