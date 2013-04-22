import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Set;

public class Query {
	private String query;
	private QuerySession session;
	private static int idcounter=0;
	private static int id;
	public Set<Dimension> projections = new HashSet<Dimension>();
	public Set<Constraint> constraints = new HashSet<Constraint>();
	public Query(String query) {
		super();
		this.query = query;
		this.id = idcounter++;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public QuerySession getSession() {
		return session;
	}
	public void setSession(QuerySession session) {
		this.session = session;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void addProjection(Dimension d){
		this.projections.add(d);
	}
	
	public void addConstraints(Constraint c){
		this.constraints.add(c);
	}
	
}
