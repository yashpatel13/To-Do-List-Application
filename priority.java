package todo;

public enum priority {
	Low, Moderate, High;
	
	private priority(){}
	
	public String value(){
		return name();
	}
	
	public static priority fromvalue(String v){
		return valueOf(v);
	}
}
