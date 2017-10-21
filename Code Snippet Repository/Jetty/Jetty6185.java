    @Test
    public void testAbstractEchoInstance() throws Exception
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        server.addBean(container); // allow to shutdown with server
        EchoStringEndpoint echoer = new EchoStringEndpoint();
        Assert.assertThat(echoer,instanceOf(javax.websocket.Endpoint.class));
        // Issue connect using instance of class that extends abstract that extends Endpoint
        Session session = container.connectToServer(echoer,serverUri);
        if (LOG.isDebugEnabled())
            LOG.debug("Client Connected: {}",session);
        session.getBasicRemote().sendText("Echo");
        if (LOG.isDebugEnabled())
            LOG.debug("Client Message Sent");
        echoer.messageQueue.awaitMessages(1,1000,TimeUnit.MILLISECONDS);
    }
