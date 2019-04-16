package com.institute.bi.tierc.user.client.fallback;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class BookServiceFallback {
	public String bookByIsbn(Integer isbn) {
		return "Hystrix FallBack";
	}

}
