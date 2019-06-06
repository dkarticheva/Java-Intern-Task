package com.mentormate.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class FileFilter {

	private Employee[] employees;
	private static Report report;
	private static String fileName;
	
	public FileFilter(Employee[] employees, Report rep, String filename) {
		this.employees = employees;
		report = rep;
		fileName = filename;
	}
	
	private static double calculateScore(Employee e) {
		double salesPerPeriod = e.getTotalSales() / e.getSalesPeriod();
		if (report.getUseExprienceMultiplier() == true) {
			return salesPerPeriod * e.getExperienceMultiplier();
		}
		return salesPerPeriod;
	}
	
	private Employee[] getEmployeesWithHighestResults() {
		
		double topXpercent = (report.getTopPerformersThreshold() / 100) * employees.length;
		int topX = (int) Math.round(topXpercent);
		if (topX < 1) {
			topX = 1;
		}
		Arrays.sort(employees, new Comparator<Employee>() {

			@Override
			public int compare(Employee e1, Employee e2) {
				double salesPerPeriod1 = e1.getTotalSales() / e1.getSalesPeriod();
				double salesPerPeriod2 = e2.getTotalSales() / e2.getSalesPeriod();
				if (report.getUseExprienceMultiplier() == true ) {
					salesPerPeriod1 *= e1.getExperienceMultiplier();
					salesPerPeriod2 *= e2.getExperienceMultiplier();
				}
				return (int)(salesPerPeriod2 - salesPerPeriod1);
			}
			
		});
		Employee[] topEmployees = new Employee[topX];
		int i = 0; int added = 0;
		while (i<employees.length && added < topXpercent) {
			if (employees[i].getSalesPeriod() <= report.getPeriodLimit()) {
				topEmployees[added] = employees[i];
				added++;
			}
			i++;
		}
		
		return topEmployees;
	}
	
	public void writeEmployeesInFile() {
		Employee[] topEmployees = getEmployeesWithHighestResults();
		
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Issue while creating file " + fileName);
				e.printStackTrace();
			}
		}
		
		FileWriter csvWriter;
		try {
			csvWriter = new FileWriter(fileName);
			csvWriter.append("Name ");  
			csvWriter.append(",");  
			csvWriter.append(" Score"); 
			for (Employee e : topEmployees) {
				csvWriter.append("\n");
				csvWriter.append(e.getName());
				csvWriter.append(", ");
				csvWriter.append(String.valueOf(calculateScore(e)));
			}
			csvWriter.flush();  
			csvWriter.close(); 
			
		} catch (IOException e1) {
			System.out.println("Issue while opening file " + fileName);
			e1.printStackTrace();
		}
		System.out.println("Successfully created file " + fileName + " which contains the report results");
	}
}