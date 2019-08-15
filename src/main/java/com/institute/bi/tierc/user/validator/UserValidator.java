
/**

*

 */

package com.institute.bi.tierc.user.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.institute.bi.domain.user.request.User;
import com.institute.bi.tierc.user.utility.PasswordValidator;
import com.institute.bi.tierc.user.utility.RegexValidator;

/**
 * @author bijendra
 *
 */
@Component("userRegistrationValidator")
public class UserValidator implements Validator {

	@Autowired
	private RegexValidator regexValidator;

	@Autowired
	private PasswordValidator passwordValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		User user = (User) target;
		if (StringUtils.isEmpty(user.getEmailId())) {
			error.rejectValue("emailId", "required.field", new Object[] { " emailId" }, "Email Id is required");
		} else if (!regexValidator.checkEmailValidator(user.getEmailId())) {
			error.rejectValue("emailId", "emailId.validate", new Object[] { " emailId" },
					"The format of an email address must be in bijendra.gupta@gmail.com");
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			error.rejectValue("password", "required.field", new Object[] { "Password" }, "Password is required field");
		} else if (!passwordValidator.validate(user.getPassword())) {
			error.rejectValue("password", "password.validate", new Object[] { "Password" },
					"Password must Be between 8 and 40 characters long ,Contain at least one digit, Contain at least one lower case character Contain at least one upper case character and Contain at least on special character from [ @ # $ % ! . ].");
		}

		if (StringUtils.isEmpty(user.getMobileNo())) {
			error.rejectValue("mobileNo", "mobileNo.validate", new Object[] { "mobileNo" },
					"Mobile Number is required field");
		} else if (!regexValidator.checkMobileValidator(user.getMobileNo())) {
			error.rejectValue("mobileNo", "validate.mobile.field", new Object[] { " mobileNo" },
					"EPP-style phone numbers use the format +CCC.NNNNNNNNNNxEEEE, where C is the 1–3 digit country code, N is up to 14 digits, and E is the (optional) extension. The leading plus sign and the dot following the country code are required. The literal “x” character is required only if an extension is provided.");
		}

	}

}