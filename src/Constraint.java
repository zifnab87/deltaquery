
public class Constraint {
	private Dimension dimension;
	private String min;
	private String max;
	public static String INF = "Inf";
	
	public Constraint(Dimension dimension, String min, String max) {
		this.dimension = dimension;
		this.min = min;
		this.max = max;
	}
	public Constraint(Dimension dimension) {
		this.dimension = dimension;
		this.min = INF;
		this.max = INF;
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

	public Constraint setMin(String min) {
		this.min = min;
		return this;
	}

	public Constraint setMax(String max) {
		this.max = max;
		return this;
	}
	
	
}
