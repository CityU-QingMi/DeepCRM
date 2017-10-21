    @Test
    public void getLogger_String_ParameterizedMessageFactory() {
        final ParameterizedMessageFactory messageFactory =  ParameterizedMessageFactory.INSTANCE;
        final TestLogger testLogger = (TestLogger) LogManager.getLogger("getLogger_String_ParameterizedMessageFactory",
                messageFactory);
        assertNotNull(testLogger);
        assertEqualMessageFactory(messageFactory, testLogger);
        testLogger.debug("{}", Integer.MAX_VALUE);
        assertEquals(1, testLogger.getEntries().size());
        assertEquals(" DEBUG " + Integer.MAX_VALUE, testLogger.getEntries().get(0));
    }
