
public class PanDelta extends Delta {
	private Dimension dimension;
	private String change;
	
	public PanDelta(Query source, Query destination, Dimension dimension, String change) {
		super(source, destination);
		this.dimension = dimension;
		this.change = change;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
	public String getChange() {
		return change;
	}
	
	public boolean equals(Object other) {
		if (other.getClass().equals(getClass())) {
			PanDelta opd = (PanDelta)other;
			return opd.getDimension().equals(dimension) && opd.getChange().equals(change);
		}
		return  false;
	}
	
	public String toString() {
		return String.format("Pan window of %s by %s", getDimension(), getChange());
	}
	
	public Query apply(Query source) {
		return null;
	}
}
