package model;

import java.util.List;

import controller.Strategy;

/**
 * Die Schnittstelle eines Models.
 * 
 * @author Markus Fa�bender
 */
public abstract class AbstractModel
{
	/**
	 * Gibt die Startfl�che zur�ck.
	 * 
	 * @return Die Startfl�che
	 */
	public abstract Area getStartArea();

	/**
	 * Setzt die Startfl�che.
	 * 
	 * @param area
	 *            Die Startfl�che
	 */
	public abstract void setStartArea(Area area);

	/**
	 * F�gt eine Strategie hinzu.
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
