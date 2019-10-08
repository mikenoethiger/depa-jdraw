package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.std.AbstractShapeDrawTool;
import jdraw.std.AbstractShapeFigure;

public class LineTool extends AbstractShapeDrawTool {

    /**
     * Create a new line tool for the given context.
     * @param context a context to use this tool in.
     */
    public LineTool(DrawContext context) {
        super(context);
    }

    @Override
    protected String getIconName() {
        return "line.png";
    }

    @Override
    public String getName() {
        return "line";
    }

    @Override
    protected AbstractShapeFigure createShape(int x, int y) {
        return new Line(x, y, x, y);
    }
}
