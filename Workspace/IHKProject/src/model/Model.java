package model;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel
{
	private Area area;
	private List<Strategy> strategies;
	
	public Model() {
		strategies = new ArrayList<>();
	}
	
	@Override
	public Area getStartArea() {
		return area;
	}

	@Override
	public void setStartArea(Area area) {
		this.area = area;
	}

	@Override
	public void addStrategy(Strategy strategy) {
		strategies.add(strategy);
	}

	@Override
	public List<Strategy> getStrategies() {
		return strategies;
	}

}
