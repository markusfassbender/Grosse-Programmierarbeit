package model;

import java.util.*;

/**
 * Stellt eine zweidimensionale Fläche, die versiegelt werden kann, dar.
 * 
 * @author Markus Faßbender
 */
public class Area
{
	/**
	 * Das zu versiegelnde Feld.
	 */
	private char[][] area;
	/**
	 * Die Parzelle auf welcher der Roboter gestartet wird.
	 */
	private Point startPoint;

	/**
	 * Konstruktor.
	 * 
	 * @param dimensions
	 *            Die Abmessungen der Fläche
	 * @param startPoint
	 *            Die Startparzelle
	 * 
	 * @throws IllegalArgumentException
	 *             Falls keine zwei Dimensionen für die Fläche angegeben wurden.
	 */
	public Area(int[] dimensions, Point startPoint)
	{
		if (dimensions.length < 2)
		{
			throw new IllegalArgumentException(
					"Es müssen zwei Dimensionen angegeben werden!");
		}

		this.area = new char[dimensions[0]][dimensions[1]];

		for (int row = 0; row < area.length; ++row)
		{ // iterate rows
			for (int column = 0; column < area[0].length; ++column)
			{ // iterate
				// columns
				this.area[row][column] = ' ';
			}
		}

		this.startPoint = startPoint;
	}

	/**
	 * Copy-Konstruktor.
	 * 
	 * @param area
	 *            Die andere Fläche
	 */
	public Area(Area area)
	{
		this.area = new char[area.area.length][area.area[0].length];

		for (int row = 0; row < this.area.length; ++row)
		{ // iterate rows
			for (int column = 0; column < this.area[0].length; ++column)
			{ // iterate
				// columns
				this.area[row][column] = area.area[row][column];
			}
		}

		this.startPoint = new Point(area.startPoint.x, area.startPoint.y);
	}

	/**
	 * Prüft ob die Parzelle in der Fläche existiert.
	 * 
	 * @param p
	 *            Die Parzelle
	 * @return true falls diese existiert.
	 */
	public boolean existsCell(Point p)
	{
		return (p.x >= 0 && p.x < area.length)
				&& (p.y >= 0 && p.y < area[0].length);
	}

	/**
	 * Prüft ob eine Parzelle unversiegelt ist.
	 * 
	 * @param p
	 *            Die Parzelle
	 * @return true falls unversiegelt
	 */
	public boolean isCellEmpty(Point p)
	{
		return area[p.x][p.y] == ' ';
	}

	/**
	 * Prüft ob bereits ein Endpunkt für den Roboter gefunden wurde.
	 * 
	 * @return true wenn bereits ein Endpunkt existiert
	 */
	public boolean containsEndPoint()
	{
		for (int row = 0; row < this.area.length; ++row)
		{ // iterate rows
			for (int column = 0; column < this.area[0].length; ++column)
			{ // iterate
				// columns
				if (this.area[row][column] == 'Z')
				{
					return true;
				}

			}
		}

		return false;
	}

	/**
	 * Gibt den Wert einer Parzelle.
	 * 
	 * @param p
	 *            Die Parzelle
	 * @return Der eingetragene Wert
	 */
	public char getCell(Point p)
	{
		return area[p.x][p.y];
	}

	/**
	 * Setzt einen Wert einer Parzelle.
	 * 
	 * @param p
	 *            Die Parzelle
	 * @param value
	 *            Der Wert
	 */
	public void setCell(Point p, char value)
	{
		area[p.x][p.y] = value;
	}

	/**
	 * Setzt eine Parzelle auf den Zustand 'unversiegelt'.
	 * 
	 * @param p
	 *            Die Parzelle
	 */
	public void clearCell(Point p)
	{
		setCell(p, ' ');
	}

	/**
	 * Gibt die Startparzelle des Roboters auf der Fläche.
	 * 
	 * @return Die Startparzelle
	 */
	public Point getStartPoint()
	{
		return startPoint;
	}

	/**
	 * Gibt die Anzahl aller Parzellen der Fläche.
	 * 
	 * @return Die Anzahl aller Parzellen der Fläche
	 */
	public int numberOfCells()
	{
		if (area.length > 0)
		{
			return area.length * area[0].length;
		} else
		{
			return 0;
		}
	}

	/**
	 * Gibt die Anzahl der Parzellen, die einen der mitgegebenen Werte haben.
	 * 
	 * @param possibleValues
	 *            Die möglichen Werte
	 * @return Die Anzahl
	 */
	private int numberOfCellsWithValues(char[] possibleValues)
	{
		int counter = 0;

		for (char[] row : area)
		{ // iterate rows
			for (char cellValue : row)
			{ // iterate area cells
				for (char possibleValue : possibleValues)
				{ // iterate possible
					// values
					if (cellValue == possibleValue)
					{
						++counter;
						break;
					}
				}
			}
		}

		return counter;
	}

	/**
	 * Gibt die Anzahl der versiegelten Parzellen.
	 * 
	 * @return Die Anzahl der versiegelten Parzellen
	 */
	public int numberOfWorkedCells()
	{
		char[] values =
		{ '^', '<', 'v', '>' };
		return numberOfCellsWithValues(values);
	}

	/**
	 * Gibt die Anzahl der unversiegelten Parzellen (aber ohne Hindernisse).
	 * 
	 * @return Die Anzahl der unversiegelten Parzellen
	 */
	public int numberOfNonWorkedCells()
	{
		return numberOfCellsWithValues(new char[]
		{ ' ' });
	}

	/**
	 * Gibt die Anzahl der Parzellen, die durch ein Hindernis blockiert werden.
	 * 
	 * @return Die Anzahl der blockierten Parzellen
	 */
	public int numberOfBlockedCells()
	{
		return numberOfCellsWithValues(new char[]
		{ 'H' });
	}

	/**
	 * Setzt Hindernisse auf die Fläche.
	 * 
	 * @param blocks
	 *            Eine Liste von Hindernissen
	 * 
	 * @throws IllegalArgumentException
	 *             Falls einer der Blocks eine ungültig Position hat.
	 */
	public void setBlocks(List<Block> blocks)
	{
		for (Block block : blocks)
		{
			// prüfe auf gültige lage
			if (!existsCell(block.getStartPoint())
					|| !existsCell(block.getEndPoint()))
			{
				throw new IllegalArgumentException(
						"Der Block muss innerhalb der Abmessungen der Flaeche liegen!");
			}

			// fuege geblockte parzellen hinzu
			for (Point p : block.getPointsBlocked())
			{
				if (startPoint.x == p.x && startPoint.y == p.y)
				{
					throw new IllegalArgumentException(
							"Der Block darf den Startpunkt nicht ueberschneiden!");
				} else
				{
					setCell(p, 'H');
				}
			}
		}
	}

	@Override
	public String toString()
	{
		boolean printStart = !containsEndPoint();
		StringBuilder sb = new StringBuilder(" ");

		for (int i = 1; i <= area[0].length; ++i)
		{
			sb.append(" " + i);
		}
		sb.append("\n");

		for (int row = 0; row < area.length; ++row)
		{ // iterate rows
			sb.append("" + (row + 1) + " ");

			for (int column = 0; column < area[0].length; ++column)
			{ // iterate area cells
				if (printStart && row == startPoint.x && column == startPoint.y)
				{
					sb.append("S ");
				} else
				{
					sb.append(area[row][column] + " ");
				}
			}

			sb.append("\n");
		}

		return sb.toString();
	}
}
