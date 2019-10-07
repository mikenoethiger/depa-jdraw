package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.std.StdDrawTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool extends StdDrawTool {

    /**
     * Temporary variable. During line creation (during a
     * mouse down - mouse drag - mouse up cycle) this variable refers
     * to the new line that is inserted.
     */
    private Line newLine = null;

    /**
     * Temporary variable.
     * During line creation this variable refers to the point the
     * mouse was first pressed.
     */
    private Point anchor = null;

    /**
     * Create a new line tool for the given context.
     * @param context a context to use this tool in.
     */
    public LineTool(DrawContext context) {
        super(context);
    }

    /**
     * Initializes a new line object by setting an anchor
     * point where the mouse was pressed. A new line is then
     * added to the model.
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were pressed.
     *
     * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
     */
    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newLine != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newLine = new Line(x, y, 0, 0);
        view.getModel().addFigure(newLine);
    }

    /**
     * During a mouse drag, the line will be resized according to the mouse
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
        newLine.setBounds(anchor, new Point(x, y));
        java.awt.Rectangle r = newLine.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    /**
     * When the user releases the mouse, the line object is updated
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
        newLine = null;
        anchor = null;
        this.context.showStatusText("Line Mode");
    }

    @Override
    protected String getIconName() {
        return "line.png";
    }

    @Override
    public String getName() {
        return "line";
    }
}
