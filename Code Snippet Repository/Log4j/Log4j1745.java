    @Test
    public void testTcpAppenderDeadlock() throws Exception {

        // @formatter:off
        final SocketAppender appender = SocketAppender.newBuilder()
                .withHost("localhost")
                .withPort(DYN_PORT)
                .withReconnectDelayMillis(100)
                .withName("test")
                .withImmediateFail(false)
                .withLayout(JsonLayout.newBuilder().setProperties(true).build())
                .build();
        // @formatter:on
        appender.start();
        // set appender on root and set level to debug
        logger.addAppender(appender);
        logger.setAdditive(false);
        logger.setLevel(Level.DEBUG);

        final TcpSocketTestServer tcpSocketServer = new TcpSocketTestServer(DYN_PORT);
        try {
            tcpSocketServer.start();

            logger.debug("This message is written because a deadlock never.");

            final LogEvent event = tcpSocketServer.getQueue().poll(3, TimeUnit.SECONDS);
            assertNotNull("No event retrieved", event);
        } finally {
            tcpSocketServer.shutdown();
        }
    }
