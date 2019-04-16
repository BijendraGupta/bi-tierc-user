
/**

*

 */

package com.institute.bi.tierc.user.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.institute.bi.domain.user.request.User;

/**
 * @author gupbi001
 *
 */
@Component("userValidator")
public class UserValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		User user = (User) target;
		if (StringUtils.isEmpty(user.getUserId())) {
			error.rejectValue("userId", "required.field", new Object[] { " User Id" }, "User Id is required");
		}

		if (StringUtils.isEmpty((user.getEmailId()))) {
			error.rejectValue("emailId", "required.filed", new Object[] { "Email ID" }, "Email Id is required field");
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			error.rejectValue("password", "required.field", new Object[] { "Password" }, "Password is required field");
		}

	}

}