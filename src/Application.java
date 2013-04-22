import Zql.ParseException;

public class Application {
	
	public static void main(String args[]) throws ParseException {
		Query q1 = new Query("SELECT id FROM table1 WHERE id >= 3;");
		Query q2 = new Query("SELECT id, height, weight FROM table1 WHERE id >= 3;");
		System.out.println(Delta.extractDeltas(q1, q2));
		Query q3 = new Query("SELECT * FROM table1 WHERE ;");
		System.out.println(Delta.extractDeltas(q2, q3));
	}
	
	//single step delta
	public static Delta calculateSingleStepDelta(Query q1,Query q2){
		return null;
	}
}



