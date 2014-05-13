package model;

/**
 * Stellt eine Parzelle auf der Fl�che dar.
 * 
 * @author Markus Fa�bender
 */
public class Point
{
	/**
	 * Wert f�r x.
	 */
	public int x;

	/**
	 * Wert f�r y.
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
	 * Pr�ft auf Gleicheit eines anderen Punktes.
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
