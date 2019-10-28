package jdraw.std;

import java.awt.*;

public abstract class AbstractShapeFigure extends AbstractFigure {

    @Override
    public boolean contains(int x, int y) {
        return getShape().contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return getShape().getBounds();
    }

    @Override
    public void move(int dx, int dy) {
    	// XXX move wird aber in den Unterklassen Line und Rect überschrieben. Für Line macht das definitiv sinn, aber 
    	//     ich frage mich dann ob diese Methode hier sinnvoll ist.
        if (dx == 0 && dy == 0) return;
        int x1 = (int) getBounds().getX()+dx;
        int y1 = (int) getBounds().getY()+dy;
        int x2 = (int) (x1+getBounds().getWidth());
        int y2 = (int) (y1+getBounds().getHeight());

        Point orig = new Point(x1, y1);
        Point dest = new Point(x2, y2);
        setBounds(orig, dest);
    }

    protected abstract Shape getShape();
    // XXX ich habe das Gefühl, dass man noch viel mehr in dieser Basisklasse implementieren kann, z.B. auch das setBounds
    //     (allerdings nicht für die Linie).
    //     Die Methode draw könnte wie folgt implementiert werden:
    //
	//		    public final void draw(Graphics g) {
	//		    	Graphics2D g2d = (Graphics2D)g;
	//		        g2d.setColor(Color.WHITE);
	//		        g2d.fill(getShape());
	//		        g2d.setColor(Color.BLACK);
	//		        g2d.draw(getShape());
	//		    }
}
