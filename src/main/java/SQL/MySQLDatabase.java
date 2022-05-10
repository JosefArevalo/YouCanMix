package SQL;

import CFG.Config;
import CFG.ConfigSingleton;
import Drink.Drink;
import Drink.DrinkBuilder;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;


public class MySQLDatabase extends AbstractSQLDatabase {
    private final Config cfg;

    public MySQLDatabase() throws IOException {
        super();
        this.cfg = ConfigSingleton.getConfig();
    }

    @Override
    public void connect() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%s/%s",
                this.cfg.sqlHost,
                this.cfg.sqlPort,
                this.cfg.sqlDatabase);

        this.conn = DriverManager.getConnection(url, this.cfg.sqlUsername, this.cfg.sqlPassword);
        this.createTables();
    }
}
