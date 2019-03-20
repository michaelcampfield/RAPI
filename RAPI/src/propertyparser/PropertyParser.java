package propertyparser;

import java.util.ArrayList;
import java.util.HashMap;

import propertyparser.Property;
import rapiinterface.rapl.fileio.XMLToDomParser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PropertyParser {
	
	XMLToDomParser xmlParser;
	private final String BUSINESSLOGIC = "BUSLOGIC";
	private final String POCCLASS = "POCCLASS";
	private final String FILENAME = "FILE";
	private final String FILES = "files";
	private final String RESET = "reset";
	
	public PropertyParser(XMLToDomParser parser){
		xmlParser = parser;
	}
	
	public ArrayList<Property> parseConfigFile(String configFile){
		Document dom = xmlParser.startXMLParser(configFile);
		ArrayList<Property> properties = new ArrayList<Property>();
		//System.out.println(dom.getElementsByTagName("dbconfig").item(0).getChildNodes().item(1).getChildNodes().item(1).getChildNodes().item(0).getNodeValue());
		//properties.add(parsePOC(dom));
		//ArrayList<Property> fileProperties = parseFiles(dom);//This will be updated to add all of the properties in the near future in an ArrayList.

		properties.addAll(parseFiles(dom));
		return properties;
	}
	
	/*private Property parsePOC(Document doc){
		String pocClass = doc.getElementsByTagName("businesslogic").item(0).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
		Property prop = new Property(BUSINESSLOGIC);
		prop.addAttributeToHashMap(POCCLASS, pocClass);
		return prop;
	}*/
	
	private ArrayList<Property> parseFiles(Document doc){
		ArrayList<Property> properties = new ArrayList<Property>();
		properties.add(new Property("FILES", RESET, FileParser.getReset(doc)));
		NodeList fileConfig = doc.getElementsByTagName(FILES).item(0).getChildNodes();
		properties.addAll(traverseNodeList(0, fileConfig));
		return properties;
	}
	
	private static ArrayList<Property> traverseNodeList(int levelCounter, NodeList fileConfig){//Parses all configurations within the current directory.
		ArrayList<Property> properties = new ArrayList<Property>();
		
		Node currentNode;
		for(int i = 0; i < fileConfig.getLength(); i++){
			currentNode = fileConfig.item(i);

			if(!currentNode.getNodeName().equalsIgnoreCase("#text")){
				properties.add(parseTag(currentNode, levelCounter));
			}
			if(currentNode.getNodeName().equalsIgnoreCase("appdirectory")){
				levelCounter++;
				properties.addAll(traverseNodeList(levelCounter, goLevelDeeper(levelCounter, currentNode)));
			}
		}
		
		return properties;
	}
	
	private static NodeList goLevelDeeper(int levelCounter, Node currentLevel){//Return the Nodelist of childern to parse nested directory.
		if(levelCounter < 10)
			return currentLevel.getChildNodes();
		else{
			System.out.println("Warning: tags are deeper than 10 levels.");
			return null;
		}
	}
	
	private static Property parseTag(Node currentNode, int levelCounter){
		String currentNodeName = currentNode.getNodeName();
		switch(currentNodeName){
		case "appdirectory": 
			 return FileParser.parseDirectory(currentNode);//Call go deeper in the tag level, parse the tag.
		case "appfile": return FileParser.parseFile(currentNode);//Deal with file.
	    default: System.out.println("Incorrect Tag used in \"files\" configurations. Application will fail.");
	         //Throw Exception here, halt execution.
	         break;
		}
		
		return null;
	}
}