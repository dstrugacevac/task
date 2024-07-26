package ingemark.assignment.task.shared.test;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.spring.api.DBRider;
import ingemark.assignment.task.shared.container.localstack.InternalLocalStackContainer;
import ingemark.assignment.task.shared.container.posgresql.InternalPostgreSQLContainer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.*;

@DBRider
@DBUnit

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")

@Testcontainers

@ExtendWith(SpringExtension.class)
public class BaseTest {

    @Container
    public static PostgreSQLContainer postgreSQLContainer = InternalPostgreSQLContainer.getInstance()
            .waitingFor(Wait.forLogMessage(".*database system is ready to accept connections.*\\n", 1));
    @Container
    public static LocalStackContainer localStack =
            InternalLocalStackContainer.getInstance(false)
                    .withServices(SES, SQS);
    @LocalServerPort
    protected int port;

    @DynamicPropertySource
    private static void properties(DynamicPropertyRegistry registry) {
        registry.add("datasource.driver-class-name", () -> "org.postgresql.Driver");
        registry.add("DATABASE_URL", () -> postgreSQLContainer.getJdbcUrl());
        registry.add("DATABASE_USERNAME", () -> postgreSQLContainer.getUsername());
        registry.add("DATABASE_PASSWORD", () -> postgreSQLContainer.getPassword());
    }
}