package model;

import java.util.*;

/**
 * Stellt ein Hindernis für den Roboter dar.
 * 
 * @author Markus Faßbender
 */
public class Block
{
	/**
	 * Die Anfangsecke des Hindernisses.
	 */
	private Point startPoint;
	/**
	 * Die Endecke des Hindernisses.
	 */
	private Point endPoint;

	/**
	 * Konstruktor.
	 * 
	 * @param startPoint
	 *            Die Anfangsecke
	 * @param endPoint
	 *            Die Endecke
	 */
	public Block(Point startPoint, Point endPoint)
	{
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	/**
	 * Gibt die Anfangsecke.
	 * 
	 * @return Die Anfangsecke
	 */
	public Point getStartPoint()
	{
		return startPoint;
	}

	/**
	 * Gibt die Endecke.
	 * 
	 * @return Die Endecke
	 */
	public Point getEndPoint()
	{
		return endPoint;
	}

	/**
	 * Gibt alle Parzellen, die das Hinternis blockiert.
	 * 
	 * @return Die blockierten Parzellen
	 */
	public List<Point> getPointsBlocked()
	{
		// TODO implement
		return new ArrayList<Point>();
	}
}
