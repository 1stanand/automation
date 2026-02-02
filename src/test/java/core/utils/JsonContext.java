package core.utils;

public final class JsonContext {
    private final static ThreadLocal<String> ACTIVE_JSON = new ThreadLocal<>();
    private final static ThreadLocal<String> ACTIVE_NODE = new ThreadLocal<>();

    private JsonContext() {
    }

    public static void use(String fileName) {
        ACTIVE_JSON.set(fileName);
        ACTIVE_NODE.remove();
    }

    public static void use(String fileName, String scenarioNode) {
        ACTIVE_JSON.set(fileName);
        ACTIVE_NODE.set(scenarioNode);
    }

    public static String get(String jsonPath) {
        String fileName = ACTIVE_JSON.get();
        String baseNode = ACTIVE_NODE.get();
        if (fileName == null) {
            throw new RuntimeException("No JSON context set. Call JsonContext.use(fileName) first.");
        }
        String keyPath = (baseNode == null) ? jsonPath : baseNode + "." + jsonPath;
        return JsonUtils.getFromJson(fileName, keyPath);
    }

    public static void clear() {
        ACTIVE_JSON.remove();
        ACTIVE_NODE.remove();
    }
}
