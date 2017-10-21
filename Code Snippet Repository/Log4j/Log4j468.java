    @Test
    public void testThreadLocalInheritableIfConfigured() {
        System.setProperty(DefaultThreadContextMap.INHERITABLE_MAP, "true");
        try {
            final ThreadLocal<Map<String, String>> threadLocal = DefaultThreadContextMap.createThreadLocalMap(true);
            assertTrue(threadLocal instanceof InheritableThreadLocal<?>);
        } finally {
            System.clearProperty(DefaultThreadContextMap.INHERITABLE_MAP);
        }
    }
