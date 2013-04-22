
public class WindowShrinkageDelta extends Delta {
	private Constraint constraint;
	
	public WindowShrinkageDelta(Query source, Query destination, Constraint constraint) {
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
