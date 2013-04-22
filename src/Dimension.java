
public class Dimension {
	private String field;
	
	public Dimension(String field) {
		this.field = field;
	}
	
	public String getField() {
		return field;
	}
	

	public String toString(){
		return this.field;
		
	}
	public boolean equals(Object other) {
		return other.getClass().equals(getClass()) && ((Dimension) other).getField().equals(field);
	}
}
