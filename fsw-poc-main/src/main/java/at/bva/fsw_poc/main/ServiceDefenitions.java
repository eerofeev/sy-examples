package at.bva.fsw_poc.main;

public interface ServiceDefenitions {
	public static interface ComponentService{
		public static final String STRING_CONVERTER="StringConverter";
		public static final String CURRENCY_CONVERTER="CurrencyConverter";
	}
	
	public static interface CompositeReference{
		public static final String CURRENCY_CONVERTER_EXTERNAL="CurrencyConverterExternal";
	}
}
