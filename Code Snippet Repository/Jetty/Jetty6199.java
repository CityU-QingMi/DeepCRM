    @Test
    public void testWholeBinaryMessage() throws Exception {
        final TestEndpoint echoer = new TestEndpoint(new WholeByteBufferCaptureHandler());
        Assert.assertThat(echoer, instanceOf(javax.websocket.Endpoint.class));
        // Issue connect using instance of class that extends Endpoint
        final Session session = container.connectToServer(echoer, serverUri);
        if (LOG.isDebugEnabled())
            LOG.debug("Client Connected: {}", session);
        sendBinary(session, "");
        sendBinary(session, "Echo");
        if (LOG.isDebugEnabled())
            LOG.debug("Client Message Sent");
        echoer.handler.getMessageQueue().awaitMessages(2, 1000, TimeUnit.MILLISECONDS);
    }
