
public class ProjectionRemovalDelta extends Delta {
	private Dimension dimension;
	
	public ProjectionRemovalDelta(Query source, Query destination, Dimension dimension) {
		super(source, destination);
		this.dimension = dimension;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
	public boolean equals(Object other) {
		return other.getClass().equals(getClass()) && ((ProjectionRemovalDelta) other).getDimension().equals(dimension);
	}
	
	public Query apply(Query source) {
		return null;
	}
}
