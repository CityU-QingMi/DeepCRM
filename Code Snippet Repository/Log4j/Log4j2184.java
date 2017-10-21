    @Test
    public void testSerialization() throws Exception {
        final Throwable throwable = new IllegalArgumentException("This is a test");
        final ThrowableProxy proxy = new ThrowableProxy(throwable);
        final byte[] binary = serialize(proxy);
        final ThrowableProxy proxy2 = deserialize(binary);

        assertEquals(proxy.getName(), proxy2.getName());
        assertEquals(proxy.getMessage(), proxy2.getMessage());
        assertEquals(proxy.getCauseProxy(), proxy2.getCauseProxy());
        assertArrayEquals(proxy.getExtendedStackTrace(), proxy2.getExtendedStackTrace());
    }
