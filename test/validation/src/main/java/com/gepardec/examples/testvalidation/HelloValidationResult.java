package com.gepardec.examples.testvalidation;

import org.switchyard.validate.ValidationResult;

import com.gepardec.examples.testvalidation.generated.hello.SayHello;

public class HelloValidationResult implements ValidationResult{
	
	public SayHello testable;
	
	public HelloValidationResult(SayHello t) {
		this.testable = t;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return testable != null 
				&& testable.getString() != null
				&& !testable.getString().isEmpty()
				&& !testable.getString().contains("!");
	}

	@Override
	public String getDetail() {
		return "invalid. Must not be empty and must not contain '!'";
	}
	
}

