
public class ConstraintAdditionDelta extends Delta {
	private Constraint constraint;
	
	public ConstraintAdditionDelta(Query source, Query destination, Constraint constraint) {
		super(source, destination);
		this.constraint = constraint;
	}
	
	public Constraint getConstraint() {
		return constraint;
	}
	
	public Query apply(Query source) {
		return null;
	}
}
