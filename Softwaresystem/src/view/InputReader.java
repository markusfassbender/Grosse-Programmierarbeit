package view;

import java.io.FileNotFoundException;
import java.util.*;

import model.*;

/**
 * Schnittelle zum Einlesen von Eingabewerten.
 * 
 * @author Markus Faßbender
 * 
 */
public abstract class InputReader
{
	/**
	 * Die minimale Dimension.
	 */
	protected final int DIMENSION_MINIMUM;

	/**
	 * Die maximale Dimension.
	 */
	protected final int DIMENSION_MAXIMUM;

	/**
	 * Eine Liste von Warnungen.
	 */
	protected List<String> warnings;

	/**
	 * Konstruktor.
	 */
	public InputReader()
	{
		DIMENSION_MINIMUM = 1;
		DIMENSION_MAXIMUM = 10;

		warnings = new LinkedList<String>();
	}

	/**
	 * Gibt aufgetretene Warnungen.
	 * 
	 * @return Aufgetretene Warnungen oder null, wenn es keine gab
	 */
	public String getWarning()
	{
		if (warnings.size() > 0)
		{
			StringBuilder sb = new StringBuilder("Warnungen:");

			for (String w : warnings)
			{
				sb.append("\n" + w);
			}

			return sb.toString();
		} else
		{
			return null;
		}
	}

	/**
	 * Liest die Abmessungen einer Fläche ein.
	 * 
	 * @return Die Abmessungen, wobei der Wert an nullter Stelle die Höhe und
	 *         der Wert an erster Stelle die Breite ist
	 * @throws FileNotFoundException
	 *             Falls die Eingabedatei nicht existiert.
	 * @throws InputMismatchException
	 *             Falls die Abmessungen nicht eingelesen werden können.
	 */
	public abstract int[] readAreaDimensions() throws FileNotFoundException;

	/**
	 * Liest die Startparzelle ein.
	 * 
	 * @return Die Startparzelle
	 * @throws FileNotFoundException
	 *             Falls die Eingabedatei nicht existiert.
	 * @throws InputMismatchException
	 *             Falls die Startparzelle nicht eingelesen werden kann.
	 */
	public abstract Point readStartPoint() throws FileNotFoundException;

	/**
	 * Liest die Hindernisse ein. Wenn ein Hindernis nicht ausreichend definiert
	 * ist, wird dies als Warnung abgespeichert.
	 * 
	 * @return Eine Liste von Hindernissen
	 * @throws FileNotFoundException
	 *             Falls die Eingabedatei nicht existiert.
	 * @throws InputMismatchException
	 *             Falls die Ecken eines Hindernisses existieren, aber nicht
	 *             geparsed werden können.
	 */
	public abstract List<Block> readBlocks() throws FileNotFoundException;
}
