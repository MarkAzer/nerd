package model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

public class StraightRoad extends Road {
	private Point2D.Float startingPoint, endingPoint;
	private final int[][] perpenMat = new int[][] { { 0, -1 }, { 1, 0 } };

	// can only hold straight lanes
	// even and odd lane

	public StraightRoad(Point2D.Float startingPoint, Point2D.Float endingPoint,
			int numAddLane, int numSubLane) {
		super(Road.STRAIGHT_LANE);
		if (startingPoint.x < endingPoint.x ||startingPoint.y<endingPoint.y)
			setUpLanes(startingPoint, endingPoint, numAddLane, numSubLane);
		else if(startingPoint.x>endingPoint.x || startingPoint.y>endingPoint.y){
			setUpLanes(endingPoint, startingPoint, numAddLane, numSubLane);
		}
		else{
			System.out.println("Illegal road");
		}
		
		// TODO Auto-generated constructor stub
	}

	private void setUpLanes(Point2D.Float startingPoint,
			Point2D.Float endingPoint, int numAddLane, int numSubLane) {
		// TODO Auto-generated method stub
		Point2D.Float vector = new Point2D.Float(endingPoint.x
				- startingPoint.x, endingPoint.y - startingPoint.y);
		float vectorLength = (float) Math.sqrt(Math.pow(vector.x, 2.0)
				+ Math.pow(vector.y, 2.0));
		Point2D.Float normalVector = new Point2D.Float(vector.x / vectorLength,
				vector.y / vectorLength);
		Point2D.Float perpenVector = new Point2D.Float(normalVector.x
				* perpenMat[0][0] + normalVector.x * perpenMat[1][0],
				normalVector.y * perpenMat[0][1] + normalVector.y
						* perpenMat[1][1]);
		Point2D.Float scaledPerpen = new Point2D.Float(perpenVector.x
				* Road.roadWidth, perpenVector.y * Road.roadWidth);
		Point2D.Float halfScaled = new Point2D.Float(perpenVector.x
				* Road.roadWidth/2, perpenVector.y * Road.roadWidth/2);

		// setting up add lanes
		for (int i = 0; i < numAddLane; i++) {
			int laneNumber = i * 2;
			Point2D.Float newStart = new Point2D.Float(startingPoint.x
					+ halfScaled.x  + i * scaledPerpen.x, startingPoint.y
					+ halfScaled.y  + i * scaledPerpen.y);
			Point2D.Float newEnd = new Point2D.Float(endingPoint.x
					+ halfScaled.x  + scaledPerpen.x * i, endingPoint.y
					+ halfScaled.y  + i * scaledPerpen.y);
			Lane newStraight = new StraightLane(newStart, newEnd, this.roadId);
			this.lanes.put(laneNumber, newStraight);
		}

		// setting up sub lanes
		for (int i = 0; i < numSubLane; i++) {
			int laneNumber = i * 2 + 1;
			Point2D.Float newStart = new Point2D.Float(endingPoint.x
					+ halfScaled.x  + i * scaledPerpen.x, endingPoint.y
					+ halfScaled.y  + i * scaledPerpen.y);

			Point2D.Float newEnd = new Point2D.Float(startingPoint.x
					+ halfScaled.x + i * scaledPerpen.x, startingPoint.y
					+ halfScaled.y  + i * scaledPerpen.y);
		}

	}
}
