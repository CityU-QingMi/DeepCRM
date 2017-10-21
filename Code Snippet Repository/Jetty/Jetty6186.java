    @Test
    public void testAbstractEchoClassref() throws Exception
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        server.addBean(container); // allow to shutdown with server
        // Issue connect using class reference (class that extends abstract that extends Endpoint)
        Session session = container.connectToServer(EchoStringEndpoint.class,serverUri);
        if (LOG.isDebugEnabled())
            LOG.debug("Client Connected: {}",session);
        session.getBasicRemote().sendText("Echo");
        if (LOG.isDebugEnabled())
            LOG.debug("Client Message Sent");
        // TODO: figure out echo verification.
        // echoer.messageQueue.awaitMessages(1,1000,TimeUnit.MILLISECONDS);
    }
