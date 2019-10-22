package jdraw.handles;

import jdraw.framework.Figure;
import jdraw.std.AbstractFigureHandle;

import java.awt.*;

public class SouthEastHandle extends AbstractFigureHandle {

    public SouthEastHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        return bottomRight();
    }

    @Override
    protected Point moveOwnerOrigin(int x, int y) {
        return new Point(x,y);
    }

    @Override
    protected Point getOwnerCorner() {
        return topLeft();
    }
}
