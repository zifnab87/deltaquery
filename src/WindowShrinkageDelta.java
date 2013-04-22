
public class WindowShrinkageDelta extends Delta {
private Constraint constraint;
	
	public WindowShrinkageDelta(Query source, Query destination, Dimension dimension, String dmin, String dmax) {
		super(source, destination);
		this.constraint = new Constraint(dimension, dmin, dmax);
	}
	
	public Dimension getDimension() {
		return constraint.getDimension();
	}
	
	public String getChangeInMin() {
		return constraint.getMin();
	}
	
	public String getChangeInMax() {
		return constraint.getMax();
	}
	
	public Query apply(Query source) {
		return null;
	}
}
