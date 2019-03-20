package rapiinterface;

public class RAPIObject {
	
	public String returnType;
	
	public RAPIObject(String returnType){
		this.returnType = returnType;
	}
	
	private void setReturnType(String returnType){
		this.returnType = returnType;
	}
}
