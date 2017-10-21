    @Test
    public void testSessionInfo() throws Exception
    {
        URI serverURI = new URI("ws://localhost:58080/cdi-webapp/");

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        ClientSessionInfoSocket socket = new ClientSessionInfoSocket();
        
        container.connectToServer(socket,serverURI.resolve("sessioninfo"));

        assertThat("Await open", socket.openLatch.await(1,TimeUnit.SECONDS), is(true));
        
        socket.session.getBasicRemote().sendText("info");
        socket.messages.awaitEventCount(1,2,TimeUnit.SECONDS);
        
        System.out.printf("socket.messages.size = %s%n",socket.messages.size());
        
        String msg = socket.messages.poll();
        System.out.printf("Message is [%s]%n",msg);
        
        assertThat("Message", msg, containsString("HttpSession = HttpSession"));
        
        socket.session.getBasicRemote().sendText("close");
        assertThat("Await close", socket.closeLatch.await(1,TimeUnit.SECONDS),is(true));
    }
