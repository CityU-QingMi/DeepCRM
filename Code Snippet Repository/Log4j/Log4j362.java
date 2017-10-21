    @Test
    public void getFormatterLogger_Object() {
        // The TestLogger logger was already created in an instance variable for this class.
        // The message factory is only used when the logger is created.
        final TestLogger testLogger = (TestLogger) LogManager.getFormatterLogger(new TestStringFormatterMessageFactory());
        assertNotNull(testLogger);
        assertMessageFactoryInstanceOf(testLogger.getMessageFactory(), StringFormatterMessageFactory.class);
        assertEqualMessageFactory(StringFormatterMessageFactory.INSTANCE, testLogger);
        testLogger.debug("%,d", Integer.MAX_VALUE);
        assertEquals(1, testLogger.getEntries().size());
        assertEquals(String.format(" DEBUG %,d", Integer.MAX_VALUE), testLogger.getEntries().get(0));
    }
