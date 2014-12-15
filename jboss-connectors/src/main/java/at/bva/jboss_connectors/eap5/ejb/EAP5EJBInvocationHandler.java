package at.bva.jboss_connectors.eap5.ejb;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EAP5EJBInvocationHandler implements InvocationHandler {

	private Object bean;

	public EAP5EJBInvocationHandler(Object bean) {
		this.bean = bean;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		ClassLoader ctxCl = EAP5EJBProvider.setContextClassloaderToLocal();
		try {
			return bean.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(bean, args);
		} finally{
			EAP5EJBProvider.resetContextClassloader(ctxCl);	
		}
	}

}
