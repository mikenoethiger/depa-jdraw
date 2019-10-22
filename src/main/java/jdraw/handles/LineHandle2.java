package jdraw.handles;

import jdraw.figures.Line;
import jdraw.std.AbstractFigureHandle;

import java.awt.*;

public class LineHandle2 extends AbstractFigureHandle {

    public LineHandle2(Line owner) {
        super(owner);
    }

    @Override
    protected Point moveOwnerOrigin(int x, int y) {
        return new Point(x,y);
    }

    @Override
    protected Point getOwnerCorner() {
        return castOwner().getP1();
    }

    @Override
    public Point getLocation() {
        return castOwner().getP2();
    }

    private Line castOwner() {
        return (Line) getOwner();
    }

}
