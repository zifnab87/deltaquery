import Zql.ParseException;

public class Application {
	
	public static void main(String args[]) throws ParseException {
		Query q1 = new Query("SELECT id FROM table1 WHERE id = 3;");
		Query q2 = new Query("SELECT id, height, weight FROM table1 WHERE id = 3;");
		System.out.println(Delta.extractDeltas(q1, q2));
		Query q3 = new Query("SELECT id, weight FROM table1 WHERE id = 3;");
		System.out.println(Delta.extractDeltas(q2, q3));
		
		Query q4 = new Query("SELECT balance FROM bank;");
		Query q5 = new Query("SELECT balance FROM bank WHERE date1 >= 2010 AND date1 <= 2011;");
		System.out.println(Delta.extractDeltas(q4, q5));
		Query q6 = new Query("SELECT balance FROM bank WHERE date1 >= 2010 AND date1 <= 2012;");
		System.out.println(Delta.extractDeltas(q5, q6));
		Query q7 = new Query("SELECT balance FROM bank WHERE date1 >= 2011 AND date1 <= 2013;");
		System.out.println(Delta.extractDeltas(q6, q7));
	}
}



