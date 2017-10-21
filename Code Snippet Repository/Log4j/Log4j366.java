    @Test
    public void getLogger_Object_ParameterizedMessageFactory() {
        // The TestLogger logger was already created in an instance variable for this class.
        // The message factory is only used when the logger is created.
        final ParameterizedMessageFactory messageFactory =  ParameterizedMessageFactory.INSTANCE;
        final TestLogger testLogger = (TestLogger) LogManager.getLogger(new TestParameterizedMessageFactory(),
                messageFactory);
        assertNotNull(testLogger);
        assertEqualMessageFactory(messageFactory, testLogger);
        testLogger.debug("{}", Integer.MAX_VALUE);
        assertEquals(1, testLogger.getEntries().size());
        assertEquals(" DEBUG " + Integer.MAX_VALUE, testLogger.getEntries().get(0));
    }
