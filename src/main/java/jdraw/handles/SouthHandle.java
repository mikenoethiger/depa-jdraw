package jdraw.handles;

import jdraw.framework.Figure;
import jdraw.std.AbstractFigureHandle;

import java.awt.*;

public class SouthHandle extends AbstractFigureHandle {

    public SouthHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point((int)r.getCenterX(), r.y + r.height);
    }

    @Override
    protected Point moveOwnerOrigin(int x, int y) {
        Rectangle r = getOwner().getBounds();
        return new Point((int)(r.getX()+r.getWidth()), y);
    }

    @Override
    protected Point getOwnerCorner() {
        return getOwner().getBounds().getLocation();
    }
}
