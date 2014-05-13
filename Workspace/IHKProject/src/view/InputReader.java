package view;

import java.io.FileNotFoundException;
import java.util.*;

import model.*;

public abstract class InputReader {
	/**
	 * Die minimale Dimension.
	 */
	protected final int DIMENSION_MINIMUM;
	
	/**
	 * Die maximale Dimension.
	 */
	protected final int DIMENSION_MAXIMUM;
	
	
	/**
	 * Eine Liste von Warnungen.
	 */
	protected List<String> warnings;
	
	/**
	 * Konstruktor.
	 */
	public InputReader() {
		DIMENSION_MINIMUM = 1;
		DIMENSION_MAXIMUM = 10;
		
		warnings =  new LinkedList<String>();
	}
	
	/**
	 * Gibt aufgetretene Warnungen.
	 * @return Aufgetretene Warnungen oder null, wenn es keine gab
	 */
	public String getWarning() {
		if(warnings.size() > 0) {
			StringBuilder sb = new StringBuilder("Warnungen:");
			
			for(String w : warnings) {
				sb.append("\n" + w);
			}
			
			return sb.toString();			
		} else {
			return null;
		}
	}
	
	
	public abstract int[] readAreaDimensions() throws FileNotFoundException;
	public abstract Point readStartPoint() throws FileNotFoundException;
	public abstract List<Block> readBlocks() throws FileNotFoundException;
}
