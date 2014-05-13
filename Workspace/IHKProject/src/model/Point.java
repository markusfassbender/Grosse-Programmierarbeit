package model;

/**
 * Stellt eine Parzelle auf der Fläche dar.
 * 
 * @author Markus Faßbender
 */
public class Point
{
	/**
	 * Wert für x.
	 */
	public int x;

	/**
	 * Wert für y.
	 */
	public int y;

	/**
	 * Konstruktor.
	 */
	public Point()
	{
		x = y = 0;
	}

	/**
	 * Konstruktor
	 * 
	 * @param x
	 *            Der x-Wert
	 * @param y
	 *            Der y-Wert
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Prüft auf Gleicheit eines anderen Punktes.
	 * 
	 * @param p
	 *            Der andere Punkt
	 * @return true falls die Punkte gleich sind
	 */
	public boolean isEqual(Point p)
	{
		return x == p.x && y == p.y;
	}

	@Override
	public String toString()
	{
		return "" + (x + 1) + "," + (y + 1);
	}
}
