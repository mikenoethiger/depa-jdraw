/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

import jdraw.framework.*;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 * @author Mike Nöthiger
 *
 */
public class StdDrawModel implements DrawModel {

	private final List<DrawModelListener> listeners = new CopyOnWriteArrayList<>();
	private final Map<Figure, FigureListener> figureListeners = new HashMap<>();
	private final List<Figure> figures = new ArrayList<>();

	@Override
	public void addFigure(Figure f) {
		if (figures.contains(f)) return;
		boolean added = figures.add(f);
		if (added) {
			DrawModelEvent e1 = new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_ADDED);
			notifyListeners(e1);

			FigureListener figureListener = figureEvent -> {
				DrawModelEvent e2 = new DrawModelEvent(this, figureEvent.getFigure(), DrawModelEvent.Type.FIGURE_CHANGED);
				notifyListeners(e2);
			};
			f.addFigureListener(figureListener);
			figureListeners.put(f, figureListener);
		}
	}

	@Override
	public Stream<Figure> getFigures() {
		return figures.stream();
	}

	@Override
	public void removeFigure(Figure f) {
		boolean removed = figures.remove(f);
		if (removed) {
			FigureListener figureListener = figureListeners.get(f);
			f.removeFigureListener(figureListener);
			figureListeners.remove(f);
			notifyListeners(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_REMOVED));
		}
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		// TODO to be implemented  
		System.out.println("StdDrawModel.removeModelChangeListener has to be implemented");
	}

	/** The draw command handler. Initialized here with a dummy implementation. */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * @return the draw command handler.
	 */
	@Override
	public DrawCommandHandler getDrawCommandHandler() {
		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		if (index < 0 || index >= figures.size()) throw new IndexOutOfBoundsException(String.format("given index %s out of range 0..%s", index, figures.size()));
		if (figures.indexOf(f) < 0) throw new IllegalArgumentException();

		figures.remove(f);
		figures.add(index, f);

		notifyListeners(new DrawModelEvent(this, f, DrawModelEvent.Type.DRAWING_CHANGED));
	}

	@Override
	public void removeAllFigures() {
		figures.clear();
		Set<Map.Entry<Figure, FigureListener>> listeners = figureListeners.entrySet();
		for (Map.Entry<Figure, FigureListener> e : listeners) {
			e.getKey().removeFigureListener(e.getValue());
		}
		figureListeners.clear();
		notifyListeners(new DrawModelEvent(this, null, DrawModelEvent.Type.DRAWING_CLEARED));
	}

	private void notifyListeners(DrawModelEvent e) {
		for (DrawModelListener l : listeners) {
			l.modelChanged(e);
		}
	}
}
