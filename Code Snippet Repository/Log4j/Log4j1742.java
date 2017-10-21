    static void testTcpAppender(final TcpSocketTestServer tcpTestServer, final Logger logger, final int bufferSize)
            throws Exception {
        // @formatter:off
        final SocketAppender appender = SocketAppender.newBuilder()
                .withHost("localhost")
                .withPort(tcpTestServer.getLocalPort())
                .withReconnectDelayMillis(-1)
                .withName("test")
                .withImmediateFail(false)
                .withBufferSize(bufferSize)
                .withLayout(JsonLayout.newBuilder().setProperties(true).build())
                .build();
        // @formatter:on
        appender.start();
        Assert.assertEquals(bufferSize, appender.getManager().getByteBuffer().capacity());

        // set appender on root and set level to debug
        logger.addAppender(appender);
        logger.setAdditive(false);
        logger.setLevel(Level.DEBUG);
        final String tcKey = "UUID";
        final String expectedUuidStr = UUID.randomUUID().toString();
        ThreadContext.put(tcKey, expectedUuidStr);
        ThreadContext.push(expectedUuidStr);
        final String expectedExMsg = "This is a test";
        try {
            logger.debug("This is a test message");
            final Throwable child = new LoggingException(expectedExMsg);
            logger.error("Throwing an exception", child);
            logger.debug("This is another test message");
        } finally {
            ThreadContext.remove(tcKey);
            ThreadContext.pop();
        }
        Thread.sleep(250);
        LogEvent event = tcpTestServer.getQueue().poll(3, TimeUnit.SECONDS);
        assertNotNull("No event retrieved", event);
        assertTrue("Incorrect event", event.getMessage().getFormattedMessage().equals("This is a test message"));
        assertTrue("Message not delivered via TCP", tcpTestServer.getCount() > 0);
        assertEquals(expectedUuidStr, event.getContextData().getValue(tcKey));
        event = tcpTestServer.getQueue().poll(3, TimeUnit.SECONDS);
        assertNotNull("No event retrieved", event);
        assertTrue("Incorrect event", event.getMessage().getFormattedMessage().equals("Throwing an exception"));
        assertTrue("Message not delivered via TCP", tcpTestServer.getCount() > 1);
        assertEquals(expectedUuidStr, event.getContextStack().pop());
        assertNotNull(event.getThrownProxy());
        assertEquals(expectedExMsg, event.getThrownProxy().getMessage());
    }
