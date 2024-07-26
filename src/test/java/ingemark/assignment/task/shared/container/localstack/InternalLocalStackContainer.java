package ingemark.assignment.task.shared.container.localstack;

import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

public class InternalLocalStackContainer extends LocalStackContainer {

    private static final String IMAGE_VERSION = "localstack/localstack:2.1.0";

    private static InternalLocalStackContainer container;

    private InternalLocalStackContainer(boolean legacyMode) {
        super(DockerImageName.parse(IMAGE_VERSION), legacyMode);
    }

    public static InternalLocalStackContainer getInstance(boolean legacyMode) {
        if (container == null) {
            container = new InternalLocalStackContainer(legacyMode);
        }

        return container;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {

    }
}
