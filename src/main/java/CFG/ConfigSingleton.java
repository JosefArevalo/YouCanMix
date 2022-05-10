package CFG;

import java.io.IOException;

public class ConfigSingleton {
    private static Config cfg;

    public static Config getConfig() throws IOException {
        if (cfg == null) {
            cfg = new Config();
        }

        return cfg;
    }
}
