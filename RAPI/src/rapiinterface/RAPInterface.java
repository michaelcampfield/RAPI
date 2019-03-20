package rapiinterface;

import rapiinterface.RAPLManager;


public class RAPInterface {
	private RAPLManager rapl;//This is temporary for testing purposes, this will not be the final code.
	//private String pointOfContact;
	
	public RAPInterface(String config) throws Exception{
		rapl = new RAPLManager(config);
	}
	
	public void writeToTextFile(String filePath, String text){
		rapl.generalInterfaceReceiver(new String[]{"addToTxtFile", filePath, text});
	}
}