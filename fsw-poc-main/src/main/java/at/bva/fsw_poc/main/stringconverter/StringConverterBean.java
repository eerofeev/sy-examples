package at.bva.fsw_poc.main.stringconverter;

import java.net.MalformedURLException;

import javax.naming.NamingException;

import org.switchyard.component.bean.Service;

import at.gepardec.jboss5.ejb_invoker.JBoss5Context;

@Service(StringConverter.class)
public class StringConverterBean implements StringConverter {
	
	/*@Inject
	@EAP5EJB
	@EAP5Lookup(url="jnp://127.0.0.1:1399", user="admin", password="admin", beanJNDI="Converter/remote-at.bva.remote_ejb.StringConverter")
	EAP5EJBProvider eap5EJBProvider;*/

	@Override
	public StringConverterResponse toLowerCase(StringConverterRequest request){
		try {
			return toLowerCaseEJB(request);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public StringConverterResponse toLowerCaseEJB(StringConverterRequest request) throws NamingException, MalformedURLException, ClassNotFoundException{
		JBoss5Context  ctx = new JBoss5Context("jnp://127.0.0.1:1399", "admin", "admin");
		at.bva.remote_ejb.StringConverter remoteConverter = ctx.lookup(
				"Converter/remote-at.bva.remote_ejb.StringConverter", at.bva.remote_ejb.StringConverter.class);
		StringConverterResponse response = new StringConverterResponse();		
		response.setContent(remoteConverter.toLowerCase(request.getContent()));

		return response;
	}

	@Override
	public StringConverterResponse toUpperCase(StringConverterRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
