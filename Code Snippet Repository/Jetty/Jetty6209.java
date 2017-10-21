    private void run() throws DeploymentException, IOException, URISyntaxException, InterruptedException
    {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        System.out.printf("WebSocketContainer Impl: %s%n",container.getClass().getName());

        ExampleSocket socket = new ExampleSocket();
        URI uri = new URI("ws://echo.websocket.org/");
        Session session = container.connectToServer(socket,uri);
        RemoteEndpoint.Basic remote = session.getBasicRemote();
        String msg = "Hello world";
        System.out.printf("Sending: %s%n",Objects.toString(msg));
        remote.sendText(msg);
        socket.messageLatch.await(1,TimeUnit.SECONDS); // give remote 1 second to respond
        session.close();
        socket.closeLatch.await(1,TimeUnit.SECONDS); // give remote 1 second to acknowledge response
        System.out.println("Socket is closed");
    }
