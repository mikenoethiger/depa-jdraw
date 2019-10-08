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
        return "Circle";
    }

    @Override
    protected AbstractShapeFigure createShape(int x, int y) {
        return new Circle(x, y, 0, 0);
    }
}
