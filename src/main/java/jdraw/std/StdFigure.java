package jdraw.std;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class StdFigure implements Figure {

    private final Set<FigureListener> figureListeners = new CopyOnWriteArraySet<>();

    @Override
    public void addFigureListener(FigureListener listener) {
        figureListeners.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        figureListeners.remove(listener);
    }

    protected void notifyListeners() {
        for (FigureListener fl : figureListeners) {
            fl.figureChanged(new FigureEvent(this));
        }
    }

    @Override
    public Figure clone() {
        return null;
    }
}
