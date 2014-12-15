package at.bva.fsw_poc.eap5ejb;

import org.switchyard.component.bean.Service;

import at.bva.fsw_poc.eap5ejb2.Eap5Ejb2;
import at.bva.fsw_poc.eap5ejb2.Eap5Ejb2Request;
import at.bva.fsw_poc.eap5ejb2.Eap5Ejb2Response;

@Service(value=Eap5Ejb2.class, name="Eap5Ejb2PortType")
public class Eap5Ejb2Bean implements Eap5Ejb2 {

	@Override
	public Eap5Ejb2Response m(Eap5Ejb2Request request){

		
		Eap5Ejb2Response response = new Eap5Ejb2Response();
		/*try {
			/*Object remoteObject;
			JBoss5Context  ctx = new JBoss5Context("jnp://127.0.0.1:1399", "admin", "admin");
			remoteObject = ctx.lookup(
					"Hello");
			
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			JBoss5Context.setContextClassloaderToLocal();
			HelloHome ejbHome = (HelloHome)
			        PortableRemoteObject.narrow(remoteObject,HelloHome.class);
			HelloObject ejbObject = ejbHome.create();
			ejbObject = ctx.applyInvocationHandler(ejbObject);
			Thread.currentThread().setContextClassLoader(cl);
			String message = ejbObject.sayHello();*/
			response.setResponseMsg("asd");
		/*} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return response;
	}

}
