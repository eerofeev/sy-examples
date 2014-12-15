package at.bva.fsw_poc.eap5ejb;

import org.switchyard.component.bean.Service;

import at.bva.remote_ejb.StringConverter;
import at.bva.remote_ejb2.HelloObject;
import at.gepardec.jboss5.ejb_invoker.JBoss5Context;

@Service(StringConverter.class)
public class StringConverterEJBBean implements StringConverter {

	/*at.bva.remote_ejb.StringConverter remoteConverter;
	
	public StringConverterEJBBean() throws NamingException {
		JBoss5Context  ctx = new JBoss5Context("jnp://127.0.0.1:1399", "admin", "admin");
		remoteConverter = ctx.lookup(
				"Converter/remote-at.bva.remote_ejb.StringConverter", at.bva.remote_ejb.StringConverter.class);
	}*/
	
	/*HelloObject remoteConverter;
	
	public StringConverterEJBBean() {
		JBoss5Context  ctx = new JBoss5Context("jnp://127.0.0.1:1399", "admin", "admin");
		remoteConverter = ctx.lookup(
				"Hello", HelloObject.class);
	}*/

	@Override
	public String toLowerCase(String input) {
		//return remoteConverter.toLowerCase(input);
		return null;
	}

	@Override
	public String toUpperCase(String input) {
		//return remoteConverter.toUpperCase(input);
		return null;
	}

}
