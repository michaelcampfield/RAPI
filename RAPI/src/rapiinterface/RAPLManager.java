package rapiinterface;

import configurationmanager.ConfigurationManager;
import rapiinterface.RAPIObject;
import rapiinterface.rapl.fileio.*;

class RAPLManager {
	
	private ConfigurationManager configManager;
	private TextFileIO txtFileIO;
	
	public RAPLManager(String configFile){
		//System.out.println("RAPL constructor called.");
		configManager = new ConfigurationManager(configFile);
		txtFileIO = new TextFileIO();
	}
	
	protected RAPIObject generalInterfaceReceiver(String[] commandParams){
		String command = commandParams[0];
		switch(command){
			case "addToTxtFile":
				return addToTxtFile(commandParams[1], commandParams[2]);
			default:
				return new RAPIObject("null");
		}
	}
	
	private RAPIObject addToTxtFile(String filePath, String text){
		txtFileIO.updateTextFile(filePath, text);
		return new RAPIObject("null");
	}
}
