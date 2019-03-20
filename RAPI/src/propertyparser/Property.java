package propertyparser;

import java.util.HashMap;

public class Property {
	
	String type;
	HashMap<String, String> attributes;
	
	public Property(String type){
		this.type = type;
		attributes = new HashMap<String, String>();
	}
	
	public Property(String type, String field1, String field2){
		this.type = type;
		attributes = new HashMap<String, String>();
		attributes.put(field1, field2);
	}
	
	public void addAttributeToHashMap(String key, String value){
		addAttribute(key, value);
	}
	
	private void addAttribute(String key, String value){
		attributes.put(key, value);
	}
	
	public String getType(){
		return getTypeString();
	}
	
	private String getTypeString(){
		return type;
	}
	
	public String getAttributeFromHashMap(String key){
		return getAttribute(key);
	}
	
	private String getAttribute(String key){
		return attributes.get(key);
	}
}
