
package com.institute.bi.tierc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.institute.bi.tierc.user.dao.repository.LogonRepository;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableFeignClients
@EntityScan("com.institute.bi.tierc.user.*")
@EnableJpaRepositories(basePackages = { "com.institute.bi.tierc.user.dao.repository" })
public class BiTiercUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiTiercUserApplication.class, args);
	}

}
