package model;

import java.util.*;

public abstract class Strategy {
	private Area area;
	private List<Point> route;
	private List<Point> bestRoute;
	private int pointsInBestRoute;
	
	public Strategy(Area area) {
		this.area = new Area(area);
	}
	
	public Area getArea() {
		return area;
	}
	
	public List<Point> getRoute() {
		return route;
	}

	public List<Point> getBestRoute() {
		return bestRoute;
	}

	public int getNumberOfPointsInBestRoute() {
		return pointsInBestRoute;
	}
	
	
	public Point getNextPointWithDecision(Point lastPoint, int decision)
	{
		Point result = new Point(lastPoint.x, lastPoint.y);
		char decisionValue = getValueForDecision(decision);
		
		switch (decisionValue)
		{
		case '^':
			result.x -= 1;
			break;
			
		case '>':
			result.y += 1;
			break;
			
		case 'v':
			result.x += 1;
			break;
			
		case '<':
			result.y -= 1;
			break;
		}
		
		return result;
	}
	
	
	public void addToRouteIfNeeded(int lastDecision, Point p) {
		// TODO: implement
	}
	
	public void removeFromRouteIfNeeded(Point p) {
		// TODO: implement
	}

	
	public abstract String getName();
	
	public abstract int getFirstDecision();
	
	public abstract int getNextDecision(int lastDecision, int step);
	
	public abstract char getValueForDecision(int decision);
	
}
