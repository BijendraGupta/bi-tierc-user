/**
*
 */
package com.institute.bi.tierc.user.controller;

import java.util.Arrays;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.institute.bi.tierc.user.service.LogonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author bijendra Here are a couple of ways of setting the active profile:
 * 
 *         At the time of launching the Java application
 *         -Dspring.profiles.active=qa - in the VM properties, OR Do the
 *         following in the application.properties file
 *         spring.application.profiles=qa.
 */
@Profile(value = { "dev", "qa", "prod" })
@RestController
@RequestMapping(value = "v1/baghantInstitute/user")
@Slf4j
@Validated
@Api(value = "LogonController ", description = "Logon Controller to manage user")
public class LogonController extends BaseController {
	@Autowired
	private LogonService logonService;

	@Autowired
	public LogonController(Map<String, Validator> validatorMap) {
		super(validatorMap);
		System.out.println(LogonController.class.getName());
	}

	@ApiOperation(value = "validateLogonDetails", notes = "Validate Logon Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found All Email Forms"),
			@ApiResponse(code = 400, message = "Bad Input Params"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/login")
	public DeferredResult<User> validateLogonDetails(@RequestBody User user) {

		log.info("Validate Login Details, UserId: {}, and password: {} ", user.getEmailId(),
				user.getPassword().replaceAll("[\\s\\S]", "*"));
		DeferredResult<User> deferredResult = new DeferredResult<>();
		Validator validator = validatorMap.get("userLoginValidator");
		validate(validator, user, User.class);
		// User res = new ValidateUserCommand().execute();
		String book = "";// bookServiceClient.bookByIsbn(908);
		// res.setUserName(book);
		User usr = logonService.getUserByEmailId(user);
		deferredResult.setResult(usr);
		return deferredResult;
	}

	@ApiOperation(value = "doRegister", notes = "Do Registration")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Details Saved succesfully"),
			@ApiResponse(code = 400, message = "Bad Input Params"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/registration")
	public DeferredResult<User> doRegister(@RequestBody User user) {

		log.info("Registration UserId: {}, and password: {} ", user.getEmailId(),
				user.getPassword().replaceAll("[\\s\\S]", "*"));
		DeferredResult<User> deferredResult = new DeferredResult<>();
		Validator validator = validatorMap.get("userRegistrationValidator");
		validate(validator, user, User.class);

		User usr = logonService.doUserRegistration(user);
		deferredResult.setResult(usr);
		return deferredResult;
	}
}
