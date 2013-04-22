
public class ConstraintAdditionDelta extends Delta {
	private Constraint constraint;
	
	public ConstraintAdditionDelta(Query source, Query destination, Constraint constraint) {
		super(source, destination);
		this.constraint = constraint;
	}
	
	public Constraint getConstraint() {
		return constraint;
	}
	
	public boolean equals(Object other) {
		return other.getClass().equals(getClass()) && ((ConstraintAdditionDelta) other).getConstraint().equals(constraint);
	}
	
	public Query apply(Query source) {
		return null;
	}
}
