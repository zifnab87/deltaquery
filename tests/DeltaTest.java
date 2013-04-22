import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;


public class DeltaTest {

	/**
	 * ProjectionAdditionDelta and ProjectionRemovalDelta
	 */
	@Test
	public void testExtractProjectionDeltas() {
		Query src = new Query("select a, b, e, f from tab;");
		Query dest = new Query("select b, c, d, e from tab;");
		Collection<Delta> deltas = Delta.extractProjectionDeltas(src, dest);
		System.out.println(deltas);
		assertEquals(4, deltas.size());
		assertTrue(deltas.contains(new ProjectionRemovalDelta(src, dest, new Dimension("a"))));
		assertTrue(deltas.contains(new ProjectionAdditionDelta(src, dest, new Dimension("c"))));
		assertTrue(deltas.contains(new ProjectionAdditionDelta(src, dest, new Dimension("d"))));
		assertTrue(deltas.contains(new ProjectionRemovalDelta(src, dest, new Dimension("f"))));
	}

	/**
	 * ConstraintAdditionDelta, ConstraintRemovalDelta, WindowExpansionDelta, WindowShrinkageDelta, PanDelta
	 */
	@Test
	public void testExtractConstraintDeltas() {
		Query src = new Query("select * from tab where a <= 1 and b >= 2 and e <= 3 and f <= 4 and g >= 11 and g <= 12;");
		Query dest = new Query("select * from tab where b >= 5 and c <= 6 and d <= 7 and e <= 8 and g >= 13 and g <= 14;");
		Collection<Delta> deltas = Delta.extractConstraintDeltas(src, dest);
		System.out.println(deltas);
		assertEquals(7, deltas.size());
		assertTrue(deltas.contains(new ConstraintRemovalDelta(src, dest, new Constraint(new Dimension("a"), Constraint.INF, "1"))));
		assertTrue(deltas.contains(new WindowShrinkageDelta(src, dest, new Dimension("b"), "3.0", "0.0")));
		assertTrue(deltas.contains(new ConstraintAdditionDelta(src, dest, new Constraint(new Dimension("c"), Constraint.INF, "6"))));
		assertTrue(deltas.contains(new ConstraintAdditionDelta(src, dest, new Constraint(new Dimension("d"), Constraint.INF, "7"))));
		assertTrue(deltas.contains(new WindowExpansionDelta(src, dest, new Dimension("e"), "0.0", "5.0")));
		assertTrue(deltas.contains(new ConstraintRemovalDelta(src, dest, new Constraint(new Dimension("f"), Constraint.INF, "4"))));
		assertTrue(deltas.contains(new PanDelta(src, dest, new Dimension("g"), "2.0")));
	}
}
