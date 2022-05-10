package CFG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public final String sqlMethod;
    public final String sqlHost;
    public final String sqlPort;
    public final String sqlDatabase;
    public final String sqlUsername;
    public final String sqlPassword;


    public Config() throws IOException {
        Properties props = new Properties();
        InputStream is = new FileInputStream("config.properties");
        props.load(is);

        this.sqlMethod = props.getProperty("sql_method");
        this.sqlHost = props.getProperty("sql_host");
        this.sqlPort = props.getProperty("sql_port");
        this.sqlDatabase = props.getProperty("sql_database");
        this.sqlUsername = props.getProperty("sql_username");
        this.sqlPassword = props.getProperty("sql_password");
    }
}
