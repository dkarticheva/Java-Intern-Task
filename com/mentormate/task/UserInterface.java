package com.mentormate.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class UserInterface {
	
	public static void main(String[] args) {
		String fileEmployees;
		String fileReport;
		
		System.out.println("Please enter the the path to file with the employee information");
		
		Scanner consoleReader = new Scanner(System.in);
	
		fileEmployees = consoleReader.nextLine();
		
		System.out.println("Please enter the path to file with the report information");
		fileReport = consoleReader.nextLine();
		
		consoleReader.close();
		
		Report report = null;
		List<Employee> employees = null;
		
		try {
			final GsonBuilder gsonBuilder = new GsonBuilder();
			final Gson gson = gsonBuilder.create();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReport));
			report = gson.fromJson(bufferedReader, Report.class);
			
		} catch (IOException e) {
			System.out.println("Issue while operating with file " + fileReport);
			e.printStackTrace();
			System.exit(1);
		}
		try {
			final GsonBuilder gsonBuilder = new GsonBuilder();
			final Gson gson = gsonBuilder.create();
			BufferedReader reader = new BufferedReader(new FileReader(fileEmployees));
			Type listType = new TypeToken<List<Employee>>(){}.getType();
			employees = gson.fromJson(reader, listType);
		} catch (IOException e) {
			System.out.println("Issue while operating with file " + fileEmployees);
			e.printStackTrace();
			System.exit(1);
		}
		
		String fileName = "result.txt";
		Employee[] employeesArray = Employee.getArrayFromList(employees);
		FileFilter filter = new FileFilter(employeesArray, report, fileName);
		filter.writeEmployeesInFile(); 
	}	
}

