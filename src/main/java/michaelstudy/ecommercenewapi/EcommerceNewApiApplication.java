package michaelstudy.ecommercenewapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class EcommerceNewApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(EcommerceNewApiApplication.class, args);
	}

	@Bean
	public CountDownLatch countDownLatch() {
		return new CountDownLatch(1);
	}

}
