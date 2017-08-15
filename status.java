package todo;

public enum status {
	Complete, Current, Incomplete;
	
	private status(){}
	
	public String value(){
		return name();
	}
	
	public static status fromvalue(String v){
		return valueOf(v);
	}
}
