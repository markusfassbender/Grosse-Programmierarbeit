package view;

import java.util.List;
import model.*;

/**
 * Schreibt Ausgabewerte.
 * 
 * @author Markus Fa�bender
 * 
 */
public abstract class OutputWriter
{
	/**
	 * Schreibt Ausgabewerte nach dem Format der Beispiele in der
	 * Aufgabenstellung.
	 * 
	 * @param startArea
	 *            Die eingelesene, leere Fl�che am Anfang
	 * @param strategies
	 *            Die Strategien mit ihren Fl�chen usw.
	 */
	public abstract void write(Area startArea, List<Strategy> strategies);

	/**
	 * H�ngt einen String an.
	 * 
	 * @param s
	 *            Der String
	 */
	public abstract void append(String s);
}
