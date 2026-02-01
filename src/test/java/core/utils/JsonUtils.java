package core.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

public final class JsonUtils {
    private static JsonMapper mapper = new JsonMapper();
    private static String jsonDir = System.getProperty("user.dir") + "/src/test/resources/testdata/";

    private JsonUtils() {
    }

    private static JsonNode getJsonAsNodes(String fileName) {
        try {
            File file = new File(jsonDir + fileName);
            return mapper.readTree(file);
        } catch (IOException e) {
            throw new RuntimeException(fileName + " not found in " + jsonDir, e);
        }
    }

    public static String getFromJson(String fileName, String jsonPath) {
        JsonNode node = getJsonAsNodes(fileName);

        for (String key : jsonPath.split("\\.")) {
            node = node.get(key);
            if (node == null) {
                throw new RuntimeException("Invalid Json Path " + jsonPath);
            }
        }
        if (node.isNull()) {
            return "";
        }
        return node.asText();
    }
}
