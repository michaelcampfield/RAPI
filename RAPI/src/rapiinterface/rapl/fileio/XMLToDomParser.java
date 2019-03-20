package rapiinterface.rapl.fileio;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class XMLToDomParser {
	
	File xmlfile;
	DocumentBuilderFactory docFactory;
	DocumentBuilder dBuilder;
	Document document = null;
	
	public XMLToDomParser(){//This needs to be updated to be usable to any xml file, not just configuration files.
		try{
			docFactory = DocumentBuilderFactory.newInstance();
			dBuilder = docFactory.newDocumentBuilder();
		}
		catch(Exception e){
			System.out.println("Failure to initialize XMLToDomParser:");
			e.printStackTrace();
		}
	}
	
	public Document startXMLParser(String fileLocation){
		if(isValidConfig(fileLocation))
			return parseXML(fileLocation);
		else
			return null;
	}
	
	private boolean isValidConfig(String fileLocation){//Checking if xml file is correctly formatting to RAPI format specifications. Temporarily setting to true without checking.
		return true;
	}
	
	private Document parseXML(String fileLocation){
		try {
			xmlfile = new File(fileLocation);
			document = dBuilder.parse(xmlfile);
			document.getDocumentElement().normalize();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return document;
	}
}
