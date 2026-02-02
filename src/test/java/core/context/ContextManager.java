package core.context;

public class ContextManager {
    private static final ThreadLocal<TestContext> CONTEXT = ThreadLocal.withInitial(TestContext::new);

    private ContextManager() {
    }

    public static TestContext get() {
        return CONTEXT.get();
    }

    public static void cleanContext() {
        CONTEXT.remove();
    }

}
