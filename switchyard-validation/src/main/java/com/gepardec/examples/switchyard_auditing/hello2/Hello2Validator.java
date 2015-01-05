package com.gepardec.examples.switchyard_auditing.hello2;

import javax.inject.Named;

import org.switchyard.annotations.Validator;
import org.switchyard.validate.ValidationResult;

import com.gepardec.examples.switchyard_auditing.generated.hello2.SayHello2;

@Named("Hello2Validator")
public class Hello2Validator {
	
	public static class MyValidationResult implements ValidationResult{
		
		public SayHello2 testable;
		
		public MyValidationResult(SayHello2 t) {
			this.testable = t;
		}

		@Override
		public boolean isValid() {
			// TODO Auto-generated method stub
			return testable != null 
					&& testable.getHello2Request() != null
					&& testable.getHello2Request().getValue() != null
					&& !testable.getHello2Request().getValue().isEmpty()
					&& !testable.getHello2Request().getValue().contains("!");
		}

		@Override
		public String getDetail() {
			return "invalid";
		}
		
	}

	@Validator(name = "{http://hello2.simulation.fsw_poc.bva.at/}sayHello2")
	public ValidationResult validate(SayHello2 content) {
		return new MyValidationResult(content);
	}

}
