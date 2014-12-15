package at.bva.fsw_poc.main.stringconverter;


public interface StringConverter {
	StringConverterResponse toLowerCase(StringConverterRequest request);
	StringConverterResponse toUpperCase(StringConverterRequest request);
}
