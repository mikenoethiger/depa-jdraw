package jdraw.std;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class AbstractFigure implements Figure {

    private final Set<FigureListener> figureListeners = new CopyOnWriteArraySet<>();
    private final FigureEvent figureEvent = new FigureEvent(this);

    @Override
    public final void addFigureListener(FigureListener listener) {
        figureListeners.add(listener);
    }

    @Override
    public final void removeFigureListener(FigureListener listener) {
        figureListeners.remove(listener);
    }

    protected final void notifyListeners() {
        for (FigureListener fl : figureListeners) {
            fl.figureChanged(figureEvent);
        }
    }

    @Override
    public Figure clone() {
        return null;
    }
}
