
public class ProjectionAdditionDelta extends Delta {
	private Dimension dimension;
	
	public ProjectionAdditionDelta(Query source, Query destination, Dimension dimension) {
		super(source, destination);
		this.dimension = dimension;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
	public boolean equals(Object other) {
		return other.getClass().equals(getClass()) && ((ProjectionAdditionDelta) other).getDimension().equals(dimension);
	}
	
	public Query apply(Query source) {
		return null;
	}
}
