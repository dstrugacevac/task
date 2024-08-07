package ingemark.assignment.task.shared.common.infrastructure.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ingemark Assignment Task")
                        .version("1.0")
                        .description("API documentation for Ingemark Assignment Task, developed by David Strugacevac")
                )
                .servers(servers());
    }

    private List<Server> servers() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("LOCAL environment");

        return List.of(localServer);
    }
}
