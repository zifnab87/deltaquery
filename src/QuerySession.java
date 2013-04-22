import java.util.Vector;


public class QuerySession {
	private int id;
	private Vector<Query> queries = new Vector<Query>();
	
	public void addQuery(Query query){
		queries.add(query);
	}
	
	public void removeQuery(Query query){
		queries.remove(query);
	}
	
}
