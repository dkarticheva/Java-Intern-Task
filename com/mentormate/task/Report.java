package com.mentormate.task;

public class Report {

	private double topPerformersThreshold;
	private boolean useExprienceMultiplier;
	private int periodLimit;
	
	public double getTopPerformersThreshold() {
		return topPerformersThreshold;
	}
	public void setTopPerformersThreshold(double topPerformersThreshold) {
		this.topPerformersThreshold = topPerformersThreshold;
	}
	public boolean getUseExprienceMultiplier() {
		return useExprienceMultiplier;
	}
	public void setUseExprienceMultiplier(boolean useExprienceMultiplier) {
		this.useExprienceMultiplier = useExprienceMultiplier;
	}
	public int getPeriodLimit() {
		return periodLimit;
	}
	public void setPeriodLimit(int periodLimit) {
		this.periodLimit = periodLimit;
	}
}
