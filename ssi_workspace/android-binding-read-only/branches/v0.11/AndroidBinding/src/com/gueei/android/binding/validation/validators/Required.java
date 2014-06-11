package com.gueei.android.binding.validation.validators;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.gueei.android.binding.validation.ValidatorBase;

@Retention(RetentionPolicy.RUNTIME)
public @interface Required{
	public Class<?> Validator() default RequiredValidator.class;

	public String ErrorMessage() default "%fieldname% is a required field";
	
	public class RequiredValidator extends ValidatorBase<Required> {

		@Override
		public Class<?> getAcceptedAnnotation() {
			return Required.class;
		}

		@Override
		protected String doFormatErrorMessage(Required parameters,
				String fieldName) {
			return parameters.ErrorMessage().replace("%fieldname%", fieldName);
		}

		@Override
		protected boolean doValidate(Object value, Required parameters,
				Object model) {
			if (value==null) return false;
			if (Boolean.FALSE.equals(value)) return false;
			if (value instanceof CharSequence){
				if (((CharSequence) value).length() == 0) return false;
			}
			return true;
		}

	}
}
