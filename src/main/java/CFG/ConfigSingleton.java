package CFG;

import java.io.IOException;

public class ConfigSingleton {
    private static Config cfg;

    public Config getConfig() throws IOException {
        if (ConfigSingleton.cfg != null) {
            return ConfigSingleton.cfg;
        }
        ConfigSingleton.cfg = new Config();
        return ConfigSingleton.cfg;
    }
}
