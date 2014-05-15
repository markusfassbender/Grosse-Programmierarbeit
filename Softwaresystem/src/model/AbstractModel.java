package model;

import java.util.List;

import controller.Strategy;

/**
 * Die Schnittstelle eines Models.
 * 
 * @author Markus Faßbender
 */
public abstract class AbstractModel
{
	/**
	 * Gibt die Startfläche zurück.
	 * 
	 * @return Die Startfläche
	 */
	public abstract Area getStartArea();

	/**
	 * Setzt die Startfläche.
	 * 
	 * @param area
	 *            Die Startfläche
	 */
	public abstract void setStartArea(Area area);

	/**
	 * Fügt eine Strategie hinzu.
	 * 
	 * @param strategy
	 *            Die neue Strategie
	 */
	public abstract void addStrategy(Strategy strategy);

	/**
	 * Gibt alle Strategien.
	 * 
	 * @return Alle Strategien
	 */
	public abstract List<Strategy> getStrategies();
}
