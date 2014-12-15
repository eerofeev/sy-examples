package at.bva.fsw_poc.main;

import org.switchyard.annotations.Transformer;

import at.bva.fsw_poc.main.stringconverter.StringConverterRequest;
import at.bva.fsw_poc.main.stringconverter.StringConverterResponse;

public final class StringConverterTransformer {

	@Transformer
	public String transformStringConverterRequestToString(
			StringConverterRequest from) {
		// TODO Auto-generated method stub
		return from.getContent();
	}

	@Transformer
	public StringConverterResponse transformStringToStringConverterResponse(
			String from) {
		StringConverterResponse response = new StringConverterResponse();
		response.setContent(from);
		return response;
	}

}
