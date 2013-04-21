
public class ConstraintRemovalDelta extends Delta {
	private Constraint constraint;
	
	public ConstraintRemovalDelta(Query source, Query destination, Constraint constraint) {
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
