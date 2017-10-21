    @Test
    public void getFormatterLogger() {
        // The TestLogger logger was already created in an instance variable for this class.
        // The message factory is only used when the logger is created.
        final TestLogger testLogger = (TestLogger) LogManager.getFormatterLogger();
        final TestLogger altLogger = (TestLogger) LogManager.getFormatterLogger(getClass());
        assertEquals(testLogger.getName(), altLogger.getName());
        assertNotNull(testLogger);
        assertMessageFactoryInstanceOf(testLogger.getMessageFactory(), StringFormatterMessageFactory.class);
        assertEqualMessageFactory(StringFormatterMessageFactory.INSTANCE, testLogger);
        testLogger.debug("%,d", Integer.MAX_VALUE);
        assertEquals(1, testLogger.getEntries().size());
        assertEquals(String.format(" DEBUG %,d", Integer.MAX_VALUE), testLogger.getEntries().get(0));
    }
