    @Test
    public void getLogger_String_SimpleMessageFactory() {
        final SimpleMessageFactory messageFactory = SimpleMessageFactory.INSTANCE;
        final TestLogger testLogger = (TestLogger) LogManager.getLogger("getLogger_String_StringFormatterMessageFactory",
                messageFactory);
        assertNotNull(testLogger);
        assertEqualMessageFactory(messageFactory, testLogger);
        testLogger.debug("{} %,d {foo}", Integer.MAX_VALUE);
        assertEquals(1, testLogger.getEntries().size());
        assertEquals(" DEBUG {} %,d {foo}", testLogger.getEntries().get(0));
    }
