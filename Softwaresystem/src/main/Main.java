package main;

import java.io.File;
import java.util.InputMismatchException;

import controller.Controller;

public class Main
{
	public static void main(String[] args)
	{

		if (args.length >= 2)
		{
			String in = args[0];
			String out = args[1];

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
