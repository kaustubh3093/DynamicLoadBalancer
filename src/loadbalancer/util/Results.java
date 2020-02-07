package loadbalancer.util;

import java.io.PrintWriter;
import java.util.ArrayList;

import loadbalancer.subject.Cluster;

public class Results implements FileDisplayInterface , StdoutDisplayInterface{
	
	//Methods to print the results
	@Override
	public void consoleWrite(String str) {
		System.out.println(str);
	}
	@Override
	 public void putOutput(PrintWriter printWriter ,String output){
		printWriter.println(output);
	 }
}
