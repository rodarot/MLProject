package com.challenge.util.geometry;

import static java.lang.Math.*;

import java.io.Serializable;

public final class Vector {

	public final double x;
	public final double y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + x + ", " + y + ")";
	}

}
