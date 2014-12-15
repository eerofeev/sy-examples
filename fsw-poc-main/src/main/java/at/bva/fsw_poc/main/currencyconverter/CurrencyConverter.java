package at.bva.fsw_poc.main.currencyconverter;

public interface CurrencyConverter {
	CurrencyConverterResponse convert(CurrencyConverterRequest request);
}
