package jdraw.std;

import java.awt.*;

public abstract class AwtFigure extends StdFigure {

    @Override
    public boolean contains(int x, int y) {
        return getShape().contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return getShape().getBounds();
    }

    protected abstract Shape getShape();
}
