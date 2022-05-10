package SQL;

import java.io.IOException;

public interface IDatabaseFactory {
    IDatabase createDatabase(String kind) throws IOException;
}
