package jdraw.std;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractShapeDrawTool implements DrawTool {

    /**
     * the image resource path.
     */
    private static final String IMAGES = "/images/";

    /**
     * The context we use for drawing.
     */
    private final DrawContext context;

    /**
     * The context's view. This variable can be used as a shortcut, i.e.
     * instead of calling context.getView().
     */
    private final DrawView view;

    /**
     * Temporary variable.
     * During shape creation this variable refers to the point the
     * mouse was first pressed.
     */
    private Point anchor = null;

    /**
     * Temporary variable. During shape creation (during a
     * mouse down - mouse drag - mouse up cycle) this variable refers
     * to the new shape that is inserted.
     */
    private AbstractShapeFigure newShape = null;

    /**
     * Create a new draw tool for the given context.
     * @param context a context to use this tool in.
     */
    public AbstractShapeDrawTool(DrawContext context) {
        this.context = context;
        this.view = context.getView();
    }

    /**
     * Initializes a new shape object by setting an anchor
     * point where the mouse was pressed. A new shape is then
     * added to the model.
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were pressed.
     *
     * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
     */
    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newShape != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newShape = createShape(x,y);
        view.getModel().addFigure(newShape);
    }

    /**
     * During a mouse drag, the shape will be resized according to the mouse
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
        newShape.setBounds(anchor, new Point(x, y));
        java.awt.Rectangle r = newShape.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    /**
     * When the user releases the mouse, the shape object is updated
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
        newShape = null;
        anchor = null;
        this.context.showStatusText(getName() + " Mode");
    }

    /**
     * Deactivates the current mode by resetting the cursor
     * and clearing the status bar.
     * @see jdraw.framework.DrawTool#deactivate()
     */
    @Override
    public void deactivate() {
        this.context.showStatusText("");
    }

    /**
     * Activates the draw Mode. There will be a
     * specific menu added to the menu bar that provides settings for
     * draw attributes
     */
    @Override
    public void activate() {
        this.context.showStatusText(getName() + " Mode");
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + getIconName()));
    }

    protected abstract String getIconName();

    /**
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @return
     */
    protected abstract AbstractShapeFigure createShape(int x, int y);
}
