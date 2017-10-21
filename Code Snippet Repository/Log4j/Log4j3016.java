    private static void checkMessageFactory(final MessageFactory messageFactory1, final Logger testLogger1) {
        if (messageFactory1 == null) {
            assertEquals(AbstractLogger.DEFAULT_MESSAGE_FACTORY_CLASS, testLogger1.getMessageFactory().getClass());
        } else {
            MessageFactory actual = testLogger1.getMessageFactory();
            if (actual instanceof MessageFactory2Adapter) {
                actual = ((MessageFactory2Adapter) actual).getOriginal();
            }
            assertEquals(messageFactory1, actual);
        }
    }
