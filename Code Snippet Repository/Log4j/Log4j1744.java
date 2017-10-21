    @Test
    public void testUdpAppender() throws Exception {
        try {
            udpServer.latch.await();
        } catch (final InterruptedException ex) {
            ex.printStackTrace();
        }

        // @formatter:off
        final SocketAppender appender = SocketAppender.newBuilder()
                .withProtocol(Protocol.UDP)
                .withPort(tcpServer.getLocalPort())
                .withReconnectDelayMillis(-1)
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
        logger.debug("This is a udp message");
        final LogEvent event = udpServer.getQueue().poll(3, TimeUnit.SECONDS);
        assertNotNull("No event retrieved", event);
        assertTrue("Incorrect event", event.getMessage().getFormattedMessage().equals("This is a udp message"));
        assertTrue("Message not delivered via UDP", udpServer.getCount() > 0);
    }
