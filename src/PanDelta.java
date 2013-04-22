
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
	
	public Query apply(Query source) {
		return null;
	}
}
