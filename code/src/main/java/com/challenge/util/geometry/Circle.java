package com.challenge.util.geometry;

public final class Circle {

	public final Vector point;
	public final double radius;

	public Circle(Vector point, double radius) {
		if (!(radius > 0)) throw new IllegalArgumentException("Radius must be positive");
		this.point = point;
		this.radius = radius;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(c: " + point + ", r: " + radius + ")";
	}

	public Vector[] getIntersectionPoints(Circle c2) {
		double d = Math.sqrt(Math.pow(c2.point.x-this.point.x,2) + Math.pow(c2.point.y-this.point.y,2));
		if (d > this.radius + c2.radius){
			return null;
		}
		if (d < Math.abs(this.radius-c2.radius)){
			return null;
		}
		if (d == 0 && this.radius == c2.radius){
			return null;
		}else{
			double a = (Math.pow(this.radius,2)-Math.pow(c2.radius,2)+Math.pow(d,2))/(2*d);
			double h = Math.sqrt(Math.pow(this.radius,2)-Math.pow(a,2));
			double x2 = this.point.x+a*(c2.point.x-this.point.x)/d;
			double y2 = this.point.y+a*(c2.point.y-this.point.y)/d;
			double x3 = x2+h*(c2.point.y-this.point.y)/d;
			double y3 = y2-h*(c2.point.x-this.point.x)/d;

			double x4 = x2-h*(c2.point.y-this.point.y)/d;
			double y4 = y2+h*(c2.point.x-this.point.x)/d;

			Vector[] result = new Vector[2];
			result[0] = new Vector(x3, y3);
			result[1] = new Vector(x4, y4);
			return result;
		}
	}
}
