package view;

import java.io.*;
import java.util.*;

import model.*;

/**
 * Implementiert das Einlesen über eine Datei.
 * 
 * @author Markus Faßbender
 */
public class InputFileReader extends InputReader
{
	/**
	 * Die Eingabedatei.
	 */
	private File file;

	/**
	 * Konstruktor.
	 * 
	 * @param path
	 *            Pfad zu einer Eingabedatei
	 */
	public InputFileReader(String path)
	{
		super();
		this.file = new File(path);
	}

	/**
	 * Konstruktor.
	 * 
	 * @param file
	 *            Die Eingabedatei
	 */
	public InputFileReader(File file)
	{
		super();
		this.file = file;
	}

	@Override
	public int[] readAreaDimensions() throws FileNotFoundException
	{
		Scanner sc = new Scanner(file);
		String line;
		int[] dimensions = new int[2];

		while (sc.hasNextLine())
		{
			line = sc.nextLine();
			if (line.length() > 0 && line.startsWith("; ") == false)
			{
				String[] values = line.trim().split("\\s+");

				if (values.length == 2)
				{
					try
					{
						dimensions[0] = Integer.parseInt(values[0]);
						dimensions[1] = Integer.parseInt(values[1]);
					} catch (NumberFormatException e)
					{
						sc.close();
						throw new InputMismatchException(
								"Die Abmessung der Flaeche muss ganzzahlig sein!");
					}

					if (!isValueValid(dimensions[0])
							|| !isValueValid(dimensions[1]))
					{
						sc.close();
						throw new InputMismatchException(
								"Die Abmessung der Flaeche muss zwischen "
										+ DIMENSION_MINIMUM + " und "
										+ DIMENSION_MAXIMUM + " liegen!");
					}
				} else
				{
					sc.close();
					throw new InputMismatchException(
							"Es muessen genau zwei Werte als Abmessungen der Flaeche gegeben sein!");
				}

				break;
			}
		}

		sc.close();

		if (dimensions[0] == 0 && dimensions[1] == 0)
		{
			throw new InputMismatchException(
					"Es muessen die Abmessungen der Flaeche definiert sein");
		} else
		{
			return dimensions;
		}

	}

	@Override
	public Point readStartPoint() throws FileNotFoundException
	{
		Scanner sc = new Scanner(file);
		String line;
		Point point = null;
		int counter = 0;

		while (sc.hasNextLine())
		{
			line = sc.nextLine();
			if (line.length() > 0 && line.startsWith("; ") == false)
			{
				if (counter == 1)
				{
					String[] values = line.trim().split("\\s+");

					if (values.length == 2)
					{
						try
						{
							point = pointFromStringValues(values[0], values[1]);
						} catch (Exception e)
						{
							sc.close();
							throw e;
						}
					} else
					{
						sc.close();
						throw new InputMismatchException(
								"Es muessen genau zwei Werte zur Definition eines Startpunkts gegeben sein!");
					}

					break;
				} else
				{
					++counter;
				}

			}
		}

		sc.close();

		if (point != null)
		{
			return point;
		} else
		{
			throw new InputMismatchException(
					"Es muss ein Startpunkt definiert sein!");
		}

	}

	@Override
	public List<Block> readBlocks() throws FileNotFoundException
	{
		Scanner sc = new Scanner(file);
		String line;
		List<Block> blocks = new LinkedList<Block>();
		int counter = 0;

		while (sc.hasNextLine())
		{
			line = sc.nextLine();
			if (line.length() > 0 && line.startsWith("; ") == false)
			{
				if (counter == 2)
				{
					String[] values = line.trim().split("\\s+");

					if (values.length >= 4)
					{
						try
						{
							Point start = pointFromStringValues(values[0],
									values[1]);
							Point end = pointFromStringValues(values[2],
									values[3]);

							blocks.add(new Block(start, end));
						} catch (InputMismatchException e)
						{
							sc.close();
							throw e;
						}
					} else
					{
						warnings.add("Das Hindernis '"
								+ line
								+ "' wurde ignoriert, da es unzureichend definiert ist.");
					}
				} else
				{
					++counter;
				}

			}
		}

		sc.close();

		return blocks;
	}

	/**
	 * Erstellt eine Parzelle aus zwei Stringwerten.
	 * 
	 * @param first
	 *            Der erste Wert als String
	 * @param second
	 *            Der zweite Wert als String
	 * @return Die neue Parzelle
	 * 
	 * @throws InputMismatchException
	 *             Falls die Werte ungültig sind.
	 */
	private Point pointFromStringValues(String first, String second)
	{
		int x, y;

		try
		{
			x = Integer.parseInt(first);
			y = Integer.parseInt(second);
		} catch (NumberFormatException e)
		{
			throw new InputMismatchException(
					"Die Werte eines Punktes muessen ganzzahlig sein!");
		}

		if (isValueValid(x) && isValueValid(y))
		{
			return new Point(x - 1, y - 1);
		} else
		{
			throw new InputMismatchException(
					"Die Werte eines Punktes muessen zwischen "
							+ DIMENSION_MINIMUM + " und " + DIMENSION_MAXIMUM
							+ " liegen!");
		}
	}

	/**
	 * Prüft ob ein Wert im gültigen Wertebereich liegt.
	 * 
	 * @param value
	 *            Der zu prüfende Wert
	 * @return true wenn er im gültigen Wertebereich liegt
	 */
	private boolean isValueValid(int value)
	{
		return value >= DIMENSION_MINIMUM && value <= DIMENSION_MAXIMUM;
	}
}
