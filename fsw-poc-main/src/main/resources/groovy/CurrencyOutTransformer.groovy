
import at.bva.fsw_poc.main.currencyconverter.external.ObjectFactory;
import at.bva.fsw_poc.main.currencyconverter.external.Request;
import at.bva.fsw_poc.main.currencyconverter.external.Code;
import at.bva.fsw_poc.main.currencyconverter.CurrencyCode;

//Request newRequest = new ObjectFactory().createRequest();
/*Code from = Code.fromValue(request.getBody().currencyCodeFrom.value())
Code to = Code.fromValue(request.getBody().currencyCodeTo.value())
newRequest.setCurrencyCodeFrom(from)
newRequest.setCurrencyCodeTo(to)*/
println request.getBody()
//request.getBody().setCurrencyCodeFrom(CurrencyCode.fromValue("CAD"))
println request.getBody()
return request