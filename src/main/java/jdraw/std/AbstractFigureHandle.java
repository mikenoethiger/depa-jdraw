package jdraw.std;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractFigureHandle implements FigureHandle {

    private static final Cursor CURSOR = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

    private final Figure owner;
    private Point corner;
    private String status;

    public AbstractFigureHandle(Figure owner) {
        this.owner = owner;
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    private Rectangle getSymbol() {
        return new Rectangle(getLocation().x -3, getLocation().y -3, 6, 6);
    }

    @Override
    public void draw(Graphics g) {
        Rectangle r = getSymbol();
        g.setColor(Color.WHITE); g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK); g.drawRect(r.x, r.y, r.width, r.height);
    }

    @Override
    public Cursor getCursor() {
        return CURSOR;
    }

    @Override
    public boolean contains(int x, int y) {
        return getSymbol().contains(x,y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = getOwnerCorner();
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        if (e.getButton() != MouseEvent.BUTTON1) return;
        getOwner().setBounds(moveOwnerOrigin(x,y), corner);
        v.getContext().showStatusText("w: " + getOwner().getBounds().width + "h: " + getOwner().getBounds().height);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
        v.getContext().showStatusText("");

    }

    protected Point topLeft() {
        return getOwner().getBounds().getLocation();
    }

    protected Point bottomRight() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x+r.width, r.y+r.height);
    }

    protected Point topRight() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x+r.width, r.y);
    }

    protected Point bottomLeft() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x, r.y+r.height);
    }

    protected abstract Point moveOwnerOrigin(int x, int y);
    protected abstract Point getOwnerCorner();
}
