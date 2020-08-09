package main.resources;
//this is the input for the lines_of_code.java
		
		
//above lines are not null -> line.length()!=0

//Have enough comments and blank lines to test the code

public class Input {
	
	static String name = "arun";
	static String lastName = "varma";
	//
	
	private static void printName(String s1,String s2) {
		
		System.out.println("Name is : "+ s1 +" "+s2);
	}
	
	public static void returnName(String a[]) {
		printName(name,lastName );
	}
}
