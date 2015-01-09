package com.gepardec.examples.testvalidation;

import javax.inject.Named;

import org.switchyard.annotations.Validator;
import org.switchyard.validate.ValidationResult;

import com.gepardec.examples.testvalidation.generated.hello.SayHello;

@Named("HelloValidator")
public class HelloValidator {
	@Validator(name = "{http://testwss.examples.gepardec.com/}sayHello")
	public ValidationResult validate(SayHello content) {
		return new HelloValidationResult(content);
	}
}
