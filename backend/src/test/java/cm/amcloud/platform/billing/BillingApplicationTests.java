package cm.amcloud.platform.billing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest
class BillingApplicationTests {

	// Load .env file
	Dotenv dotenv = Dotenv.configure()
	.load();

// Set environment variables
dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

 

}
