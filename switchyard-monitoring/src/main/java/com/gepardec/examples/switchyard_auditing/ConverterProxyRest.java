package com.gepardec.examples.switchyard_auditing;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gepardec.examples.switchyard_auditing.generated.converter.Convert;
import com.gepardec.examples.switchyard_auditing.generated.converter.ConvertResponse;

@Path("/converter")
public interface ConverterProxyRest extends ConverterProxy {
	@Override
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ConvertResponse convert(Convert convert);
}
