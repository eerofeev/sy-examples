package com.gepardec.examples.testmonitoring;

import java.util.Map;

import com.gepardec.examples.testmonitoring.util.OperationReport;
public interface Monitoring {
	public Map<String, OperationReport> getResponseTimes(MonitoringRequest monitoringRequest);
}
