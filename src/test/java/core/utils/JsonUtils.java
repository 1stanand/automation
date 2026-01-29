package core.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonUtils() {
        // prevent instantiation
    }

    private static File getJsonFile(String fileName) {
        return new File(System.getProperty("user.dir" + "src/test/resources/testdata/" + fileName));
    }

    public static JsonNode getJsonNode(String fileName) {
        try {
            return mapper.readTree(getJsonFile(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read JSON file: " + fileName, e);
        }
    }

    public static String getValue(String fileName, String keyPath) {
        JsonNode node = getJsonNode(fileName);

        for (String key : keyPath.split("\\.")) {
            node = node.get(key);
        }
        return node.asText();
    }

    public static <T> T readAsObject(String fileName, Class<T> clazz) {
        try {
            return mapper.readValue(getJsonFile(fileName), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Unable to map JSON to object: " + clazz.getSimpleName(), e);
        }
    }
}
