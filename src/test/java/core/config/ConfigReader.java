package core.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final String CONFIG_PATH = "config.properties";
    private static final Properties properties = new Properties();
    private static final String DEFAULT = "NONE";

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_PATH)) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key, DEFAULT);
    }

    public static int getConfigAsInt(String key) {
        String value = get(key);
        return DEFAULT.equals(value) ? 0 : Integer.parseInt(value);
    }

    public static boolean getConfigAsBoolean(String key) {
        String value = get(key);
        return DEFAULT.equals(value) ? false : Boolean.parseBoolean(value);
    }
}
