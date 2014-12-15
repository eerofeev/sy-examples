package at.bva.fsw_poc.main;

public class SYEnvironment {
	public static final String SY_CAMEL_PROTOCOL = "switchyard://";
	
	public static String getCamelEndpoint(String serviceName){
		if(serviceName == null){
			return null;
		}
		return SY_CAMEL_PROTOCOL + serviceName;
	}
}
