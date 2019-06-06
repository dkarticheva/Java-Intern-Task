package com.mentormate.task;

import java.util.List;

public class Employee {
	
	private String name;
	private int totalSales;
	private int salesPeriod;
	private double experienceMultiplier;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
	public int getSalesPeriod() {
		return salesPeriod;
	}
	public void setSalesPeriod(int salesPeriod) {
		this.salesPeriod = salesPeriod;
	}
	public double getExperienceMultiplier() {
		return experienceMultiplier;
	}
	public void setExperienceMultiplier(double experienceMultiplier) {
		this.experienceMultiplier = experienceMultiplier;
	}
	public static Employee[] getArrayFromList(List<Employee> e) {
		Employee[] result = new Employee[e.size()];
		int i = 0;
		for (Employee emp : e) {
			result[i] = emp;
			i++;
		}
		return result;
	}
	
}
