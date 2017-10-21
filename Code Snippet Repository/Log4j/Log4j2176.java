    @Test
    public void testStack() {
        final Map<String, ThrowableProxy.CacheEntry> map = new HashMap<>();
        final Stack<Class<?>> stack = new Stack<>();
        final Throwable throwable = new IllegalStateException("This is a test");
        final ThrowableProxy proxy = new ThrowableProxy(throwable);
        final ExtendedStackTraceElement[] callerPackageData = proxy.toExtendedStackTrace(stack, map, null,
                throwable.getStackTrace());
        assertNotNull("No package data returned", callerPackageData);
    }
