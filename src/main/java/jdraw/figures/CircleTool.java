package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.std.StdDrawTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CircleTool extends StdDrawTool {

    /**
     * Temporary variable. During circle creation (during a
     * mouse down - mouse drag - mouse up cycle) this variable refers
     * to the new circle that is inserted.
     */
    private Circle newCircle = null;

    /**
     * Temporary variable.
     * During circle creation this variable refers to the point the
     * mouse was first pressed.
     */
    private Point anchor = null;

    /**
     * Create a new circle tool for the given context.
     * @param context a context to use this tool in.
     */
    public CircleTool(DrawContext context) {
        super(context);
    }

    /**
     * Initializes a new circle object by setting an anchor
     * point where the mouse was pressed. A new circle is then
     * added to the model.
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were pressed.
     *
     * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
     */
    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newCircle != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newCircle = new Circle(x, y, 0, 0);
        view.getModel().addFigure(newCircle);
    }

    /**
     * During a mouse drag, the circle will be resized according to the mouse
     * position. The status bar shows the current size.
     *
     * @param x   x-coordinate of mouse
     * @param y   y-coordinate of mouse
     * @param e   event containing additional information about which keys were
     *            pressed.
     *
     * @see jdraw.framework.DrawTool#mouseDrag(int, int, MouseEvent)
     */
    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
        newCircle.setBounds(anchor, new Point(x, y));
        java.awt.Rectangle r = newCircle.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    /**
     * When the user releases the mouse, the circle object is updated
     * according to the color and fill status settings.
     *
     * @param x   x-coordinate of mouse
     * @param y   y-coordinate of mouse
     * @param e   event containing additional information about which keys were
     *            pressed.
     *
     * @see jdraw.framework.DrawTool#mouseUp(int, int, MouseEvent)
     */
    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
        newCircle = null;
        anchor = null;
        this.context.showStatusText("Circle Mode");
    }

    @Override
    protected String getIconName() {
        return "oval.png";
    }

    @Override
    public String getName() {
        return "Circle";
    }

}
