package loadbalancer.driver;

import java.util.ArrayList;

import loadbalancer.util.FileProcessor;
public class Driver {
	public static void main(String[] args) {
		if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 2 argumnets.");
			System.exit(0);
		}
		
		System.out.println("Your Output is generated in Output.txt file");
		String args1 = args[0];
		String args2 = args[1];
		FileProcessor file  = new FileProcessor(args2);
		file.readFile(args1);
	}
}
