import java.io.ByteArrayInputStream;
import java.util.Vector;

import Zql.ParseException;
import Zql.ZExp;
import Zql.ZExpression;
import Zql.ZQuery;
import Zql.ZStatement;
import Zql.ZqlParser;

public class Application {
	
	public static void main(String args[]) throws ParseException{
		Query query = new Query("select * FROM sada;");
		System.out.println(query.constraints);
		System.out.println(query.projections);
	}
	//single step delta
	public static Delta calculateSingleStepDelta(Query q1,Query q2){
		return null;
		
	}
	
	
	
}



