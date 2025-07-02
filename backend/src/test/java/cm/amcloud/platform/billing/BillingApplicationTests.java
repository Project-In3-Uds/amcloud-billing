package cm.amcloud.platform.billing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    // --- Database configuration for tests (using H2 in-memory) ---
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",

    // --- JPA and Hibernate configuration properties ---
    "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect",
    "spring.sql.init.mode=never", // Disable running data.sql for tests to avoid incompatibility with H2
    "spring.jpa.hibernate.ddl-auto=update",
    "spring.jpa.open-in-view=false",

    // --- Server Port Configuration ---
    "server.port=8082",

    // --- Spring Cloud Config Server URL ---
    "spring.cloud.config.uri=http://localhost:8888",
    "spring.cloud.config.enabled=false"
})
class BillingApplicationTests {

    @Test
    void contextLoads() {
        // The test passes if the application context loads without throwing exceptions.
    }
}
