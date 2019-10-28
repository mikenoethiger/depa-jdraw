package jdraw.handles;

import jdraw.framework.Figure;
import jdraw.std.AbstractFigureHandle;

import java.awt.*;

public class NorthHandle extends AbstractFigureHandle {

    public NorthHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        double x = getOwner().getBounds().getCenterX();
        double y = getOwner().getBounds().getY();
        return new Point((int)x, (int)y);
    }

    @Override
    protected Point moveOwnerOrigin(int x, int y) {
        return new Point(getOwner().getBounds().x, y);
    }

    @Override
    protected Point getOwnerCorner() {
    	// XXX ist das nicht bottomright()?
        Rectangle r = getOwner().getBounds();
        return new Point(r.x+r.width, r.y+r.height);
    }
}
