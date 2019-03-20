package testmain;

import rapiinterface.RAPInterface;
import rapiinterface.rapl.fileio.*;

public class Main {

	static RAPInterface rapi;
	static TextFileIO textIO;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		rapi = setRAPI();
		writeToTextFile("C:\\testoutput\\testfile.txt", "Test words.");
		
	}
	
	private static RAPInterface setRAPI(){
		try {
			return new RAPInterface("C:\\TestXMLFiles\\config.xml");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private static void createNewTextFile(String filePath){
		//rapi.writeToTextFile();
	}
	
	private static void writeToTextFile(String filePath, String text){
		rapi.writeToTextFile(filePath, text);
	}
}
