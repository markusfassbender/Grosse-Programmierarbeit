package main;

import java.io.File;
import java.util.InputMismatchException;

import controller.Controller;

/**
 * Wrapper um das Programm auszuführen.
 * 
 * @author Markus Faßbender
 */
public class Main
{
	/**
	 * Führt das Programm mit Parametern aus.
	 * 
	 * @param args
	 *            Die Übergabeparameter
	 */
	public static void main(String[] args)
	{
		// TODO: args statt values verwenden
		String[] values =
		{ "resources/test.in", "resources/test.out" };

		if (values.length >= 2)
		{
			String in = values[0];
			String out = values[1];

			try
			{
				Controller c = new Controller(new File(in), new File(out));
				c.run();
			} catch (InputMismatchException ime)
			{
				// ignore, because the error has already been written to the
				// view
			} catch (IllegalArgumentException iae)
			{
				// ignore, because the error has already been written to the
				// view
			} catch (Exception e)
			{
				System.err.println("Exception: " + e.getMessage() + "\n");
			}
		} else
		{
			// brich ab, da keine parameter übergeben wurden
			System.err
					.println("Das Programm benötigt mindestens zwei Übergabeparameter:\n"
							+ "1.) Der Name der Eingabedatei\n"
							+ "2.) Der Name der Ausgabedatei");
		}
	}
}
