package jdraw.handles;

import jdraw.framework.Figure;
import jdraw.std.AbstractFigureHandle;

import java.awt.*;

public class NorthWestHandle extends AbstractFigureHandle {

    public NorthWestHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        return topLeft();
    }

    @Override
    protected Point moveOwnerOrigin(int x, int y) {
        return new Point(x,y);
    }

    @Override
    protected Point getOwnerCorner() {
        return bottomRight();
    }
}
