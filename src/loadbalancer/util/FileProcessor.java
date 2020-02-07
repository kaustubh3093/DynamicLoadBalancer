package loadbalancer.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import loadbalancer.subject.Cluster;

public class FileProcessor {
	String res;
	int flag;
	Results result;
	Cluster cluster;
	PrintWriter printWriter;
	int line;
	//Constructor to instantiate result, cluster and printWriter
	public FileProcessor(String args) {
		flag = 0;
		line = 0;
		result = new Results();
		cluster = new Cluster();
		try {
			printWriter = new PrintWriter(args);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void incompleteArgument() {
		result.putOutput(printWriter, "The given line of the file contains less number of argument");
	}
	//readFile method to read line by line from the file
	//Perform the exception and validations required in the assignment
	public void readFile(String arraypath){
	try {
		Scanner scanner = new Scanner(new File(arraypath));	
		scanner.useDelimiter("\\n");
		while(scanner.hasNext()){
			line++;
			String str = scanner.next();
			str = str.trim().replaceAll(" +", ",");
			String commandArr[]= str.split(",");
			if(commandArr.length == 0) {
				result.putOutput(printWriter, "The given line of the file is empty");
				continue;
			}
			if(commandArr[0].equals("CLUSTER_OP__SCALE_UP")) {
				if(commandArr.length <= 1) {
					incompleteArgument();
					continue;
				}
				res = cluster.CLUSTER_OP__SCALE_UP(commandArr[1]);
				result.putOutput(printWriter, res);
			}
			else if(commandArr[0].equals("SERVICE_OP__ADD_SERVICE")) {
				if(commandArr.length <= 3) {
					incompleteArgument();
					continue;
				}
				for(int j = 3; j < commandArr.length ; j++) {
				res = cluster.SERVICE_OP_ADD_SERVICE(commandArr[1], commandArr[2], commandArr[j]);
				if(res.equals("") == false) {
					result.putOutput(printWriter, res);
					flag++;
					}
				}
				if(flag != commandArr.length - 3) {
					result.putOutput(printWriter, "Service Added");
				}
				flag = 0;
			}
			else if(commandArr[0].equals("REQUEST")) {
				if(commandArr.length <= 1) {
					incompleteArgument();
					continue;
				}
				res = cluster.request(commandArr[1]);
				result.putOutput(printWriter, res);
			}
			else if(commandArr[0].equals("SERVICE_OP__ADD_INSTANCE")) {
				if(commandArr.length <= 2) {
					incompleteArgument();
					continue;
				}
				res = cluster.SERVICE_OP_ADD_INSTANCE(commandArr[1] , commandArr[2]);
				result.putOutput(printWriter, res);
			}
			else if(commandArr[0].equals("SERVICE_OP__REMOVE_SERVICE")) {
				if(commandArr.length <= 1) {
					incompleteArgument();
					continue;
				}
				res = cluster.SERVICE_OP_REMOVE_SERVICE(commandArr[1]);
				result.putOutput(printWriter, res);
			}
			else if(commandArr[0].equals("SERVICE_OP_REMOVE_INSTANCE")) {
				if(commandArr.length <= 2) {
					incompleteArgument();
					continue;
				}
				res = cluster.SERVICE_OP_REMOVE_INSTANCE(commandArr[1],commandArr[2]);
				result.putOutput(printWriter, res);
			}
			else if(commandArr[0].equals("CLUSTER_OP__SCALE_DOWN")) {
				if(commandArr.length <= 1) {
					incompleteArgument();
					continue;
				}
				res = cluster.CLUSTER_OP__SCALE_DOWN(commandArr[1]);
				result.putOutput(printWriter, res);
			}
			else {
				result.putOutput(printWriter, "The given line in the file is invalid");
			}
			}
		//To check if the file is empty
		if(line == 0) {
			result.putOutput(printWriter, "The given file is empty");
		}
		printWriter.close();
		scanner.close();
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
}
}
