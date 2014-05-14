package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;

import view.*;
import model.*;

/**
 * Enthält den Hauptteil der Logik und kommuniziert sowohl mit dem Model als
 * auch mit der View.
 * 
 * @author Markus Faßbender
 */
public class Controller
{
	/**
	 * Das Model.
	 */
	private AbstractModel model;

	/**
	 * Liest die Eingabe.
	 */
	private InputReader input;

	/**
	 * Schreibt in die Ausgabe.
	 */
	private OutputWriter output;

	/**
	 * Konstruktor
	 * 
	 * @param inFile
	 *            Die Eingabedatei
	 * @param outFile
	 *            Die Ausgabedatei
	 * @throws FileNotFoundException
	 *             Falls eine Datei nicht erstellt oder nicht geöffnet werden
	 *             kann.
	 */
	public Controller(File inFile, File outFile) throws FileNotFoundException
	{
		input = new InputFileReader(inFile);
		output = new OutputFileWriter(outFile);
		Area startArea = null;

		// parse from file
		try
		{
			int[] dimensions = input.readAreaDimensions();
			Point startPoint = input.readStartPoint();
			List<Block> blocks = input.readBlocks();

			startArea = new Area(dimensions, startPoint);
			startArea.setBlocks(blocks);
		} catch (InputMismatchException e)
		{
			output.append("Fehler: " + e.getMessage());
			throw e;
		} catch (IllegalArgumentException e)
		{
			output.append("Fehler: " + e.getMessage());
			throw e;
		}

		model = new Model();
		model.addStrategy(new ClockwiseStrategy(startArea));
		model.addStrategy(new MyStrategy(startArea));
		model.setStartArea(startArea);
	}

	/**
	 * Führt das Programm vollständig aus und schreibt das Ergebnis in die
	 * Augabe.
	 */
	public void run()
	{
		List<Strategy> strategies = model.getStrategies();

		for (Strategy strategy : strategies)
		{
			startBacktracking(strategy);
		}

		Area startArea = model.getStartArea();
		output.write(startArea, strategies);

		String warning = input.getWarning();
		if (warning != null)
		{
			output.append("\n\n" + warning);
		}
	}

	/**
	 * Startet das Backtracking für eine Strategy.
	 * 
	 * @param strategy
	 *            Die Strategy
	 */
	private void startBacktracking(Strategy strategy)
	{
		Area startArea = model.getStartArea();
		Point startPoint = startArea.getStartPoint();

		backtrack(strategy, startPoint, strategy.getFirstDecision());
	}

	/**
	 * Führt das Backtracking rekursiv aus.
	 * 
	 * @param strategy
	 *            Die auszuführende Strategie
	 * @param point
	 *            Die aktuelle Parzelle
	 * @param lastDecision
	 *            Die letzte getroffene Richtungsentscheidung
	 * @return true falls eine vollständige Lösung gefunden wurde
	 */
	private boolean backtrack(Strategy strategy, Point point, int lastDecision)
	{
		Area area = strategy.getArea();

		boolean success = false;

		if (isValidPoint(point, area))
		{
			for (int step = 0; step <= 3; ++step)
			{
				int decision = strategy.getNextDecision(lastDecision, step);
				char value = strategy.getValueForDecision(decision);
				area.setCell(point, value); // setze wert in area ein
				strategy.addToRouteIfNeeded(point);

				Point nextPoint = strategy.getNextPointWithDecision(point,
						decision);

				success = backtrack(strategy, nextPoint, decision);

				if (success)
				{
					break;
				}
			}

			// wurde bisher keine vollstaendige loesung gefunden?
			if (success == false)
			{
				if (area.numberOfWorkedCells() == (area.numberOfCells() - area
						.numberOfBlockedCells()))
				{
					// wenn alle zellen bis auf die blockierten abgearbeitet
					// wurden
					// markiere diese lösung als vollstaendig
					area.setCell(point, 'Z');
					success = true;
				} else
				{
					// sonst mache einen schritt zurück
					area.clearCell(point);
					strategy.removeFromRouteIfNeeded(point);
				}
			}
		}

		return success;
	}

	private boolean isValidPoint(Point point, Area area)
	{
		if (area.existsCell(point) == false)
		{
			// die parzelle existiert nicht
			return false;
		}

		if (area.getCell(point) == 'H')
		{
			// die parzelle ist ein hindernis
			return false;
		}

		if (area.isCellEmpty(point) == false)
		{
			// die parzelle ist nicht mehr unversiegelt
			return false;
		}

		return true;
	}
}
