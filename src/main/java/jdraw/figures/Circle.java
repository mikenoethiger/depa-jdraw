package jdraw.figures;

import jdraw.framework.FigureHandle;
import jdraw.std.AbstractShapeFigure;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class Circle extends AbstractShapeFigure {

    /**
     * Use the java.awt.geom.Ellipse2D in order to save/reuse code.
     */
    private final Ellipse2D circle;

    /**
     * Create a new cricle of the given dimension.
     * @param x the x-coordinate of the upper left corner of the cricle
     * @param y the y-coordinate of the upper left corner of the cricle
     * @param w the cricle's width
     * @param h the cricle's height
     */
    public Circle(int x, int y, int w, int h) {
        circle = new Ellipse2D.Double(x, y, w, h);
    }

    /**
     * Draw the cricle to the given graphics context.
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillArc((int) circle.getMinX(), (int) circle.getMinY(), (int) circle.getWidth(), (int) circle.getHeight(), 0, 360);
        g.setColor(Color.BLACK);
        g.drawArc((int) circle.getMinX(), (int) circle.getMinY(), (int) circle.getWidth(), (int) circle.getHeight(), 0, 360);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Ellipse2D circ = new Ellipse2D.Double();
        circ.setFrameFromDiagonal(origin, corner);

        if (!circ.equals(circle)) {
            circle.setFrameFromDiagonal(origin, corner);
            notifyListeners();
        }
    }

    /**
     * Returns a list of 8 handles for this cricle.
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

    @Override
    protected Shape getShape() {
        return circle;
    }
}
