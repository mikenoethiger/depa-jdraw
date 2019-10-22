package jdraw.handles;

import jdraw.framework.Figure;
import jdraw.std.AbstractFigureHandle;

import java.awt.*;

public class WestHandle extends AbstractFigureHandle {

    public WestHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x, (int) r.getCenterY());
    }

    @Override
    protected Point moveOwnerOrigin(int x, int y) {
        return new Point(x, topLeft().y);
    }

    @Override
    protected Point getOwnerCorner() {
        return bottomRight();
    }
}
