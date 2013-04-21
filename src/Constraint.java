
public class Constraint {
	private Dimension dimension;
	private String min;
	private String max;
	
	public Constraint(Dimension dimension, String min, String max) {
		this.dimension = dimension;
		this.min = min;
		this.max = max;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public String getMin() {
		return min;
	}

	public String getMax() {
		return max;
	}
}
