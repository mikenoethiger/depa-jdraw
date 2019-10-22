package jdraw.handles;

import jdraw.framework.Figure;
import jdraw.std.AbstractFigureHandle;

import java.awt.*;

public class EastHandle extends AbstractFigureHandle {

    public EastHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point((int)r.getX()+r.width, (int)r.getCenterY());
    }

    @Override
    protected Point moveOwnerOrigin(int x, int y) {
        return new Point(x, getOwner().getBounds().y+getOwner().getBounds().height);
    }

    @Override
    protected Point getOwnerCorner() {
        return getOwner().getBounds().getLocation();
    }
}
