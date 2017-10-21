    @Test
    public void testMessageWithThrowable() {
        final ThrowableExpectingLogger logger = new ThrowableExpectingLogger(true);
        final ThrowableMessage message = new ThrowableMessage(t);

        logger.debug(message);
        logger.error(message);
        logger.fatal(message);
        logger.info(message);
        logger.trace(message);
        logger.warn(message);
        logger.log(Level.INFO, message);

        logger.debug(MARKER, message);
        logger.error(MARKER, message);
        logger.fatal(MARKER, message);
        logger.info(MARKER, message);
        logger.trace(MARKER, message);
        logger.warn(MARKER, message);
        logger.log(Level.INFO, MARKER, message);
    }
