package view;

import java.util.List;

import model.*;

/**
 * Schnittelle zur Ausgabe von Ergebnissen.
 * 
 * @author Markus Faﬂbender
 * 
 */
public abstract class OutputWriter
{
	/**
	 * Formatiert eine Route in einen lesbaren String.
	 * 
	 * @param route
	 *            Die Route
	 * @return Die formatierte Route oder einen leeren String, wenn die Route
	 *         leer ist.
	 */
	protected String formatRoute(List<Point> route)
	{
		if (route != null && route.size() > 0)
		{
			StringBuilder sb = new StringBuilder(route.get(0).toString());

			for (int pointIndex = 1; pointIndex < route.size(); ++pointIndex)
			{
				sb.append(" / " + route.get(pointIndex).toString());
			}

			return sb.toString();
		} else
		{
			return "";
		}
	}

	/**
	 * Schreibt Ausgabewerte nach dem Format der Beispiele in der
	 * Aufgabenstellung.
	 * 
	 * @param startArea
	 *            Die eingelesene, leere Fl‰che am Anfang
	 * @param strategies
	 *            Die Strategien mit ihren Fl‰chen usw.
	 */
	public abstract void write(Area startArea, List<Strategy> strategies);

	/**
	 * H‰ngt einen String an.
	 * 
	 * @param s
	 *            Der String
	 */
	public abstract void append(String s);
}
