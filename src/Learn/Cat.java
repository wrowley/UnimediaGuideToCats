package Learn;

import java.io.File;

import prog.TextFileReader;

public class Cat {
	
	public String name;
	public String asciiPicture;
	public String information;
	
	
	public Cat(){

	}

	public void init() {
		name = new String();
		asciiPicture = new String();
		information = new String();
	}
	
	public void initialiseCat(File catDir) {
		
		
		File nameFile = new File(catDir,"name");
		String name = TextFileReader.getContents(nameFile);
		File infoFile = new File(catDir,"info");
		String info = TextFileReader.getContents(infoFile);
		File asciiFile = new File(catDir,"ascii");
		String ascii = TextFileReader.getContents(asciiFile);

		
		this.name = name;
		this.information = info;
		this.asciiPicture = ascii;
		
//		System.out.println(catDir);
//   	System.out.println(nameFile);
//		System.out.println(infoFile);
//		System.out.println(asciiFile);
//		System.out.println(this.name);

	}

	public void display() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Name:");
		System.out.println(name);
		System.out.println("Information:");
		System.out.println(information);
		System.out.println("Picture:");
		System.out.println(asciiPicture);
		System.out.println();
	}
	
	public String getString() {
		
       String retString = "Name: \n"+name+"\nInformation: \n"+information+"\nPicture: \n"+asciiPicture+"\n";
		
	   return retString;	
	
	}
	
}
