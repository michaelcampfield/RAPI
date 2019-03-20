package rapiinterface.rapl.fileio;

import java.io.File;
import java.io.IOException;

public class TextFileIO {
	
	public TextFileIO(){

	}
	
	public static void createTextFile(String filePath){
		writeNewTextFile(filePath);
	}
	
	public static void deleteTextFile(String filePath){
		purgeTextFile(filePath);
	}
	
	public void updateTextFile(String filePath, String text){
		writeToTextFile(filePath, text);
	}
	
	public void readTextFile(String filePath){
		readFromTextFile(filePath);
	}
	
	private static void writeNewTextFile(String filePath){
		//System.out.println("Create new text file: " + filePath.replace("\\", "\\\\"));
		File newFile = new File(filePath);
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void purgeTextFile(String filePath){
		System.out.println("Delete text file at: " + filePath);
	}
	
	private void writeToTextFile(String filePath, String text){
		System.out.println("Write to file: " + filePath + " with text: " + text);
	}
	
	private void readFromTextFile(String filePath){
		System.out.println("Line from file: " + filePath + ": " + readLine());
	}
	
	private String readLine(){
		return "Read from file.";
	}
}
