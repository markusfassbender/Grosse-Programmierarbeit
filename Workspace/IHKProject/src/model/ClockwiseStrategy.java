package model;

/**
 * Konkrete Implementierung der Uhrzeiger-Strategie.
 * 
 * @author markus.fassbender
 * 
 */
public class ClockwiseStrategy extends Strategy
{
	/**
	 * Konstruktor.
	 * 
	 * @param area
	 *            Die Fläche auf der operiert werden kann
	 */
	public ClockwiseStrategy(Area area)
	{
		super(area);
	}

	@Override
	public String getName()
	{
		return "Uhrzeiger-Strategy";
	}

	@Override
	public int getFirstDecision()
	{
		return 0;
	}

	@Override
	public int getNextDecision(int lastDecision, int step)
	{
		return step;
	}

	@Override
	public char getValueForDecision(int decision)
	{
		char value;

		switch (decision)
		{
		case 0:
			value = '^';
			break;

		case 1:
			value = '>';
			break;

		case 2:
			value = 'v';
			break;

		case 3:
			value = '<';
			break;

		default:
			throw new IllegalArgumentException(
					"Step muss zwischen 0 und 3 (beides inklusiv) sein.");
		}

		return value;
	}

}
