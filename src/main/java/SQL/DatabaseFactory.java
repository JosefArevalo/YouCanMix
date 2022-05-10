package SQL;

import java.io.IOException;
import java.util.Locale;

public class DatabaseFactory implements IDatabaseFactory {
    public DatabaseFactory() {

    }

    public IDatabase createDatabase(String kind) throws IOException {
        return switch (kind.toLowerCase(Locale.ROOT)) {
            case "mysql" -> new MySQLDatabase();
            case "sqlite" -> new SQLiteDatabase();
            case "postgresql" -> new PostgreSQLDatabase();
            default -> null;
        };
    }
}
