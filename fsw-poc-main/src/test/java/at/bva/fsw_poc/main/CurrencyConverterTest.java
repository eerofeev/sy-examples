package at.bva.fsw_poc.main;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.test.Invoker;
import org.switchyard.test.MockHandler;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = AbstractTest.SWITCHYARD_XML, mixins = {
		CDIMixIn.class})
public class CurrencyConverterTest extends AbstractTest{
	@ServiceOperation(ServiceDefenitions.ComponentService.CURRENCY_CONVERTER)
	private Invoker service;
	
	@Test
	public void testStringConverter() throws JAXBException, IOException{
		String request = getResource(TestMessages.CURRENCY_CONVERTER_REQUEST);
		String externalResponse = getResource(TestMessages.CURRENCY_CONVERTER_EXTERNAL_RESPONSE);
		MockHandler mock = mockInOutService(ServiceDefenitions.CompositeReference.CURRENCY_CONVERTER_EXTERNAL);
		mock.replyWithOut(externalResponse);
		
		service.sendInOut(request);
	}
}
