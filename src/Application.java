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
		Query query = new Query("select a,b,c FROM sada WHERE a>= 3 AND a<= 5 AND a>=2 AND b>=4;");
		System.out.println(query.constraints);
		
	}
	//single step delta
	public static Delta calculateSingleStepDelta(Query q1,Query q2){
		return null;
		
	}
	
	
	
}



