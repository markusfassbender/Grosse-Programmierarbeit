package view;

import java.io.*;
import java.util.*;

import model.*;

public class InputFileReader extends InputReader
{
	private File file;
	
	public InputFileReader(String path) {
		super();
		this.file = new File(path);
	}
	
	public InputFileReader(File file) {
		super();
		this.file = file;
	}

	@Override
	public int[] readAreaDimensions() throws FileNotFoundException
	{
		Scanner sc = new Scanner(file);
		String line;
		int[] dimensions = new int[2];
		
		while(sc.hasNextLine())
		{
			line = sc.nextLine();
			if(line.startsWith("; ") == false)
			{
				String[] values = line.split("\\s");
				
				dimensions[0] = Integer.parseInt(values[0]);
				dimensions[1] = Integer.parseInt(values[1]);
				break;
			}
		}
		
		sc.close();
		
		return dimensions;
	}

	@Override
	public Point readStartPoint() throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		String line;
		Point point = null;
		int counter = 0;
		
		while(sc.hasNextLine())
		{
			line = sc.nextLine();
			if(line.startsWith("; ") == false)
			{
				if(counter == 1) {
					String[] values = line.split("\\s");
					
					point = pointFromStringValues(values[0], values[1]);
					break;
				} else {
					++counter;
				}
				
			}
		}
		
		sc.close();
		
		return point;
	}

	@Override
	public List<Block> readBlocks() throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		String line;
		List<Block> blocks = new LinkedList<Block>();
		int counter = 0;
		
		while(sc.hasNextLine())
		{
			line = sc.nextLine();
			if(line.startsWith("; ") == false)
			{
				if(counter == 2) {
					String[] values = line.split("\\s");
					
					Point start = pointFromStringValues(values[0], values[1]);
					Point end = pointFromStringValues(values[2], values[3]);
					
					blocks.add(new Block(start, end));
				} else {
					++counter;
				}
				
			}
		}
		
		sc.close();
		
		return blocks;
	}
	
	private Point pointFromStringValues(String first, String second) {
		int x = Integer.parseInt(first) - 1;
		int y = Integer.parseInt(second) - 1;
		
		return new Point(x,y);
	}
}
