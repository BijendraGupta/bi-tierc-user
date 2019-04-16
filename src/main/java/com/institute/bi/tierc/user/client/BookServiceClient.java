
package com.institute.bi.tierc.user.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import com.institute.bi.tierc.user.client.fallback.BookServiceFallback;

import com.institute.bi.tierc.user.config.RedditFeignConfiguration;
//url = "http://localhost:8084"
@FeignClient(name = "Feign-Client", configuration = RedditFeignConfiguration.class, fallback = BookServiceFallback.class)

public interface BookServiceClient {

	@RequestMapping(method = RequestMethod.GET, path = "/books/{isbn}")

	public String bookByIsbn(@PathVariable("isbn") Integer isbn);

}