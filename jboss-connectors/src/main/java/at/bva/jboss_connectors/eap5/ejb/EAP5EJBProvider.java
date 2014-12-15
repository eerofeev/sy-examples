package at.bva.jboss_connectors.eap5.ejb;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EAP5EJBProvider {
	
	private static final Logger LOG = LoggerFactory.getLogger(EAP5EJBProvider.class);
	
	private String url;
	private String beanJNDI;
	private String user;
	private String password;
	private Object bean;
	
	public EAP5EJBProvider() {
		// TODO Auto-generated constructor stub
	}
	
	public EAP5EJBProvider(String url, String beanJNDI, String user, String password) {
		this.url = url;
		this.beanJNDI = beanJNDI;
		this.user = user;
		this.password = password;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<? extends T> beanClass) throws NamingException{
		if(bean == null){
			try {
				initBean(beanClass);
			} catch (Exception e) {
				LOG.error("Can not return bean.", e);
				return null;
			}
		}
		
		if(bean == null){
			LOG.error("Can not return bean. Bean is null");
			return null;
		}
			
		return (T) bean;
		
	}

	private void initBean(Class<?> beanClass) throws NamingException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		ClassLoader cl = setContextClassloaderToLocal();
		
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, url);
		jndiProperties.put(Context.SECURITY_PRINCIPAL, user);
		jndiProperties.put(Context.SECURITY_CREDENTIALS, password);
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		jndiProperties.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		
		Object obj = null;
		
		try {			
			final Context context = new InitialContext(jndiProperties);
			obj = context.lookup(beanJNDI);
			context.close();
		} catch (NamingException e) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Can not retrieve bean [")
			.append(url)
			.append(" ")
			.append(user)
			.append(" ")
			.append(password)
			.append(" ")
			.append(beanJNDI)
			.append("]");
			LOG.error(buffer.toString(), e);
		} finally {
			resetContextClassloader(cl);
		}
		
		InvocationHandler handler = new EAP5EJBInvocationHandler(obj);
		bean = beanClass.cast(Proxy.newProxyInstance(cl, new Class[] {beanClass}, handler));
	}
	
	static void resetContextClassloader(ClassLoader ctxCl) {
		Thread.currentThread().setContextClassLoader(ctxCl);
	}

	static ClassLoader setContextClassloaderToLocal() {
		ClassLoader ctxCl = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(
				EAP5EJBProvider.class.getClassLoader());
		return ctxCl;
	}
}
