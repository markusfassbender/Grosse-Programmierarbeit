package model;

/**
 * Konkrete implementierung meiner Strategie.
 * 
 * @author Markus Faﬂbender
 */
public class MyStrategy extends Strategy
{
	/**
	 * Konstruktor.
	 * 
	 * @param area
	 *            Die Fl‰che auf der operiert werden kann
	 */
	public MyStrategy(Area area)
	{
		super(area);
	}

	@Override
	public String getName()
	{
		return "Meine Strategie";
	}

	@Override
	public int getNextDecision(int lastDecision, int step)
	{
		return (lastDecision + step) % 4;
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
			value = '<';
			break;

		case 2:
			value = 'v';
			break;

		case 3:
			value = '>';
			break;

		default:
			throw new IllegalArgumentException(
					"Step muss zwischen 0 und 3 (beides inklusiv) sein.");
		}

		return value;
	}

	@Override
	public int getFirstDecision()
	{
		return 0;
	}

}
