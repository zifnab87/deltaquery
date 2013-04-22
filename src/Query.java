import java.io.ByteArrayInputStream;
import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Zql.ParseException;
import Zql.ZConstant;
import Zql.ZExpression;
import Zql.ZFromItem;
import Zql.ZQuery;
import Zql.ZSelectItem;
import Zql.ZStatement;
import Zql.ZqlParser;

public class Query {
	private static int idcounter = 0;
	private int id;
	private String query;
	private QuerySession session;
	public Set<Dimension> projections = new HashSet<Dimension>();
	public Set<Constraint> constraints = new HashSet<Constraint>();
	
	public Query(String query) {
		this.query = query;
		this.id = idcounter++;
		try {
			this.prepareQuery(query);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
	
	public Constraint getConstraintForDimension(Dimension din){
		
		for (Constraint constr : this.constraints){
			if (constr.getDimension().getField().equals(din.getField())){
				return constr;
			}
		}
		return null;
	}
	
	public void prepareQuery(String queryString) throws ParseException{
		ZqlParser zqlparser = new ZqlParser();
		zqlparser.initParser(new ByteArrayInputStream( queryString.getBytes() ));
		ZQuery queryz = new ZQuery();
		ZStatement st = zqlparser.readStatement();
		//we assume that we only have >= in the where and only ANDs
		//also column name comes before value
		if (st instanceof ZQuery){
			 ZQuery q = (ZQuery)st;
			 Vector<ZSelectItem> sel = q.getSelect(); // SELECT part of the query
			 for ( ZSelectItem proj : sel){
				 projections.add(new Dimension(proj.getColumn()));
			 }
			 Vector<ZFromItem> from = q.getFrom();  // FROM part of the query
			 ZExpression where = (ZExpression)q.getWhere();  // WHERE part of the query
			 if (where==null){
				 return;
			 }
			 for(int i=0; i< where.nbOperands(); i++){
				 // (a >= 3 )
				 String operator;
				 String leftOperand;
				 String rightOperand;
				 if (where.getOperand(i) instanceof ZExpression){
					 ZExpression clause = (ZExpression) where.getOperand(i);
					 operator = clause.getOperator(); // >=
					 leftOperand = ((ZConstant) clause.getOperand(0)).getValue();
					 rightOperand = ((ZConstant) clause.getOperand(1)).getValue();
				 }
				 else {
					 operator = where.getOperator();
					 leftOperand = ((ZConstant) where.getOperand(0)).getValue();
					 rightOperand = ((ZConstant) where.getOperand(1)).getValue();
				 }
				 
				 
				 // a , 3
				 
				 Constraint alreadyStoredDimension = this.getConstraintForDimension(new Dimension(leftOperand));
				 if (operator.equals(">=")){
					 if(alreadyStoredDimension == null){
						 this.constraints.add(new Constraint(new Dimension(leftOperand)).setMin(rightOperand));
					 }
					 else {
						 alreadyStoredDimension.setMin(rightOperand);					 
					 }
				 }
				 else if (operator.equals("<=")){
					 if(alreadyStoredDimension == null){
						 this.constraints.add(new Constraint(new Dimension(leftOperand)).setMax(rightOperand));
					 }
					 else {
						 alreadyStoredDimension.setMax(rightOperand);					 
					 }
				 }
				 else if (operator.equals("=")){
					 if(alreadyStoredDimension == null){
						 this.constraints.add(new Constraint(new Dimension(leftOperand)).setMin(rightOperand).setMax(rightOperand));
				 
					 }
			 
				 }
			 }
			 
			
		}
		
	}
	
	/*public static HashSet<Dimension> extractProjections(Query q){
		String queryString = q.getQuery();
		Pattern pattern = Pattern.compile("SELECT (.*) FROM (.*)");
		Matcher m = pattern.matcher(queryString);
		m.find();
		String projectionsString = m.group(1);
		StringTokenizer strtok = new StringTokenizer(projectionsString,",");
		HashSet<Dimension> projectionsSet = new HashSet<Dimension>();
		while (strtok.hasMoreTokens()){
			String projection = strtok.nextToken();
			projectionsSet.add(new Dimension(projection));
		}
		return projectionsSet;
	}
	
	public static HashSet<Constraint> extractConstraints(Query q){
		String queryString = q.getQuery();
		Pattern pattern = Pattern.compile("(.*) FROM (.*) WHERE ([^(?!ORDER).]*)");
		Matcher m = pattern.matcher(queryString);
		m.find();
		String constraintsString = m.group(3);
		System.out.println(constraintsString);
		return null;
	}*/
	
}
