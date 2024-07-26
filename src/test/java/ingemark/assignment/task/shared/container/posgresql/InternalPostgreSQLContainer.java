package ingemark.assignment.task.shared.container.posgresql;

import org.testcontainers.containers.PostgreSQLContainer;

public class InternalPostgreSQLContainer extends PostgreSQLContainer<InternalPostgreSQLContainer> {

    private static final String IMAGE_VERSION = "postgres:14.1-alpine";

    private static InternalPostgreSQLContainer container;

    private InternalPostgreSQLContainer() {
        super(IMAGE_VERSION);
    }

    public static InternalPostgreSQLContainer getInstance() {
        if (container == null) {
            return new InternalPostgreSQLContainer();
        }

        return container;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        //JVM handles shut down, do nothing here
    }


}
