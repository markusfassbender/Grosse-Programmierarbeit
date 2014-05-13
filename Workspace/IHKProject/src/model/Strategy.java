package model;

import java.util.*;

public abstract class Strategy {
	private Area area;
	private Area bestArea;
	
	private List<Point> route;
	private List<Point> bestRoute;
	
	private int pointsInBestRoute;
	
	public Strategy(Area area) {
		this.area = new Area(area);
		this.route = new ArrayList<Point>();
		this.pointsInBestRoute = 0;
		saveCurrentRouteAndAreaAsBest();
	}
	
	public Area getArea() {
		return area;
	}
	
	public Area getBestArea() {
		return bestArea;
	}
	
	public List<Point> getRoute() {
		return route;
	}

	public List<Point> getBestRoute() {
		if(bestRoute.size() == 0) {
			return bestRoute;
		}
		
		List<Point> optimizedRoute = new ArrayList<Point>(bestRoute);
		boolean allXEqual, allYEqual;
		Point last, current, next;
		
		for(int i = 1; i < bestRoute.size() - 1; ++i) {
			last = bestRoute.get(i - 1);
			current = bestRoute.get(i);
			next = bestRoute.get(i + 1);
			
			allXEqual = (last.x == current.x && last.x == next.x);
			allYEqual = (last.y == current.y && last.y == next.y);
			
			if(allXEqual || allYEqual) {
				// falls sich x oder y nicht geändert haben, kann das mittlere element entfernt werden
				optimizedRoute.remove(current);
			}
		}
		
		return optimizedRoute;
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
		if(!route.contains(p)) {
			route.add(p);
		}
		
		if(pointsInBestRoute < area.numberOfWorkedCells())
		{
			saveCurrentRouteAndAreaAsBest();
			bestArea.setCell(p, 'Z');
		}
	}
	
	private void saveCurrentRouteAndAreaAsBest() {
		bestRoute = new ArrayList<Point>(route);
		bestArea = new Area(area);
		pointsInBestRoute = area.numberOfWorkedCells();
	}
	
	public void removeFromRouteIfNeeded(Point p)
	{
		route.remove(p);
		// TODO: besser remove last element?
	}

	
	public abstract String getName();
	
	public abstract int getFirstDecision();
	
	public abstract int getNextDecision(int lastDecision, int step);
	
	public abstract char getValueForDecision(int decision);
	
}
