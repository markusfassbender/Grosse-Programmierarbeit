package model;

import java.util.*;

public abstract class Strategy
{
	/**
	 * Die Fl�che auf der operiert wird.
	 */
	private Area area;

	/**
	 * Die am Besten versiegelte Fl�che.
	 */
	private Area bestArea;

	/**
	 * Die Route die w�hrend der Operationen erstellt wird.
	 */
	private List<Point> route;
	/**
	 * Die beste Route, die also am meisten Parzellen abdeckt.
	 */
	private List<Point> bestRoute;

	/**
	 * Die Anzahl an abgedeckten Parzellen der besten Route.
	 */
	private int pointsInBestRoute;

	/**
	 * Konstruktor.
	 * 
	 * @param area
	 *            Die Fl�che auf der operiert werden kann
	 */
	public Strategy(Area area)
	{
		this.area = new Area(area);
		this.route = new ArrayList<Point>();
		this.pointsInBestRoute = 0;
		saveCurrentRouteAndAreaAsBest();
	}

	/**
	 * Gibt die Fl�che auf der operiert wird.
	 * 
	 * @return Die Fl�che
	 */
	public Area getArea()
	{
		return area;
	}

	/**
	 * Gibt die am Besten versiegelte Fl�che.
	 * 
	 * @return Die beste Fl�che
	 */
	public Area getBestArea()
	{
		return bestArea;
	}

	/**
	 * Gibt die atuelle Route.
	 * 
	 * @return Die aktuelle Route
	 */
	public List<Point> getRoute()
	{
		return route;
	}

	/**
	 * Gibt die Route, die am meisten Parzellen versiegelt hat. Dabei werden
	 * Parzellen, die nicht f�r das nachvollziehen der Route relevant sind,
	 * herausgek�rzt.
	 * 
	 * @return Die beste Route
	 */
	public List<Point> getBestRoute()
	{
		List<Point> optimizedRoute = new ArrayList<Point>(bestRoute);
		boolean allXEqual, allYEqual;
		Point last, current, next;

		for (int i = 1; i < bestRoute.size() - 1; ++i)
		{
			last = bestRoute.get(i - 1);
			current = bestRoute.get(i);
			next = bestRoute.get(i + 1);

			allXEqual = (last.x == current.x && last.x == next.x);
			allYEqual = (last.y == current.y && last.y == next.y);

			if (allXEqual || allYEqual)
			{
				// falls sich x oder y nicht ge�ndert haben, kann das mittlere
				// element entfernt werden
				optimizedRoute.remove(current);
			}
		}

		return optimizedRoute;
	}

	/**
	 * Gibt die Anzahl an vesiegelten Parzellen der besten Route.
	 * 
	 * @return Die Anzahl
	 */
	public int getNumberOfPointsInBestRoute()
	{
		return pointsInBestRoute;
	}

	/**
	 * Gibt die n�chste Parzelle, abh�ngig von der getroffenen
	 * Richtungsentscheidung. Die n�chste Parzelle kann auch au�erhalb des
	 * Feldes liegen.
	 * 
	 * @param currentPoint
	 *            Die aktuelle Parzelle
	 * @param decision
	 *            Die Richtungsentscheidung
	 * @return Die n�chste Parzelle
	 */
	public Point getNextPointWithDecision(Point currentPoint, int decision)
	{
		Point result = new Point(currentPoint.x, currentPoint.y);
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

	/**
	 * F�ge Parzelle zur aktuellen Route hinzu, falls diese noch nicht darin
	 * existiert.
	 * 
	 * @param p
	 *            Die Parzelle
	 */
	public void addToRouteIfNeeded(Point p)
	{
		if (!route.contains(p))
		{
			route.add(p);
		}

		if (pointsInBestRoute < area.numberOfWorkedCells())
		{
			saveCurrentRouteAndAreaAsBest();
			// setze die aktuelle Zelle als Ziel, da dies die letzte Parzelle
			// der Route ist.
			bestArea.setCell(p, 'Z');
		}
	}

	/**
	 * Speichert die aktuelle Route und Fl�che als die Besten.
	 */
	private void saveCurrentRouteAndAreaAsBest()
	{
		bestRoute = new ArrayList<Point>(route);
		bestArea = new Area(area);
		pointsInBestRoute = area.numberOfWorkedCells();
	}

	/**
	 * Entfernt eine Parzelle wieder von der aktuellen Route.
	 * 
	 * @param p
	 *            Die Parzelle.
	 */
	public void removeFromRouteIfNeeded(Point p)
	{
		route.remove(p);
	}

	/**
	 * Gibt den Namen der konkreten Strategie aus.
	 * 
	 * @return Der Name
	 */
	public abstract String getName();

	/**
	 * Gibt die erste Richtungsentscheidung der Strategie aus.
	 * 
	 * @return Die erste Richtungsentscheidung
	 */
	public abstract int getFirstDecision();

	/**
	 * Gibt die n�chste Richtungsentscheidung aus, abh�ngig von der letzten und
	 * dem aktuellen Schritt.
	 * 
	 * @param lastDecision
	 *            Die letzte Richtungsentscheidung
	 * @param step
	 *            Der aktuelle Schritt
	 * @return Die n�chste Richtungsentscheidung
	 */
	public abstract int getNextDecision(int lastDecision, int step);

	/**
	 * Gibt den Wert der Richtungsentscheidung aus.
	 * 
	 * @param decision
	 *            Die Richtungsentscheidung
	 * @return Einen der Werte: '^', '&lt;', 'v', '&gt;' um "nach oben",
	 *         "nach rechts", "nach unten", "nach links" zu symbolisieren.
	 * @throws IllegalArgumentException
	 *             Falls diese Richtungsentscheidung nicht existiert.
	 */
	public abstract char getValueForDecision(int decision);
}
