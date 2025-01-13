package teco.challenge.challengejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChallengejavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengejavaApplication.class, args);
	}

}
