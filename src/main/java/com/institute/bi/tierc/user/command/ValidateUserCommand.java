
/**

*

 */

package com.institute.bi.tierc.user.command;

import java.util.ArrayList;
import java.util.List;

import com.institute.bi.domain.user.request.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommand.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author bijendra
 *
 * 
 * 
 */
@Slf4j
public class ValidateUserCommand extends HystrixCommand<User> {
	private static final Setter cachedSetter = Setter
			.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserMngtGroup"))
			.andCommandKey(HystrixCommandKey.Factory.asKey("ValidateUserCommand"));

	public ValidateUserCommand() {
		super(cachedSetter);
	}

	@Override
	protected User run() throws Exception {
		List<String> s=new ArrayList<>();
		s.stream().filter(f->{return f.equals("");});
		//.emailId("bijendrakrguptaitfiem@gmail.com").password("abcd").build();
		return User.builder().emailId("bijendrakrguptaitfiem@gmail.com").password("abcd").build();
	}

	@Override
	public User getFallback() {
		Throwable t = this.getFailedExecutionException();
		if (t != null) {
			log.error("Fallback {}", t.getMessage());
		} else {
			log.error("Fallback");
		}
		return null;
	}

}
