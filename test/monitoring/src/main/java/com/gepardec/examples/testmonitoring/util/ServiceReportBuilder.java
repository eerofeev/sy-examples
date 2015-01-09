package com.gepardec.examples.testmonitoring.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ServiceReportBuilder {
	List<Activity> activities;
	Map<String, List<String>> operationsToIds;
	Map<String, Time> operationsToTime;
	Map<String, Time> idsToTime;
	
	public static class Time{
		public long start;
		public long duration;
		public long min;
		public long max;
		public int maxsec;
		public long maxsecat;
		
		private Queue<Long> rollingQueue;
		
		public Time() {
			min = 1000000;
			max = 0;
			maxsec = 0;
			rollingQueue = new LinkedList<Long>();
		}
		
		public void trymin(long newmin){
			if(newmin < min){
				min = newmin;
			}
		}
		
		public void trymax(long newmax){
			if(newmax > max){
				max = newmax;
			}
		}
		
		public void duration(long time){
			trymin(time);
			trymax(time);
			duration = duration + time;
		}
		
		public void timestamp(long timestamp){
			while(rollingQueue.peek() != null && timestamp > rollingQueue.peek() + 1000){
				rollingQueue.remove();
			}
			
			rollingQueue.add(timestamp);
			int nr = rollingQueue.size();
			if(nr > maxsec){
				maxsec = nr;
				maxsecat = rollingQueue.peek();
			}
		}
	}
	
	public ServiceReportBuilder(List<Activity> activities) {
		this.activities = activities;
		operationsToIds = new HashMap<String, List<String>>();
		operationsToTime = new HashMap<String, Time>();
		idsToTime = new HashMap<String, Time>();
		scan();
	}

	private void scan() {
		for(Activity activity : activities){
			if(operationsToIds.get(activity.getOperation())== null ){
				operationsToIds.put(activity.getOperation(), new ArrayList<String>());
				operationsToTime.put(activity.getOperation(), new Time());
			}
			if(!operationsToIds.get(activity.getOperation()).contains(activity.getUnitId())){
				operationsToIds.get(activity.getOperation()).add(activity.getUnitId());
			}
			if(activity.unitIndex == 0){
				Time time = new Time();
				time.start = activity.getTimestamp();
				idsToTime.put(activity.getUnitId(), time);
				operationsToTime.get(activity.getOperation()).timestamp(time.start);
			} else {
				long duration = activity.getTimestamp() - idsToTime.get(activity.getUnitId()).start;
				idsToTime.get(activity.getUnitId()).duration = duration;
				operationsToTime.get(activity.getOperation()).duration(duration);
			}
		}
	}
	
	public Map<String, OperationReport> getReport(){
		Map<String, OperationReport> report = new HashMap<String, OperationReport>();
		
		for(String operation : operationsToIds.keySet()){
			OperationReport operationReport = new OperationReport();
			operationReport.calls = operationsToIds.get(operation).size();
			operationReport.average = (int) (operationsToTime.get(operation).duration / operationReport.calls);
			operationReport.min = (int) operationsToTime.get(operation).min;
			operationReport.max = (int) operationsToTime.get(operation).max;
			operationReport.maxsec = operationsToTime.get(operation).maxsec;
			operationReport.maxsecat = operationsToTime.get(operation).maxsecat;
			report.put(operation, operationReport);
		}
		
		return report;
	}
}
