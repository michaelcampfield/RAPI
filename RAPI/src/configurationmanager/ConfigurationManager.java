package configurationmanager;

import java.io.File;
import java.util.ArrayList;

import propertyparser.PropertyParser;
import propertyparser.Property;
import rapiinterface.rapl.fileio.XMLToDomParser;

public class ConfigurationManager {

	XMLToDomParser xmlParser;
	PropertyParser propertyParser;
	public ArrayList<Property> properties; //This public modifier is temporary, only for testing purposes.
	boolean fileReset;
	
	public ConfigurationManager(String configFile){
		//System.out.println("Configuration Manager called.");
		xmlParser = new XMLToDomParser();
		propertyParser = new PropertyParser(xmlParser);
		properties = propertyParser.parseConfigFile(configFile);
		createProperties();
	}
	
	private boolean createProperties(){//Might change return type to void.
		Property tempProp;
		boolean propsSucceeded = true;
		for(int i = 0; i < properties.size(); i++){
			tempProp = properties.get(i);
			
			if(propsSucceeded){
				//System.out.println(tempProp.getType());
				switch(tempProp.getType()){
					/*case "BUSLOGIC":
						propsSucceeded = setPointOfContact(tempProp.getAttributeFromHashMap("POCCLASS"));
						break;*/
					case "FILES":
						propsSucceeded = setReset(tempProp);
						break;
					case "directory":
						propsSucceeded = createDirectory(tempProp);
						break;
					case "file":
						propsSucceeded = createFile(tempProp);
						break;
					default:
						break;
				}
			}
		}
		return true;
	}
	
	/*public String getPointOfContact(){
		return POCClass;
	}
	
	private boolean setPointOfContact(String pointOfContact){
		//System.out.println("Point of contact is being set: " + pointOfContact);
		POCClass = pointOfContact;
		return true;
	}*/
	
	private boolean setReset(Property reset){
		switch(reset.getAttributeFromHashMap("reset")){
		case "true":
			fileReset = true;
			return true;
		case "false":
			fileReset = false;
			return true;
		default:
			return false;
		}
	}
	
	private boolean createFile(Property file){
		/*System.out.println("Create new file: " + file.getAttributeFromHashMap("location") +
												 file.getAttributeFromHashMap("fileName") + 
												 "." +
												 file.getAttributeFromHashMap("type"));
		*/
		/*
		try {
			File createdFile = new File(file.getAttributeFromHashMap(file.getAttributeFromHashMap("location") +
					 file.getAttributeFromHashMap("fileName") + 
					 "." +
					 file.getAttributeFromHashMap("type")));
			createdFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String fileType = file.getAttributeFromHashMap("type");
		
		switch(fileType){
		case "txt":
			/*rapiinterface.rapl.fileio.TextFileIO.createTextFile(file.getAttributeFromHashMap("location") +
											 file.getAttributeFromHashMap("fileName") +
											 "." +
											 file.getAttributeFromHashMap("type"));
											 */
											//This block of code is currently commented out to stabilize the code for publication.
			break;
		default:
		break;
		}
		return true;
	}
	
	private boolean createDirectory(Property directory){
		File newDir = new File(directory.getAttributeFromHashMap("location") + 
				 directory.getAttributeFromHashMap("name"));
		
		newDir.mkdir();
		
		return true;
	}
	
	/*private void delete(File temp) throws IOException{
		temp.delete();
	}*/
	
	public ArrayList<Property> getProperties(){//This is currently being used for testing if properties are correctly being parsed. They are.
		return properties;
	}
}