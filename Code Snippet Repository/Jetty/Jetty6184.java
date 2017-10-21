    @Test
    public void testBasicEchoClassref() throws Exception
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        server.addBean(container); // allow to shutdown with server
        // Issue connect using class reference (class extends Endpoint)
        Session session = container.connectToServer(EndpointEchoClient.class,serverUri);
        if (LOG.isDebugEnabled())
            LOG.debug("Client Connected: {}",session);
        session.getBasicRemote().sendText("Echo");
        if (LOG.isDebugEnabled())
            LOG.debug("Client Message Sent");
        // TODO: figure out echo verification.
        // echoer.textCapture.messageQueue.awaitMessages(1,1000,TimeUnit.MILLISECONDS);
    }
