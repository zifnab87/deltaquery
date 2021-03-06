
public class WindowExpansionDelta extends Delta {
	private Constraint constraint;
	
	public WindowExpansionDelta(Query source, Query destination, Dimension dimension, String dmin, String dmax) {
		super(source, destination);
		this.constraint = new Constraint(dimension, dmin, dmax);
	}
	
	public Constraint getConstraint() {
		return constraint;
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
	
	public boolean equals(Object other) {
		return other.getClass().equals(getClass()) && ((WindowExpansionDelta) other).getConstraint().equals(constraint);
	}
	
	public String toString() {
		return String.format("Expand window of %s by adding %s to min and %s to max", getDimension(), getChangeInMin(), getChangeInMax());
	}
	
	public Query apply(Query source) {
		return null;
	}
}
