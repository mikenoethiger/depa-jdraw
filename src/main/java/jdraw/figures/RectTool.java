/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.std.AbstractShapeDrawTool;
import jdraw.std.AbstractShapeFigure;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 */
public class RectTool extends AbstractShapeDrawTool {

	public RectTool(DrawContext context) {
		super(context);
	}

	@Override
	protected String getIconName() {
		return "rectangle.png";
	}

	@Override
	public String getName() {
		return "Rectangle";
	}

	@Override
	protected AbstractShapeFigure createShape(int x, int y) {
		return new Rect(x, y, 0, 0);
	}
}
