package model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class BezierCurve extends Lane {
	private Point2D.Float[] controlPoints;

	public BezierCurve() {

	}

	@Override
	public float calculateLaneSpan() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point2D.Float nextPosition(Car car, float targetDistance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float calculateDistance(Float pointA, Float pointB) {
		// TODO Auto-generated method stub
		return 0;
	}

}
