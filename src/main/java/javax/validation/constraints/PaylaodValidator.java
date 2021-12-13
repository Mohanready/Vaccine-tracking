package javax.validation.constraints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class PaylaodValidator {
	public static Map<String, Object> setErrorMessage(BindingResult result) {
		Map<String, Object> errorMap = new HashMap<>();
		List<String> validations = new ArrayList<>();
		if (!result.getAllErrors().isEmpty()) {
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				validations.add(error.getDefaultMessage());
			}
		}
		errorMap.put("message", validations);
		return errorMap;
	}
}
