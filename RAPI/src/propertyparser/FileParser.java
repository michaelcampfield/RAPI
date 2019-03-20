package propertyparser;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class FileParser {

	public static ArrayList<Property> parseFileConfigs(Document doc, ArrayList<Property> properties){
		//System.out.println("Current String: " + doc.getElementsByTagName("files").item(0).getChildNodes().item(1).getChildNodes().item(1).getTextContent());
		//System.out.println(doc.getElementsByTagName("files").item(0).getAttributes().getNamedItem("reset").getNodeValue());

		//NodeList fileConfig = doc.getElementsByTagName("files").item(0).getChildNodes();
		//System.out.println(fileConfig.item(0).getTextContent().codePointAt(0));
		
		//for(int i = 0; i < fileConfig.getLength(); i++){
		//	String currentNode = fileConfig.item(i).getNodeName();
			//System.out.println(fileConfig.item(i).getNodeName());
			/*if(fileConfig.item(i).getNodeName().equals("#text")){
				System.out.println("Tab present.");
				continue;
			}
			
			if(fileConfig.item(i))
			
			switch(currentNode){
			case "#text": continue;
			case "appdirectory": parseDirectory(properties);//Deal with directory
			     break;
			case "appfile": parseFile(properties);//Deal with file.
				 break;
		    default: System.out.println("Incorrect Tag used in \"files\" configurations. Application will fail.");
		         //Throw Exception here, halt execution.
		         break;
			}*/
		//}
		
		return properties;
	}
	
	static Property parseDirectory(Node currentNode){
		String locationKey = "location";
		String idKey = "id";
		String directoryKey = "name";
		
		Property tempProperty = new Property("directory");
		
		tempProperty.addAttributeToHashMap(locationKey, currentNode.getAttributes().getNamedItem(locationKey).getNodeValue());
		tempProperty.addAttributeToHashMap(idKey, currentNode.getAttributes().getNamedItem(idKey).getNodeValue());
		tempProperty.addAttributeToHashMap(directoryKey, currentNode.getAttributes().getNamedItem(directoryKey).getNodeValue());
		
		return tempProperty;
	}
	
	static Property parseFile(Node currentNode) {
		//System.out.println("Dealing with app file.");
		
		String locationKey = "location";
		String locationValue;
		String idKey = "id";
		String idValue;
		String typeKey = "type";
		String typeValue;
		String fileKey = "fileName";
		String fileValue;
		
		if(currentNode.getParentNode().getNodeName().equalsIgnoreCase("files")){
			locationValue = currentNode.getAttributes().getNamedItem(locationKey).getNodeValue();
			idValue = currentNode.getAttributes().getNamedItem(idKey).getNodeValue();
			typeValue = currentNode.getAttributes().getNamedItem(typeKey).getNodeValue();
			fileValue = currentNode.getTextContent();
		}
		else{
			locationValue = currentNode.getParentNode().getAttributes().getNamedItem("location").getNodeValue() +
							currentNode.getParentNode().getAttributes().getNamedItem("name").getNodeValue() + "\\";
			idValue = currentNode.getAttributes().getNamedItem(idKey).getNodeValue();
			typeValue = currentNode.getAttributes().getNamedItem("type").getNodeValue();
			fileValue = currentNode.getTextContent();
		}
		
		Property tempProperty = new Property("file");
		tempProperty.addAttributeToHashMap(locationKey, locationValue);
		tempProperty.addAttributeToHashMap(idKey, idValue);
		tempProperty.addAttributeToHashMap(typeKey, typeValue);
		tempProperty.addAttributeToHashMap(fileKey, fileValue);
		return tempProperty;
	}
	
	public static String getReset(Document doc){
		return doc.getElementsByTagName("files").item(0).getAttributes().getNamedItem("reset").getNodeValue();
	}
}
