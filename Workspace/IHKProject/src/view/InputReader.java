package view;

import java.io.FileNotFoundException;
import java.util.*;

import model.*;

public abstract class InputReader {
	/**
	 * Eine Liste von Warnungen.
	 */
	protected List<String> warnings;
	
	/**
	 * Konstruktor.
	 */
	public InputReader() {
		warnings =  new LinkedList<String>();
	}
	
	public List<String> getWarnings() {
		return warnings;
		
		/*
		if(warnings.isEmpty()) {
			return null;
		} else {
			StringBuilder sb = new StringBuilder("Warnungen:");
			
			for(String w : warnings) {
				sb.append("\n" + w);
			}
			
			return sb.toString();
		}*/
	}
	
	
	public abstract int[] readAreaDimensions() throws FileNotFoundException;
	public abstract Point readStartPoint() throws FileNotFoundException;
	public abstract List<Block> readBlocks() throws FileNotFoundException;
	
}
