
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import wheels.users.RoundedRectangle;

/**
 * Subclass of RoundedRectangle that will display a string, like text area.
 *
 * @author John Goodwin (<a href="mailto:jgoodwin@cs.brown.edu">jgoodwin</a>)
 */
public class Text extends RoundedRectangle {

    private JTextArea _text;
    private int _borderWidth, _vGap;

    public static final int DEFAULT_WIDTH = 200;
    public static final int DEFAULT_BORDER_WIDTH = 30;

    /**
     * Constructs a text in the wheels.users.Frame's DrawingPanel.
     *
     * @param text
     */
    public Text(String text) {
        this(text, wheels.users.Frame._dp, 40, Color.RED);
    }
    
    public Text(String text, int size, Color color) {
        this(text, wheels.users.Frame._dp, size, color);
    }

    /**
     * Constructs a text displaying the specified String in the
     * passed-in DrawingPanel.
     *
     * @param text the String to display
     * @param dp the DrawingPanel in which the bubble will be drawn
     * @param size
     * @param color
     */
    public Text(String text, wheels.etc.DrawingPanel dp, int size, Color color) {

        super(dp);

        _borderWidth = DEFAULT_BORDER_WIDTH;
        _vGap = _borderWidth;

        // creates the text display
        _text = new JTextArea(text);
        _text.setEditable(false);
        // defaults to same color as background, so appears transparent
        _text.setBackground(_dp.getBackground());
        //_text.setBackground(Color.MAGENTA);

        Font font = new Font("Helvetica", Font.BOLD, size);
        _text.setFont(font);
        _text.setForeground(color);

        super.setFrameColor(null);
        setFillColorLocal(null);
        setLocationLocal(new java.awt.Point(0, 0));
        setWidthLocal(DEFAULT_WIDTH);

        showLocal();

    }

    /**
     * Sets the location of the text. Overridden to position the
     * text box within the frame. (Calls to <code>setLocation(int, int)</code>
     * forward to <code>setLocation(java.awt.Point)</code>, so either one will
     * work correctly.)
     *
     * @param p
     */
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

    /**
     * Sets the size of the text. NOTE: setting an explicit size
     * for the bubble may cut off some of the text. Use
     * <code>setWidth(int)</code> to specify a width for the bubble while
     * maintaining view of all the text.
     *
     * @param d
     */
    @Override
    public void setSize(java.awt.Dimension d) {
        setSizeLocal(d);
    }

    private void setSizeLocal(java.awt.Dimension d) {

        if (null != _text) {
            // center the text vertically if there's room
            int newTWidth = d.width - 2 * _borderWidth;

            // first set the width with any height so we can find
            // out the optimal height for the given width
            _text.setSize(new java.awt.Dimension(newTWidth, 1));

            // find out the optimal dimension
            java.awt.Dimension prefD
                    = _text.getPreferredScrollableViewportSize();

            // if the passed-in height is greater than the optimal height
            // for the text box...
            int newTHeight = d.height - 2 * _borderWidth;
            if (newTHeight > prefD.height) {
                // set the height to the optimal height (visually the same, but
                // prevents the empty part of the text box from overlapping
                // the frame)
                newTHeight = prefD.height;
                // set the vertical borders so the text is centered vertically
                _vGap = (d.height - prefD.height) / 2;
            } else {
                // otherwise, vert gap is the same as horizontal gap
                _vGap = _borderWidth;
            }

            // resize the text box
            _text.setSize(new java.awt.Dimension(newTWidth, newTHeight));

            // update location to account for possible changes in vertical
            // spacing
            setLocationLocal(getLocation());

            //System.out.println("set text dim to "+newTWidth+"x" + newTHeight);
        }

        // actually resize frame and repaint
        super.setSize(d);

    }

    /**
     * Sizes the text to the given width, but maintains full view
     * of the contents by adjusting the height if necessary.
     *
     * @param width
     */
    public void setWidth(int width) {
        setWidthLocal(width);
    }

    private void setWidthLocal(int width) {
        // set to any height so we can find out the optimal height for
        // the given width
        _text.setSize(new java.awt.Dimension(width - 2 * _borderWidth,
                1));
        //set size to the optimal dimension
        java.awt.Dimension d = _text.getPreferredScrollableViewportSize();
        setSizeLocal(new java.awt.Dimension(d.width + 2 * _borderWidth,
                d.height + 2 * _borderWidth));
    }

    /**
     * Graphically shows the text.
     */
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

    /**
     * Graphically hides the text.
     */
    @Override
    public void hide() {
        if (null != _text) {
            _dp.remove(_text);
        }
        super.hide();
    }

    /**
     * Changes the size of the border of whitespace between the text area and
     * its frame.
     *
     * @param width
     */
    public void setBorderWidth(int width) {
        //System.out.println("setBorderWidth " + width);
        if (width >= 0) {
            _borderWidth = width;
            setSize(getSize());
            setLocation(getLocation());
        }
    }

    /**
     * Set the background and frame color of the text.
     *
     * @param c
     */
    @Override
    public void setColor(java.awt.Color c) {
        if (null != _text) {
            _text.setBackground(c);
        }
        super.setColor(c);
    }

    /**
     * Set the background color of the text.
     *
     * @param c
     */
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

    /**
     * Makes the bubble display the passed-in string. Adjusts height so the all
     * of new string is properly displayed.
     *
     * @param text
     */
    public void setText(String text) {
        _text.setText(text);
        setWidth(getWidth());
    }

    /**
     * Normal do not need to worry about this!
     *
     * Does the actual work to paint the text.
     */
    @Override
    public void actualPaint(java.awt.Graphics2D g) {
        super.actualPaint(g);
        java.awt.Stroke oldS = g.getStroke();
        g.setStroke(new java.awt.BasicStroke(getFrameThickness()));
        g.setColor(getFrameColor());
        g.setStroke(oldS);
    }

    /**
     * Returns the bounds of the text.
     *
     * @return
     */
    @Override
    public java.awt.Rectangle getBounds() {
        java.awt.Rectangle b = _dp.getBounds();
        b.grow(6, 6);

        return b;
    }

    /**
     * Overridden to do nothing.
     */
    @Override
    public void setRotation(int degrees) {
    }

}
