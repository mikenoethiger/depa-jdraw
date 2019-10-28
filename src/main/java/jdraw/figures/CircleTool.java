package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.std.AbstractShapeDrawTool;
import jdraw.std.AbstractShapeFigure;

public class CircleTool extends AbstractShapeDrawTool {

    public CircleTool(DrawContext context) {
        super(context);
    }

    @Override
    protected String getIconName() {
        return "oval.png";
    }

    @Override
    public String getName() {
    	// XXX wenn die Methode jeweils immer nur eine Konstante zurück gibt, dann könnte dieser WErt auch mit dem Konstruktor
    	//     der Basisklasse übergeben werden. Mit Ihrem Ansatz haben SIe nun die Möglichkeit, den Namen dynamisch anzupassen.
        return "Circle";
    }

    @Override
    protected AbstractShapeFigure createShape(int x, int y) {
        return new Circle(x, y, 0, 0);
    }
}
