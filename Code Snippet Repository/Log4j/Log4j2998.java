    private static void checkMessageFactory(final String msg, final MessageFactory messageFactory1,
            final Logger testLogger1) {
        if (messageFactory1 == null) {
            assertEquals(msg, AbstractLogger.DEFAULT_MESSAGE_FACTORY_CLASS,
                    testLogger1.getMessageFactory().getClass());
        } else {
            MessageFactory actual = testLogger1.getMessageFactory();
            if (actual instanceof MessageFactory2Adapter) {
                actual = ((MessageFactory2Adapter) actual).getOriginal();
            }
            assertEquals(msg, messageFactory1, actual);
        }
    }
