package model;

import java.util.List;

public abstract class AbstractModel
{
	public abstract Area getStartArea();

	public abstract void setStartArea(Area area);

	public abstract void addStrategy(Strategy strategy);

	public abstract List<Strategy> getStrategies();
}
