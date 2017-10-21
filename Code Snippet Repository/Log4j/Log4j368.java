    @Test
    public void getLogger_String_MessageFactoryMismatch() {
        final StringFormatterMessageFactory messageFactory = StringFormatterMessageFactory.INSTANCE;
        final TestLogger testLogger = (TestLogger) LogManager.getLogger("getLogger_String_MessageFactoryMismatch",
                messageFactory);
        assertNotNull(testLogger);
        assertEqualMessageFactory(messageFactory, testLogger);
        final TestLogger testLogger2 = (TestLogger) LogManager.getLogger("getLogger_String_MessageFactoryMismatch",
                ParameterizedMessageFactory.INSTANCE);
        assertNotNull(testLogger2);
        //TODO: How to test?
        //This test context always creates new loggers, other test context impls I tried fail other tests.
        //assertEquals(messageFactory, testLogger2.getMessageFactory());
        testLogger.debug("%,d", Integer.MAX_VALUE);
        assertEquals(1, testLogger.getEntries().size());
        assertEquals(String.format(" DEBUG %,d", Integer.MAX_VALUE), testLogger.getEntries().get(0));
    }
