
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import wheels.users.RoundedRectangle;

public class Text extends RoundedRectangle {

    private JTextArea _text;
    private int _borderWidth, _vGap;

    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_BORDER_WIDTH = 30;

    public Text(String text) {
        this(text, wheels.users.Frame._dp, 40, Color.RED);
    }

    public Text(String text, int size, Color color) {
        this(text, wheels.users.Frame._dp, size, color);
    }

    public Text(String text, wheels.etc.DrawingPanel dp, int size, Color color) {
        super(dp);

        _borderWidth = DEFAULT_BORDER_WIDTH;
        _vGap = _borderWidth;

        _text = new JTextArea(text);
        _text.setEditable(false);

        _text.setBackground(_dp.getBackground());

        Font font = new Font("Helvetica", Font.BOLD, size);
        _text.setFont(font);
        _text.setForeground(color);

        super.setFrameColor(null);
        setFillColorLocal(null);
        setLocationLocal(new java.awt.Point(0, 0));
        setWidthLocal(DEFAULT_WIDTH);

        showLocal();

    }

    @Override
    public void setLocation(java.awt.Point p) {
        setLocationLocal(p);
    }

    private void setLocationLocal(java.awt.Point p) {
        if (null != _text) {
            _text.setLocation(new java.awt.Point(p.x + _borderWidth,
                    p.y + _vGap));
        }
        super.setLocation(p);
    }

    @Override
    public void setSize(java.awt.Dimension d) {
        setSizeLocal(d);
    }

    private void setSizeLocal(java.awt.Dimension d) {
        if (null != _text) {
            int newTWidth = d.width - 2 * _borderWidth;

            _text.setSize(new java.awt.Dimension(newTWidth, 1));

            java.awt.Dimension prefD = _text.getPreferredScrollableViewportSize();

            int newTHeight = d.height - 2 * _borderWidth;

            if (newTHeight > prefD.height) {
                newTHeight = prefD.height;

                _vGap = (d.height - prefD.height) / 2;
            } else {

                _vGap = _borderWidth;
            }

            _text.setSize(new java.awt.Dimension(newTWidth, newTHeight));

            setLocationLocal(getLocation());
        }

        super.setSize(d);

    }

    public void setWidth(int width) {
        setWidthLocal(width);
    }

    private void setWidthLocal(int width) {
        _text.setSize(new java.awt.Dimension(width - 2 * _borderWidth,
                1));
        java.awt.Dimension d = _text.getPreferredScrollableViewportSize();
        setSizeLocal(new java.awt.Dimension(d.width + 2 * _borderWidth,
                d.height + 2 * _borderWidth));
    }

    @Override
    public void show() {
        showLocal();
    }

    private void showLocal() {
        if (null != _text) {
            _dp.add(_text);
        }
        super.show();
    }

    @Override
    public void hide() {
        if (null != _text) {
            _dp.remove(_text);
        }
        super.hide();
    }

    public void setBorderWidth(int width) {
        //System.out.println("setBorderWidth " + width);
        if (width >= 0) {
            _borderWidth = width;
            setSize(getSize());
            setLocation(getLocation());
        }
    }

    @Override
    public void setColor(java.awt.Color c) {
        if (null != _text) {
            _text.setBackground(c);
        }
        super.setColor(c);
    }

    @Override
    public void setFillColor(java.awt.Color c) {
        setFillColorLocal(c);
    }

    private void setFillColorLocal(java.awt.Color c) {
        if (null != _text) {
            _text.setBackground(c);
        }
        super.setFillColor(c);
    }

    public void setText(String text) {
        _text.setText(text);
        setWidth(getWidth());
    }

    @Override
    public void actualPaint(java.awt.Graphics2D g) {
        super.actualPaint(g);
        java.awt.Stroke oldS = g.getStroke();
        g.setStroke(new java.awt.BasicStroke(getFrameThickness()));
        g.setColor(getFrameColor());
        g.setStroke(oldS);
    }

    @Override
    public java.awt.Rectangle getBounds() {
        java.awt.Rectangle b = _dp.getBounds();
        b.grow(6, 6);

        return b;
    }

    @Override
    public void setRotation(int degrees) {
    }
}
