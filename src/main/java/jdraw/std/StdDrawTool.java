package jdraw.std;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

import javax.swing.*;
import java.awt.*;

public abstract class StdDrawTool implements DrawTool {

    /**
     * the image resource path.
     */
    protected static final String IMAGES = "/images/";

    /**
     * The context we use for drawing.
     */
    protected final DrawContext context;

    /**
     * The context's view. This variable can be used as a shortcut, i.e.
     * instead of calling context.getView().
     */
    protected final DrawView view;

    /**
     * Create a new draw tool for the given context.
     * @param context a context to use this tool in.
     */
    public StdDrawTool(DrawContext context) {
        this.context = context;
        this.view = context.getView();
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
}
