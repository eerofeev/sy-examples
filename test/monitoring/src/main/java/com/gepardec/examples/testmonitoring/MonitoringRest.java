package com.gepardec.examples.testmonitoring;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gepardec.examples.testmonitoring.util.OperationReport;

@Path("/responsetimes")
public interface MonitoringRest extends Monitoring{
	@Override
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<String, OperationReport> getResponseTimes(MonitoringRequest monitoringRequest);
}
