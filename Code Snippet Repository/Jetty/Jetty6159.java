    @Test
    public void testEcho() throws Exception
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        server.addBean(container); // allow to shutdown with server
        AnnotatedEchoClient echoer = new AnnotatedEchoClient();
        Session session = container.connectToServer(echoer,serverUri);
        session.getBasicRemote().sendText("Echo");
        echoer.messageQueue.awaitMessages(1,1000,TimeUnit.MILLISECONDS);
    }
