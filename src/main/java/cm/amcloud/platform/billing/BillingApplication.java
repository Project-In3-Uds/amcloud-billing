package cm.amcloud.platform.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BillingApplication {

	public static void main(String[] args) {

		// Load .env file
        Dotenv dotenv = Dotenv.configure()
                 .load();

        // Set environment variables
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(BillingApplication.class, args);
	}

}
