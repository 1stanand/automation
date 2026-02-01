package core.utils;

public final class JsonContext {
    private final static ThreadLocal<String> ACTIVE_JSON = new ThreadLocal<>();

    private JsonContext() {
    }

    public static void use(String fileName) {
        ACTIVE_JSON.set(fileName);
    }

    public static String get(String jsonPath) {
        String fileName = ACTIVE_JSON.get();
        if (fileName == null) {
            throw new RuntimeException("No JSON context set. Call JsonContext.use(fileName) first.");
        }
        return JsonUtils.getFromJson(fileName, jsonPath);
    }

    public static void clear() {
        ACTIVE_JSON.remove();
    }
}
