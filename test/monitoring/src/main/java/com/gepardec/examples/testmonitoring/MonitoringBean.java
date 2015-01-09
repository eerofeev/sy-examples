package com.gepardec.examples.testmonitoring;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.switchyard.component.bean.Service;

import com.gepardec.examples.testmonitoring.util.Activity;
import com.gepardec.examples.testmonitoring.util.ActivityDBReader;
import com.gepardec.examples.testmonitoring.util.ActivityDBReaderRequestBuilder;
import com.gepardec.examples.testmonitoring.util.OperationReport;
import com.gepardec.examples.testmonitoring.util.ServiceReportBuilder;

@Service(Monitoring.class)
public class MonitoringBean implements Monitoring {

	@Override
	public Map<String, OperationReport> getResponseTimes(MonitoringRequest monitoringRequest) {
		
		try {
			ActivityDBReaderRequestBuilder builder = new ActivityDBReaderRequestBuilder();
	    	builder.fromTimeStamp(monitoringRequest.fromms).service(monitoringRequest.service);
	    	List<Activity> activities = new ActivityDBReader().read(builder);
	    	return new ServiceReportBuilder(activities).getReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	
	}

}
