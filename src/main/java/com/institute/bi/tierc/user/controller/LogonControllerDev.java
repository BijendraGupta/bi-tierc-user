/**
*
 */
package com.institute.bi.tierc.user.controller;

import java.util.Map;

import org.apache.commons.configuration.web.AppletConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.institute.bi.domain.user.request.User;
import com.institute.bi.tierc.user.client.BookServiceClient;
import com.institute.bi.tierc.user.command.ValidateUserCommand;
import com.institute.bi.tierc.user.exception.UserNotExistException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author bijendra
 *
 */
@Profile("qa")
@RestController
@RequestMapping(value = "v1/baghantInstitute/user")
@Slf4j
@Validated
@Api(value = "LogonControllerDev", description = "Logon Controller to manage user")
public class LogonControllerDev extends BaseController {
	@Autowired
	private BookServiceClient bookServiceClient;

	@Autowired
	public LogonControllerDev(Map<String, Validator> validatorMap) {
		super(validatorMap);
		System.out.println(LogonControllerDev.class.getName());
	}

	@ApiOperation(value = "validateLogonDetails", notes = "Validate Logon Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found All Email Forms"),
			@ApiResponse(code = 400, message = "Bad Input Params"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/login")
	public DeferredResult<User> validateLogonDetails(@RequestBody User user) {
		log.info("Validate Login Details, UserId: {} Name: {}, and password: {} ", user.getUserId(), user.getUserName(),
				user.getPassword());
		DeferredResult<User> deferredResult = new DeferredResult<>();
		Validator validator = validatorMap.get("userValidator");
		validate(validator, user, User.class);
		User res = new ValidateUserCommand().execute();
		String book = bookServiceClient.bookByIsbn(908);
		res.setUserName(book);
		deferredResult.setResult(res);
		return deferredResult;
	}
}
