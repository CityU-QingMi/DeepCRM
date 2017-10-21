    @Test
    public void testDefaultProtocol() throws Exception {
        // @formatter:off
        final SocketAppender appender = SocketAppender.newBuilder()
                .withPort(tcpServer.getLocalPort())
                .withReconnectDelayMillis(-1)
                .withName("test")
                .withImmediateFail(false)
                .withLayout(JsonLayout.newBuilder().setProperties(true).build())
                .build();
        // @formatter:on
        assertNotNull(appender);
        appender.stop();
    }
