package core.context;

import java.util.HashMap;
import java.util.Map;

public final class TestContext {
    private final Map<String, Object> data = new HashMap<>();

    public <T> void put(String key, T value) {
        data.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        if (!data.containsKey(key)) {
            throw new IllegalStateException("TestContext key not found: " + key);
        }
        return (T) data.get(key);
    }

}
