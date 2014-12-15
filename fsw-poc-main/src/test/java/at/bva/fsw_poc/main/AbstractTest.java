package at.bva.fsw_poc.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.Exchange;
import org.switchyard.test.MockHandler;
import org.switchyard.test.SwitchYardTestKit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



abstract public class AbstractTest {
	
	public static final Logger LOG = LoggerFactory.getLogger(AbstractTest.class);
	
	public static final String SWITCHYARD_XML = "src/main/resources/META-INF/switchyard.xml";
	
	protected SwitchYardTestKit testKit;
	protected Map<String, MockHandler> mocks;
	
	public String getResource(String url) throws IOException{
		return IOUtils.toString(this.getClass().getResourceAsStream("/" + url), "UTF-8");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T unmarshalResource(String url, Class<? extends T> clazz)
			throws IOException, JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller u = jc.createUnmarshaller();
		return (T) u.unmarshal(this.getClass().getResourceAsStream("/" + url));
	}
	
	public AbstractTest() {
		mocks = new HashMap<String, MockHandler>();
	}
	
	@BeforeClass
	public static void before() throws IOException{
	}
	
	@AfterClass
	public static void after() throws IOException{
	}
	
	public void printMessages(MockHandler mockSvc, String txt) {
		for (Exchange exchange : mockSvc.getMessages()) {
			LOG.info(txt + ": " + exchange.getMessage().getContent(String.class));
		}
	}
	
	public MockHandler mockInOutService(String name){
		if(name !=null){
			testKit.removeService(name);
			mocks.put(name, testKit.registerInOutService(name));
			return mocks.get(name);
		}
		return null;
	}
	
	public void assertMessageCount(String serviceName, int cnt){
		if(!mocks.containsKey(serviceName)){
			assertTrue(serviceName + " is not mocked", false);
		}
		assertEquals(serviceName, cnt, mocks.get(serviceName).getMessages().size());
	}
	
}
