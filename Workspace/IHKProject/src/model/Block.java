package model;

import java.util.*;

public class Block {
	private Point startPoint;
	private Point endPoint;
	
	public Block(Point startPoint, Point endPoint)
	{
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public Point getEndPoint() {
		return endPoint;
	}
	
	public List<Point> getPointsBlocked()
	{
		// TODO implement
		return new ArrayList<Point>();
	}
}
