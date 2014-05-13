package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import view.*;
import model.*;

public class Controller {
	private AbstractModel model;
	private InputReader input;
	private OutputWriter output;
	
	
	public Controller(File inFile, File outFile) throws FileNotFoundException
	{
		input = new InputFileReader(inFile);
		
		int[] dimensions = input.readAreaDimensions();
		Point startPoint = input.readStartPoint();
		List<Block> blocks = input.readBlocks();
		
		// validiere daten, hier fliegt sonst eine exception
		Area startArea = new Area(dimensions, startPoint);
		startArea.setBlocks(blocks);
		
		model = new Model();
		model.setStartArea(startArea);
		model.addStrategy(new ClockwiseStrategy(startArea));
		model.addStrategy(new MyStrategy(startArea));
		
		output = new OutputFileWriter(outFile);
	}
	
	public void run()
	{
		List<Strategy> strategies = model.getStrategies();
		
		for(Strategy strategy : strategies) {
			startBacktracking(strategy);
		}
		
		output.write(model.getStartArea(), strategies);
		
		
		List<String> warnings = input.getWarnings();
		// TODO: Append warnings
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
		
		if(area.isPointValid(point) == false) {
			return false;
		}
		
		if(area.getCell(point) == 'H') {
			return false;
		}
		
		if(area.isCellEmpty(point) == false) {
			return false;
		}
		
		strategy.addToRouteIfNeeded(lastDecision, point);
		boolean success = false;
		
		for(int step = 0; step <= 3; ++step)
		{
			int decision = strategy.getNextDecision(lastDecision, step);
			char value = strategy.getValueForDecision(decision);
			area.setCell(point, value); // setze wert in area ein
			
			Point nextPoint = strategy.getNextPointWithDecision(point, decision);
			
			success = backtrack(strategy, nextPoint, decision);
			
			if(success) {
				break;
			}
		}
		
		// sind alle felder gefüllt?
		
		if(success == false)
		{
			if(area.numberOfWorkedCells() == (area.numberOfCells() - area.numberOfBlockedCells())) {
				area.setCell(point, 'Z');
				success = true;
			} else {
				area.clearCell(point);
				strategy.removeFromRouteIfNeeded(point);
			}
		}
		
		return success;
	}
}
