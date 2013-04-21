
public abstract class Delta {
	protected Query source;
	protected Query destination;
	protected Constraint window;
	
	public Delta(Query source, Query destination, Constraint constraint) {
		this.source = source;
		this.destination = destination;
		this.window = window;
	}
	
	public Delta(Constraint constraint) {
		this(null, null, constraint);
	}
	
	public Query getSource() {
		return source;
	}

	public Query getDestination() {
		return destination;
	}

	public Constraint getConstraint() {
		return constraint;
	}

	public abstract Query apply(Query source);
}
