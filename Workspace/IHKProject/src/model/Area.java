package model;

import java.util.*;

public class Area {
	private char[][] area;
	private Point startPoint;
	
	public Area(int[] dimensions, Point startPoint)
	{
		if(dimensions.length < 2)
		{
			throw new IllegalArgumentException("Es m�ssen zwei Dimensionen angegeben werden!");
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
		for(Block block : blocks)
		{
			// handle block
			for(int row = block.getStartPoint().x; row <= block.getEndPoint().x; ++row) {
				for(int column = block.getStartPoint().y; column <= block.getEndPoint().y; ++column) {
					area[row][column] = 'H';
				}
			}
		}
	}
	
	public String toString()
	{
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
				sb.append(area[row][column] + ",");
			}
			
			sb.append("\n");
		}

		return sb.toString();
	}
}
