package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.std.StdFigure;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class Circle extends StdFigure {

    /**
     * Use the java.awt.Rectangle in order to save/reuse code.
     */
    private final Ellipse2D circle;

    /**
     * Create a new rectangle of the given dimension.
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Circle(int x, int y, int w, int h) {
        circle = new Ellipse2D.Double(x, y, w, h);
    }

    /**
     * Draw the rectangle to the given graphics context.
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

    @Override
    public void move(int dx, int dy) {
        if (dx == 0 && dy == 0) return;
        circle.setFrame(circle.getX()+dx, circle.getY()+dy, circle.getWidth(), circle.getHeight());
        notifyListeners();
    }

    @Override
    public boolean contains(int x, int y) {
        return circle.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return circle.getBounds();
    }

    /**
     * Returns a list of 8 handles for this Rectangle.
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
}
