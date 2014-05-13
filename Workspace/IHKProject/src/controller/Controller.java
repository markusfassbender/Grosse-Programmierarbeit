package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;

import view.*;
import model.*;

public class Controller
{
	private AbstractModel model;
	private InputReader input;
	private OutputWriter output;

	public Controller(File inFile, File outFile) throws FileNotFoundException
	{
		input = new InputFileReader(inFile);
		output = new OutputFileWriter(outFile);
		Area startArea = null;

		// try parsing from file
		try
		{
			int[] dimensions = input.readAreaDimensions();
			Point startPoint = input.readStartPoint();
			List<Block> blocks = input.readBlocks();

			// validiere daten, hier fliegt sonst eine exception
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
		model.setStartArea(startArea);
		model.addStrategy(new ClockwiseStrategy(startArea));
		model.addStrategy(new MyStrategy(startArea));
	}

	public void run()
	{
		List<Strategy> strategies = model.getStrategies();

		for (Strategy strategy : strategies)
		{
			startBacktracking(strategy);
		}

		output.write(model.getStartArea(), strategies);

		String warning = input.getWarning();
		if (warning != null)
		{
			output.append("\n\n" + warning);
		}
	}

	private void startBacktracking(Strategy strategy)
	{
		Area startArea = model.getStartArea();
		Point startPoint = startArea.getStartPoint();

		backtrack(strategy, startPoint, strategy.getFirstDecision());
	}

	private boolean backtrack(Strategy strategy, Point point, int lastDecision)
	{
		Area area = strategy.getArea();

		if (area.isPointValid(point) == false)
		{
			return false;
		}

		if (area.getCell(point) == 'H')
		{
			return false;
		}

		if (area.isCellEmpty(point) == false)
		{
			return false;
		}

		boolean success = false;

		for (int step = 0; step <= 3; ++step)
		{
			int decision = strategy.getNextDecision(lastDecision, step);
			char value = strategy.getValueForDecision(decision);
			area.setCell(point, value); // setze wert in area ein
			strategy.addToRouteIfNeeded(point);

			Point nextPoint = strategy
					.getNextPointWithDecision(point, decision);

			success = backtrack(strategy, nextPoint, decision);

			if (success)
			{
				break;
			}
		}

		// sind alle felder gefüllt?

		if (success == false)
		{
			if (area.numberOfWorkedCells() == (area.numberOfCells() - area
					.numberOfBlockedCells()))
			{
				area.setCell(point, 'Z');
				success = true;
			} else
			{
				area.clearCell(point);
				strategy.removeFromRouteIfNeeded(point);
			}
		}

		return success;
	}
}
