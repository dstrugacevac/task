package ingemark.assignment.task.shared.container.posgresql;

import org.testcontainers.containers.ContainerState;
import org.testcontainers.delegate.AbstractDatabaseDelegate;
import org.testcontainers.exception.ConnectionCreationException;
import org.testcontainers.ext.ScriptUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

public class TestContainerPostgresSQLDelegate extends AbstractDatabaseDelegate<Connection> {

    private final ContainerState container;

    public TestContainerPostgresSQLDelegate(ContainerState container) {
        this.container = container;
    }

    @Override
    protected Connection createNewConnection() {
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", "test");
            connectionProps.put("password", "test");
            return DriverManager.getConnection(
                    String.format("jdbc:postgresql://localhost:%s/test", container.getFirstMappedPort()),
                    connectionProps);
        } catch (Exception e) {
            throw new ConnectionCreationException("Could not obtain PostgresSQL connection", e);
        }
    }

    @Override
    public void execute(String statement, String scriptPath, int lineNumber, boolean continueOnError, boolean ignoreFailedDrops) {
        try {
            ResultSet result = getConnection().prepareStatement(statement).executeQuery();
            result.next();
            if (result.getObject(1, Integer.class).equals(666)) {
            } else {
                throw new ScriptUtils.ScriptStatementFailedException(statement, lineNumber, scriptPath);
            }
        } catch (Exception e) {
            throw new ScriptUtils.ScriptStatementFailedException(statement, lineNumber, scriptPath, e);
        }
    }

    @Override
    protected void closeConnectionQuietly(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
