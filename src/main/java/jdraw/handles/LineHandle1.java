package jdraw.handles;

import jdraw.figures.Line;
import jdraw.std.AbstractFigureHandle;

import java.awt.*;

public class LineHandle1 extends AbstractFigureHandle {

    public LineHandle1(Line owner) {
        super(owner);
    }

    @Override
    protected Point moveOwnerOrigin(int x, int y) {
        return new Point(x,y);
    }

    @Override
    protected Point getOwnerCorner() {
        return castOwner().getP2();
    }

    @Override
    public Point getLocation() {
        return castOwner().getP1();
    }

    // XXX Sie könnten als Variante auch die Methode getOwner überschreiben 
	//	    @Override
	//	    public Line getOwner() {
	//	    	return (Line)super.getOwner();
	//	    }
    private Line castOwner() {
        return (Line) getOwner();
    }
}
