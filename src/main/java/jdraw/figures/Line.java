package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.std.AwtFigure;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class Line extends AwtFigure {

    /**
     * Use the java.awt.geom.Line2D in order to save/reuse code.
     */
    private final java.awt.geom.Line2D line;

    /**
     * Create a new line of the given dimension.
     * @param x1 the x-coordinate of start point of the line
     * @param y1 the y-coordinate of start point of the line
     * @param x2 the x-coordinate of end point of the line
     * @param y2 the y-coordinate of end point of the line
     */
    public Line(int x1, int y1, int x2, int y2) {
        line = new Line2D.Double(x1, y1, x2, y2);
    }

    /**
     * Draw the line to the given graphics context.
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Line2D l = new Line2D.Double(origin, corner);

        if (!l.equals(line)) {
            line.setLine(origin, corner);
            notifyListeners();
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (dx == 0 && dy == 0) return;
        line.setLine(line.getX1()+dx, line.getY1()+dy, line.getX2()+dx, line.getY2()+dy);
        notifyListeners();
    }

    /**
     * Returns a list of 8 handles for this line.
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }


    @Override
    public Figure clone() {
        return null;
    }

    @Override
    protected Shape getShape() {
        return line;
    }
}
