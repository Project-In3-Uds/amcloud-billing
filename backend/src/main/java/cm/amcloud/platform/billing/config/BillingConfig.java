package cm.amcloud.platform.billing.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "cm.amcloud.platform.billing.repository")
@EntityScan(basePackages = "cm.amcloud.platform.billing.model")
public class BillingConfig {
}
