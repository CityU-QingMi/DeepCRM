    private static Logger testMessageFactoryMismatch(final String name,
                                                     final MessageFactory messageFactory1,
            final MessageFactory messageFactory2) {
        final Logger testLogger1 = (Logger) LogManager.getLogger(name, messageFactory1);
        assertNotNull(testLogger1);
        checkMessageFactory(messageFactory1, testLogger1);
        final Logger testLogger2 = (Logger) LogManager.getLogger(name, messageFactory2);
        assertNotNull(testLogger2);
        checkMessageFactory(messageFactory2, testLogger2);
        return testLogger1;
    }
