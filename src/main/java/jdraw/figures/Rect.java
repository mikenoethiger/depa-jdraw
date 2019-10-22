/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.handles.*;
import jdraw.std.AbstractShapeFigure;

/**
 * Represents rectangles in JDraw.
 * 
 * @author Christoph Denzler
 *
 */
public class Rect extends AbstractShapeFigure {
	private static final long serialVersionUID = 9120181044386552132L;

	/**
	 * Use the java.awt.Rectangle in order to save/reuse code.
	 */
	private final Rectangle rectangle;
	
	/**
	 * Create a new rectangle of the given dimension.
	 * @param x the x-coordinate of the upper left corner of the rectangle
	 * @param y the y-coordinate of the upper left corner of the rectangle
	 * @param w the rectangle's width
	 * @param h the rectangle's height
	 */
	public Rect(int x, int y, int w, int h) {
		rectangle = new Rectangle(x, y, w, h);
	}

	/**
	 * Draw the rectangle to the given graphics context.
	 * @param g the graphics context to use for drawing.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		g.setColor(Color.BLACK);
		g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}
	
	@Override
	public void setBounds(Point origin, Point corner) {
		Rectangle rect = new Rectangle();
		rect.setFrameFromDiagonal(origin, corner);

		if (!rect.equals(rectangle)) {
			rectangle.setFrameFromDiagonal(origin, corner);
			notifyListeners();
		}
	}

	@Override
	public void move(int dx, int dy) {
		if (dx == 0 && dy == 0) return;
		rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
		notifyListeners();
	}

	/**
	 * Returns a list of 8 handles for this Rectangle.
	 * @return all handles that are attached to the targeted figure.
	 * @see jdraw.framework.Figure#getHandles()
	 */	
	@Override
	public List<FigureHandle> getHandles() {
		List<FigureHandle> handles = new ArrayList<>();
		handles.add(new NorthHandle(this));
		handles.add(new EastHandle(this));
		handles.add(new SouthHandle(this));
		handles.add(new WestHandle(this));
		handles.add(new NorthEastHandle(this));
		handles.add(new NorthWestHandle(this));
		handles.add(new SouthEastHandle(this));
		handles.add(new SouthWestHandle(this));
		return handles;
	}

	@Override
	public Figure clone() {
		return null;
	}

	@Override
	protected Shape getShape() {
		return rectangle;
	}
}
