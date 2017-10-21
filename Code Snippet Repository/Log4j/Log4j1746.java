    @Test
    public void testTcpAppenderNoWait() throws Exception {
        // @formatter:off
        final SocketAppender appender = SocketAppender.newBuilder()
                .withHost("localhost")
                .withPort(ERROR_PORT)
                .withReconnectDelayMillis(100)
                .withName("test")
                .withImmediateFail(false)
                .withIgnoreExceptions(false)
                .withLayout(JsonLayout.newBuilder().setProperties(true).build())
                .build();
        // @formatter:on
        appender.start();
        // set appender on root and set level to debug
        logger.addAppender(appender);
        logger.setAdditive(false);
        logger.setLevel(Level.DEBUG);

        try {
            logger.debug("This message is written because a deadlock never.");
            fail("No Exception was thrown");
        } catch (final Exception ex) {
            // TODO: move exception to @Test(expect = Exception.class)
            // Failure is expected.
            // ex.printStackTrace();
        }
    }
