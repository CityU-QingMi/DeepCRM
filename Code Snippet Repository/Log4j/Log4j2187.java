    @Test
    public void testSuffix_getCauseStackTraceAsStringWithSuppressedThrowable() throws Exception {
        final IllegalArgumentException cause = new IllegalArgumentException("This is a test");
        final Throwable throwable = new RuntimeException(cause);
        throwable.addSuppressed(new IOException("This is a test"));
        final ThrowableProxy proxy = new ThrowableProxy(throwable);

        final String suffix = "some suffix";
        assertTrue(allLinesContain(proxy.getCauseStackTraceAsString(suffix), suffix));
    }
