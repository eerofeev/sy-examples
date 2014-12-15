import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.cxf.interceptor.InInterceptors;

import at.bva.remote_ejb2.HelloHome;
import at.bva.remote_ejb2.HelloObject;


public class Test {

	public static void main(String[] args) throws NamingException, RemoteException, CreateException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1399");
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "admin");
		jndiProperties.put(Context.SECURITY_CREDENTIALS, "admin");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		jndiProperties.setProperty("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		
		Object obj = new InitialContext(jndiProperties).lookup("Hello/remote");
		HelloHome ejbHome = (HelloHome)
		        PortableRemoteObject.narrow(obj,HelloHome.class);
		HelloObject ejbObject = ejbHome.create();
		String message = ejbObject.sayHello();

	    //A drum roll please.
	    System.out.println( message );
	}

}
