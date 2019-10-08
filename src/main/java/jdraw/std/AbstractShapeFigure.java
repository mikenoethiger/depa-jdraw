package jdraw.std;

import java.awt.*;

public abstract class AbstractShapeFigure extends AbstractFigure {

    @Override
    public boolean contains(int x, int y) {
        return getShape().contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return getShape().getBounds();
    }

    @Override
    public void move(int dx, int dy) {
        if (dx == 0 && dy == 0) return;
        int x1 = (int) getBounds().getX()+dx;
        int y1 = (int) getBounds().getY()+dy;
        int x2 = (int) (x1+getBounds().getWidth());
        int y2 = (int) (y1+getBounds().getHeight());

        Point orig = new Point(x1, y1);
        Point dest = new Point(x2, y2);
        setBounds(orig, dest);
    }

    protected abstract Shape getShape();
}
