package view;

import java.util.List;
import model.*;

public abstract class OutputWriter
{
	public abstract void write(Area startArea, List<Strategy> strategies);
	
	/**
	 * H�ngt einen String an.
	 * @param s Der String
	 */
	public abstract void append(String s);
}
