package model;

import java.util.*;

/**
 * Konkrete Implementierung eines Models mit Variablen. Die Daten werden also im
 * Arbeitsspeicher gehalten.
 * 
 * @author Markus Fa�bender
 * 
 */
public class Model extends AbstractModel
{
	/**
	 * Speichert die Startfl�che.
	 */
	private Area startArea;

	/**
	 * Speichert die Strategien.
	 */
	private List<Strategy> strategies;

	/**
	 * Konstruktor.
	 */
	public Model()
	{
		strategies = new ArrayList<>();
	}

	@Override
	public Area getStartArea()
	{
		return startArea;
	}

	@Override
	public void setStartArea(Area area)
	{
		this.startArea = area;
	}

	@Override
	public void addStrategy(Strategy strategy)
	{
		strategies.add(strategy);
	}

	@Override
	public List<Strategy> getStrategies()
	{
		return strategies;
	}

}
