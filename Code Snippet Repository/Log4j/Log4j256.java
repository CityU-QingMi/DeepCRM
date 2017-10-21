    @Test
    public void testSupplierWithThrowable() {
        final ThrowableExpectingLogger logger = new ThrowableExpectingLogger(true);
        final ThrowableMessage message = new ThrowableMessage(t);
        final Supplier<Message> supplier = new Supplier<Message>() {
            @Override
            public Message get() {
                return message;
            }
        };

        logger.debug(supplier);
        logger.error(supplier);
        logger.fatal(supplier);
        logger.info(supplier);
        logger.trace(supplier);
        logger.warn(supplier);
        logger.log(Level.INFO, supplier);

        logger.debug(MARKER, supplier);
        logger.error(MARKER, supplier);
        logger.fatal(MARKER, supplier);
        logger.info(MARKER, supplier);
        logger.trace(MARKER, supplier);
        logger.warn(MARKER, supplier);
        logger.log(Level.INFO, MARKER, supplier);
    }
