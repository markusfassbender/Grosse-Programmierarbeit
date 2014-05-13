package model;

import java.util.*;

public class Area {
	private char[][] area;
	private Point startPoint;
	
	public Area(int[] dimensions, Point startPoint)
	{
		if(dimensions.length < 2)
		{
			throw new IllegalArgumentException("Es müssen zwei Dimensionen angegeben werden!");
		}
		
		this.area = new char[dimensions[0]][dimensions[1]];
		
		for(int row = 0; row < area.length; ++row)
		{ // iterate rows
			for(int column = 0; column < area[0].length; ++column)
			{ // iterate columns
				this.area[row][column] = ' ';
			}
		}
		
		this.startPoint = startPoint;
	}
	
	public Area(Area area) {
		this.area = new char[area.area.length][area.area[0].length]; 
		
		for(int row = 0; row < this.area.length; ++row)
		{ // iterate rows
			for(int column = 0; column < this.area[0].length; ++column)
			{ // iterate columns
				this.area[row][column] = area.area[row][column];
			}
		}
		
		this.startPoint = new Point(area.startPoint.x, area.startPoint.y);
	}
	
	public boolean isPointValid(Point p) {
		return (p.x >= 0 && p.x < area.length)
				&& (p.y >= 0 && p.y < area[0].length);
	}
	
	public boolean isCellEmpty(Point p) {
		return area[p.x][p.y] == ' ';
	}
	
	
	public boolean containsEndPoint() {
		for(int row = 0; row < this.area.length; ++row)
		{ // iterate rows
			for(int column = 0; column < this.area[0].length; ++column)
			{ // iterate columns
				if(this.area[row][column] == 'Z') {
					return true;
				}
				
			}
		}
		
		return false;
	}
	
	public char getCell(Point p) {
		return area[p.x][p.y];
	}
	
	public void setCell(Point p, char value) {
		area[p.x][p.y] = value;
	}
	
	public void clearCell(Point p) {
		setCell(p, ' ');
	}
	
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public int numberOfCells() {
		if(area.length > 0) {
			return area.length * area[0].length;
		} else {
			return 0;
		}
	}
	
	private int numberOfCellsWithValues(char[] possibleValues) {
		int counter = 0;
		
		for(char[] row : area)
		{ // iterate rows
			for(char cellValue : row)
			{ // iterate area cells
				for(char possibleValue : possibleValues)
				{ // iterate possible values
					if(cellValue == possibleValue)
					{
						++counter;
						break;
					}
				}
			}
		}
		
		return counter;
	}
	
	
	public int numberOfWorkedCells() {
		char[] values = {'^', '<', 'v', '>'};
		return numberOfCellsWithValues(values);
	}
	
	public int numberOfNonWorkedCells() {
		return numberOfCellsWithValues(new char[]{' '});
	}
	
	public int numberOfBlockedCells() {
		return numberOfCellsWithValues(new char[]{'H'});
	}
	
	public void setBlocks(List<Block> blocks)
	{
		Point blockStart, blockEnd;
		
		for(Block block : blocks)
		{
			blockStart = block.getStartPoint();
			blockEnd = block.getEndPoint();
			
			if(!isPointValid(blockStart) || !isPointValid(blockEnd)) {
				throw new IllegalArgumentException("Der Block muss innerhalb der Abmessungen der Flaeche liegen!");
			}
			
			// fuege block ein
			for(int row = blockStart.x; row <= blockEnd.x; ++row)
			{
				for(int column = blockStart.y; column <= blockEnd.y; ++column)
				{
					if(startPoint.x == row && startPoint.y == column)
					{
						throw new IllegalArgumentException("Der Block darf den Startpunkt nicht ueberschneiden!");
					} else {
						area[row][column] = 'H';
					}
					
				}
			}
		}
	}
	
	public String toString()
	{
		boolean printStart = !containsEndPoint();
		StringBuilder sb = new StringBuilder(" ");
		
		for(int i = 1; i <= area[0].length; ++i) {
			sb.append(" " + i);
		}
		sb.append("\n");

		for(int row = 0; row < area.length; ++row)
		{ // iterate rows
			sb.append("" + (row+1) + " ");
			
			for(int column = 0; column < area[0].length; ++column)
			{ // iterate area cells
				if(printStart && row == startPoint.x && column == startPoint.y) {
					sb.append("S ");					
				} else {
					sb.append(area[row][column] + " ");
				}
			}
			
			sb.append("\n");
		}

		return sb.toString();
	}
}
