
public abstract class Delta {
	protected Query source;
	protected Query destination;
	
	public Delta(Query source, Query destination) {
		this.source = source;
		this.destination = destination;
	}
	
	public Query getSource() {
		return source;
	}

	public Query getDestination() {
		return destination;
	}

	public abstract Query apply(Query source);
}
