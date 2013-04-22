import java.util.ArrayList;
import java.util.Collection;


public abstract class Delta {
	public static final double EPSILON = Math.exp(-10);
	
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
	
	public static Collection<Delta> extractDeltas(Query source, Query destination) {
		Collection<Delta> deltas = new ArrayList<Delta>();
		deltas.addAll(extractProjectionDeltas(source, destination));
		deltas.addAll(extractConstraintDeltas(source, destination));
		return deltas;
	}
	
	/**
	 * Finds all instances of ProjectionAdditionDelta and ProjectionRemovalDelta.
	 * @param source
	 * @param destination
	 * @return
	 */
	public static Collection<Delta> extractProjectionDeltas(Query source, Query destination) {
		Collection<Delta> deltas = new ArrayList<Delta>();
		
		// ProjectionRemovalDelta
		for (Dimension src_dim : source.projections) {
			boolean found = false;
			for (Dimension dest_dim : destination.projections) {
				if (src_dim.getField().equals(dest_dim.getField())) {
					found = true;
					break;
				}
			}
			if (!found) {
				deltas.add(new ProjectionRemovalDelta(source, destination, src_dim));
			}
		}
		
		// ProjectionAdditionDelta
		for (Dimension dest_dim : destination.projections) {
			boolean found = false;
			for (Dimension src_dim : source.projections) {
				if (dest_dim.getField().equals(src_dim.getField())) {
					found = true;
					break;
				}
			}
			if (!found) {
				deltas.add(new ProjectionAdditionDelta(source, destination, dest_dim));
			}
		}
		
		return deltas;
	}
	
	/**
	 * Finds all instances of ConstraintAdditionDelta, ConstraintRemovalDelta, WindowExpansionDelta, WindowShrinkageDelta, and PanDelta.
	 * @param source
	 * @param destination
	 * @return
	 */
	public static Collection<Delta> extractConstraintDeltas(Query source, Query destination) {
		Collection<Delta> deltas = new ArrayList<Delta>();
		
		for (Constraint src_con : source.constraints) {
			boolean found = false;
			for (Constraint dest_con : destination.constraints) {
				if (src_con.getDimension().equals(dest_con.getDimension())) {
					found = true;
					
					double src_range;
					double dest_range;
					double dmin;
					double dmax;
					try {
						double src_min = Double.parseDouble(src_con.getMin());
						double src_max = Double.parseDouble(src_con.getMax());
						double dest_min = Double.parseDouble(dest_con.getMin());
						double dest_max = Double.parseDouble(dest_con.getMax());
						src_range = src_max - src_min;
						dest_range = dest_max - dest_min;
						dmin = dest_min - src_min;
						dmax = dest_max - src_max;
					}
					catch (NumberFormatException nfe) {
						src_range = src_con.getMax().compareTo(src_con.getMin());
						dest_range = dest_con.getMax().compareTo(dest_con.getMin());
						dmin = dest_con.getMin().compareTo(src_con.getMin());
						dmax = dest_con.getMax().compareTo(src_con.getMax());
					}
					
					// PanDelta
					if (Math.abs(src_range - dest_range) < EPSILON && dmin > EPSILON) {
						deltas.add(new PanDelta(source, destination, dest_con.getDimension(), String.valueOf(dmin)));
					}
					
					// WindowExpansionDelta
					else if (src_range < dest_range) {
						deltas.add(new WindowExpansionDelta(source, destination, dest_con.getDimension(), String.valueOf(dmin), String.valueOf(dmax)));
					}
					
					// WindowShrinkageDelta
					else if (src_range > dest_range){
						deltas.add(new WindowShrinkageDelta(source, destination, dest_con.getDimension(), String.valueOf(dmin), String.valueOf(dmax)));
					}
					
					break;
				}
			}
			// ConstraintRemovalDelta
			if (!found) {
				deltas.add(new ConstraintRemovalDelta(source, destination, src_con));
			}
		}
		
		// ConstraintAdditionDelta
		for (Constraint dest_con : destination.constraints) {
			boolean found = false;
			for (Constraint src_con : source.constraints) {
				if (dest_con.getDimension().equals(src_con.getDimension())) {
					found = true;
					break;
				}
			}
			if (!found) {
				deltas.add(new ConstraintAdditionDelta(source, destination, dest_con));
			}
		}
		
		return deltas;
	}
}
